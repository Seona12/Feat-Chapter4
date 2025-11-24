package com.example.umc9th_project.domain.review.exception;

import com.example.umc9th_project.common.exception.BaseErrorCode;
import com.example.umc9th_project.common.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
