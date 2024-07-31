package com.kkoalla.kkoallaspring.controller;

import com.kkoalla.kkoallaspring.dto.request.CreateUserRequestDto;
import com.kkoalla.kkoallaspring.dto.response.KakaoTokenResponseDto;
import com.kkoalla.kkoallaspring.dto.response.KakaoUserInfoResponseDto;
import com.kkoalla.kkoallaspring.global.config.execption.BaseException;
import com.kkoalla.kkoallaspring.global.config.response.BaseResponse;
import com.kkoalla.kkoallaspring.global.config.response.BaseResponseStatus;
import com.kkoalla.kkoallaspring.service.KakaoService;
import com.kkoalla.kkoallaspring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final KakaoService kakaoService;

    @PostMapping("/callback")
    public ResponseEntity<BaseResponse<KakaoTokenResponseDto>> loginKakao(@RequestParam("code") String code) throws IOException {
        KakaoTokenResponseDto dto = kakaoService.getToken(code);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(dto));
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<String>> signUp(@RequestBody CreateUserRequestDto createUserRequestDto) throws BaseException, IOException {
        String kakaoToken = createUserRequestDto.getKakaoToken();

        KakaoUserInfoResponseDto info = kakaoService.getUserInfoByKakaoToken(kakaoToken);

        userService.CreateUser(info, createUserRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(BaseResponseStatus.SIGNUP_SUCCESS.getMessage()));
    }

}
