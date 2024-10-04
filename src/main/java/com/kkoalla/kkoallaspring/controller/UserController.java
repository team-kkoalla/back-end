package com.kkoalla.kkoallaspring.controller;

import com.kkoalla.kkoallaspring.dto.request.CreateUserRequestDto;
import com.kkoalla.kkoallaspring.dto.request.LoginDto;
import com.kkoalla.kkoallaspring.dto.response.KakaoTokenResponseDto;
import com.kkoalla.kkoallaspring.dto.response.KakaoUserInfoResponseDto;
import com.kkoalla.kkoallaspring.dto.response.LoginResponseDto;
import com.kkoalla.kkoallaspring.dto.response.UserProfileResponseDto;
import com.kkoalla.kkoallaspring.entity.User;
import com.kkoalla.kkoallaspring.global.config.execption.BaseException;
import com.kkoalla.kkoallaspring.global.config.response.BaseResponse;
import com.kkoalla.kkoallaspring.service.KakaoService;
import com.kkoalla.kkoallaspring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.kkoalla.kkoallaspring.global.config.response.BaseResponseStatus.*;

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

    // 이름, 이메일, 전화번호 수정
    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<String>> signUp(@RequestBody CreateUserRequestDto createUserRequestDto) throws BaseException, IOException {
        String kakaoToken = createUserRequestDto.getKakaoToken();

        KakaoUserInfoResponseDto info = kakaoService.getUserInfoByKakaoToken(kakaoToken);

        userService.createUser(info, createUserRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(SIGNUP_SUCCESS.getMessage()));
    }

    @PostMapping("/signin")
    public ResponseEntity<BaseResponse<LoginResponseDto>> login(@RequestBody LoginDto loginDto) throws BaseException, IOException {

        KakaoUserInfoResponseDto kakaoUserInfoResponseDto = kakaoService.getUserInfoByKakaoToken(loginDto.getKakaoToken());
        if (!userService.existsByKakaoId(kakaoUserInfoResponseDto.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseResponse<>(POST_USERS_NO_EXISTS_USER));
        }

        User user = userService.findByKakaoId(kakaoUserInfoResponseDto.getId());
        LoginResponseDto dto = new LoginResponseDto(user.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(dto));
    }

    @GetMapping("{userId}")
    public ResponseEntity<BaseResponse<UserProfileResponseDto>> getUserProfile(@PathVariable Long userId) throws BaseException, IOException {
        User user = userService.findById(userId);
        UserProfileResponseDto userProfileResponseDto = new UserProfileResponseDto(user.getNickname());
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(userProfileResponseDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<BaseResponse<Long>> kakaoLogout(String accessToken) throws BaseException, IOException {
        Long userId = kakaoService.kakaoLogout(accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(userId));
    }

}
