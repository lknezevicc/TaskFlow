package hr.lknezevic.taskflow.taskflowgui.dto;

import java.util.List;

public record ProjectDto(
        Long id,
        String title,
        List<Long> taskIds
) {}
