package com.kkoalla.kkoallaspring.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUserRequestDto {
        String phone;
        String nickname;
        String kakaoToken;
}
