package com.kkoalla.kkoallaspring.global.config.execption;

import com.kkoalla.kkoallaspring.global.config.response.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private BaseResponseStatus status; // BaseResponseStatus 객체에 매핑
}
