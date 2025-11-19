package com.example.umc9th_project.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾지 못했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    @Override
    public String getExplainError() throws NoSuchFieldException {
        return "";
    }

    @Override
    public Reason getReason() {
        return null;
    }

    @Override
    public Reason getReasonHttpStatus() {
        return null;
    }
}

