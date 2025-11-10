package com.example.umc9th_project.common.exception;

import com.example.umc9th_project.common.dto.ApiResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ApiResponseDto<String> handleRuntimeException(RuntimeException e) {
        return ApiResponseDto.onFailure(404, e.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponseDto<String> handleException(Exception e) {
        return ApiResponseDto.onFailure(500, "서버 내부 오류가 발생했습니다.", null);
    }
}
