package com.example.umc9th_project.test;

import com.example.umc9th_project.common.dto.ApiResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {
    @GetMapping("/error-test")
    public ApiResponseDto<String> errorTest() {
        throw new RuntimeException("테스트용 강제 500 오류 발생!");
    }

}
