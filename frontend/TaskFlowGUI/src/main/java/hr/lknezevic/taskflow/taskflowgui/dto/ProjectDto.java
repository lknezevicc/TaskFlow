package hr.lknezevic.taskflow.taskflowgui.dto;

import hr.lknezevic.taskflow.taskflowgui.enums.ProjectPriority;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record ProjectDto(
        Long id,
        String title,
        String description,
        ProjectPriority priority,
        LocalDateTime createdAt,
        LocalDateTime deadline,
        List<Long> taskIds
) implements Serializable {}
