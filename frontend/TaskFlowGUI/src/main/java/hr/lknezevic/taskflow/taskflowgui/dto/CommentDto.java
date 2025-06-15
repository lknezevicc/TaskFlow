package hr.lknezevic.taskflow.taskflowgui.dto;

import java.time.LocalDateTime;

public record CommentDto(
        Long id,
        LocalDateTime commentedAt,
        Long userId,
        String description
) {}
