package com.example.umc9th_project.common.exception;

public class GeneralException extends RuntimeException{
    private BaseErrorCode code;

    @Override
    public String getMessage() {
        return code.getReason().getMessage();
    }

    public Reason getErrorReason() {
        return this.code.getReason();
    }

    public Reason getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }

}
