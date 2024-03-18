package com.ssafy.user.common;

public enum CustomExceptionStatus {
    NOT_SUPPORTED_OAUTH_SERVICE("User_400_1", "해당 OAuth 서비스는 제공하지 않습니다."),
    INVALID_AUTHORIZATION_CODE("User_400_2", "유효하지 않은 인증 코드입니다."),
    FAILED_TO_DISCONNECT_SOCIAL("User_400_3", "가입하신 소셜 계정과의 연결 해제에 실패했습니다."),
    NOT_AUTHENTICATED_ACCOUNT("User_400_4", "로그인이 필요합니다."),
    ACCOUNT_ACCESS_DENIED("User_400_5", "권한이 없습니다.");

    private final String code;
    private final String message;

    private CustomExceptionStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
