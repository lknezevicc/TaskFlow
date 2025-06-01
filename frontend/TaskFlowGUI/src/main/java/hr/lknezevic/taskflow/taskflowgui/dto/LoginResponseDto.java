package hr.lknezevic.taskflow.taskflowgui.dto;

public record LoginResponseDto(
        String accessToken,
        String refreshToken
) { }
