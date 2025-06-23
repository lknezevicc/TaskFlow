package hr.lknezevic.taskflow.taskflowgui.dto;

import hr.lknezevic.taskflow.taskflowgui.enums.TaskPriority;
import hr.lknezevic.taskflow.taskflowgui.enums.TaskStatus;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public record TaskDto(
        Long id,
        String title,
        String description,
        LocalDateTime dueDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        TaskStatus status,
        TaskPriority priority,
        Long assignedToId,
        Long projectId,
        Duration timeSpent,
        List<Long> commentIds,
        List<Long> timeLogIds
) implements Serializable {}
