package com.kkoalla.kkoallaspring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {
    private String kakaoToken;
}
