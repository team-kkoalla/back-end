package com.kkoalla.kkoallaspring.service;

import com.kkoalla.kkoallaspring.dto.request.CreateUserRequestDto;
import com.kkoalla.kkoallaspring.dto.response.KakaoUserInfoResponseDto;
import com.kkoalla.kkoallaspring.entity.User;
import com.kkoalla.kkoallaspring.repositroy.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void CreateUser(KakaoUserInfoResponseDto kakaoUserInfoResponseDto, CreateUserRequestDto createUserRequestDto) {
        User user = new User(kakaoUserInfoResponseDto, createUserRequestDto);
        userRepository.save(user);
    }
}
