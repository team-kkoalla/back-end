package com.kkoalla.kkoallaspring.global.config.response;

import lombok.Getter;

/* 에러 코드 관리 */
@Getter
public enum BaseResponseStatus {

    /**
     * 200 : 요청 성공
     */
    SUCCESS(true, 200, "요청에 성공하였습니다."),
    SIGNUP_SUCCESS(true, 201, "회원가입에 성공하였습니다."),
    /**
     * 400 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 400, "입력값을 확인해주세요."),
    // Common
    RESPONSE_ERROR(false, 401, "값을 불러오는데 실패하였습니다."),

    /**
     * 500 : Database, Server 오류
     */
    DATABASE_ERROR(false, 500, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 500, "서버와의 연결에 실패하였습니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;

    BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
