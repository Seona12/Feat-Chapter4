package com.example.umc9th_project.common.exception;

import com.example.umc9th_project.common.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ApiResponseDto<String> handleRuntimeException(RuntimeException e) {
        return ApiResponseDto.onFailure(404, e.getMessage(), null);
    }


    @Value("${webhook.discord.url:}")
    private String discordWebhookUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @ExceptionHandler(Exception.class)
    public ApiResponseDto<String> handleServerException(Exception e) {

        log.error("[500 ERROR] {}", e.getMessage(), e);

        String content = "**🚨 500 Internal Server Error 발생!**\n"
                + "```"
                + "\n[시각] " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + "\n[에러] " + e.getClass().getSimpleName()
                + "\n[메시지] " + e.getMessage()
                + "\n```";

        sendToDiscord(content);

        return ApiResponseDto.onFailure(500, "서버 내부 오류가 발생했습니다.", null);
    }

    private void sendToDiscord(String message) {
        try {
            if (discordWebhookUrl == null || discordWebhookUrl.isEmpty()) {
                log.warn("⚠️ Discord Webhook URL이 설정되어 있지 않습니다.");
                return;
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String payload = "{\"content\": \"" + message.replace("\"", "\\\"") + "\"}";
            HttpEntity<String> entity = new HttpEntity<>(payload, headers);

            restTemplate.postForEntity(discordWebhookUrl, entity, String.class);

        } catch (Exception ex) {
            log.error("❌ Discord 알림 전송 실패: {}", ex.getMessage());
        }
    }
}
