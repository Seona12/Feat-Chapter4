package com.example.umc9th_project.common.exception;

public interface BaseErrorCode extends BaseCode{
    String getExplainError() throws NoSuchFieldException;
}
