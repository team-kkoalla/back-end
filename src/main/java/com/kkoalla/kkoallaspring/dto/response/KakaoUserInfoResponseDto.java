package com.kkoalla.kkoallaspring.dto.response;

import lombok.Data;

@Data
public class KakaoUserInfoResponseDto {

    public Long id;
    public KakaoAccount kakao_account;
}
