package hr.lknezevic.taskflow.taskflowgui.dto;

import java.time.Duration;
import java.time.LocalDateTime;

public record TimeLogDto(
        Long id,
        LocalDateTime startTime,
        Duration duration,
        Long userId,
        String description
) {}
