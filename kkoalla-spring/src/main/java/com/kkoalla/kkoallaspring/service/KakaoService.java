package com.kkoalla.kkoallaspring.service;

import com.kkoalla.kkoallaspring.dto.response.KakaoTokenResponseDto;
import com.kkoalla.kkoallaspring.dto.response.KakaoUserInfoResponseDto;
import com.kkoalla.kkoallaspring.global.config.execption.BaseException;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.io.IOException;
import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    @Value("${kakao.client_id}")
    private String client_id;

    @Value("${kakao.redirect_uri}")
    private String redirect_uri;

    private final String KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
    private final String KAUTH_USER_URL_HOST = "https://kapi.kakao.com";

    private final WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
                    .responseTimeout(Duration.ofSeconds(5))))
            .baseUrl(KAUTH_TOKEN_URL_HOST)
            .build();


    public KakaoTokenResponseDto getToken(String code) throws IOException {
        log.info("Requesting Kakao token with code: {}", code);

        try {
            return webClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/oauth/token")
                            .queryParam("grant_type", "authorization_code")
                            .queryParam("client_id", client_id)
                            .queryParam("redirect_uri", redirect_uri)
                            .queryParam("code", code)
                            .build())
                    .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                        log.error("4xx error occurred while requesting Kakao token: {}", clientResponse.statusCode());
                        return clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException("4xx error: " + errorBody)));
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                        log.error("5xx error occurred while requesting Kakao token: {}", clientResponse.statusCode());
                        return clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException("5xx error: " + errorBody)));
                    })
                    .bodyToMono(KakaoTokenResponseDto.class)
                    .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2)))  // Retry up to 3 times with 2 seconds delay
                    .block();
        } catch (WebClientResponseException e) {
            log.error("WebClientResponseException while requesting Kakao token: ", e);
            throw new RuntimeException("Error response from Kakao API: " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            log.error("Exception while requesting Kakao token: ", e);
            throw new RuntimeException("Exception while requesting Kakao token", e);
        }
    }

    public KakaoUserInfoResponseDto getUserInfoByKakaoToken(String accessToken) throws BaseException, IOException {
        KakaoUserInfoResponseDto userInfo = WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) // access token 인가
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoUserInfoResponseDto.class)
                .block();

        log.info("[ Kakao Service ] Auth ID ---> {} ", userInfo.getId());
        log.info("[ Kakao Service ] email ---> {} ", userInfo.getKakao_account().getEmail());

        return userInfo;
    }

    public Long kakaoLogout(String accessToken) throws BaseException, IOException {
        KakaoUserInfoResponseDto info = getUserInfoByKakaoToken(accessToken);
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v1/user/logout")
                        .build()
                )
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Params")))
                .bodyToMono(Long.class)
                .block();
    }
}
