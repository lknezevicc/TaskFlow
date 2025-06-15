package hr.lknezevic.taskflow.taskflowgui.mappers;

import hr.lknezevic.taskflow.taskflowgui.dto.TaskDto;
import hr.lknezevic.taskflow.taskflowgui.observable.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {

    default TaskFx toFx(
            TaskDto dto,
            UserFx assignedTo,
            ProjectFx project,
            List<CommentFx> comments,
            List<TimeLogFx> timeLogs
    ) {
        if (dto == null) return null;

        TaskFx fx = new TaskFx();
        fx.setId(dto.id());
        fx.setTitle(dto.title());
        fx.setDescription(dto.description());
        fx.setDueDate(dto.dueDate());
        fx.setCreatedAt(dto.createdAt());
        fx.setUpdatedAt(dto.updatedAt());
        fx.setStatus(dto.status());
        fx.setPriority(dto.priority());
        fx.setAssignedTo(assignedTo);
        fx.setProject(project);
        fx.setTimeSpent(dto.timeSpent());
        fx.setComments(comments);
        fx.setTimeLogs(timeLogs);
        return fx;
    }

    // DTO samo čuva ID-jeve
    default TaskDto toDto(TaskFx fx) {
        if (fx == null) return null;

        List<Long> commentIds = fx.getComments() == null
                ? List.of()
                : fx.getComments().stream().map(CommentFx::getId).toList();

        List<Long> timeLogIds = fx.getTimeLogs() == null
                ? List.of()
                : fx.getTimeLogs().stream().map(TimeLogFx::getId).toList();

        return new TaskDto(
                fx.getId(),
                fx.getTitle(),
                fx.getDescription(),
                fx.getDueDate(),
                fx.getCreatedAt(),
                fx.getUpdatedAt(),
                fx.getStatus(),
                fx.getPriority(),
                fx.getAssignedTo() != null ? fx.getAssignedTo().getId() : null,
                fx.getProject() != null ? fx.getProject().getId() : null,
                fx.getTimeSpent(),
                commentIds,
                timeLogIds
        );
    }

    default List<TaskDto> toDtoList(List<TaskFx> list) {
        return list == null ? List.of() : list.stream().map(this::toDto).toList();
    }
}
