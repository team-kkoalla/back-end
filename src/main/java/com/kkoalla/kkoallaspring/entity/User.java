package com.kkoalla.kkoallaspring.entity;

import com.kkoalla.kkoallaspring.dto.request.CreateUserRequestDto;
import com.kkoalla.kkoallaspring.dto.response.KakaoUserInfoResponseDto;
import com.kkoalla.kkoallaspring.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, unique = true, length = 10)
    private String nickname;

    @Column(nullable = false)
    private Long kakaoId;

    @OneToMany(mappedBy = "user")
    private List<Badge> badges = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings = new ArrayList<>();

    public User(KakaoUserInfoResponseDto kakaoUserInfoResponseDto, CreateUserRequestDto createUserRequestDto) {
        this.phone = createUserRequestDto.getPhone();
        this.email = kakaoUserInfoResponseDto.getKakao_account().getEmail();
        this.nickname = createUserRequestDto.getNickname();
        this.kakaoId = kakaoUserInfoResponseDto.getId();
    }
}
