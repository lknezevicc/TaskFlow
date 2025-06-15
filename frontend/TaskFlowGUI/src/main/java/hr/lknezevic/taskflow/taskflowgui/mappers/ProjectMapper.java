package hr.lknezevic.taskflow.taskflowgui.mappers;

import hr.lknezevic.taskflow.taskflowgui.dto.ProjectDto;
import hr.lknezevic.taskflow.taskflowgui.observable.ProjectFx;
import hr.lknezevic.taskflow.taskflowgui.observable.TaskFx;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {

    default ProjectFx toFx(ProjectDto dto, List<TaskFx> tasks) {
        if (dto == null) return null;

        ProjectFx fx = new ProjectFx();
        fx.setId(dto.id());
        fx.setTitle(dto.title());
        fx.setTasks(tasks); // mora doći izvana, npr. iz servisa
        return fx;
    }

    default ProjectDto toDto(ProjectFx fx) {
        if (fx == null) return null;

        List<Long> taskIds = fx.getTasks() != null
                ? fx.getTasks().stream().map(TaskFx::getId).toList()
                : List.of();

        return new ProjectDto(
                fx.getId(),
                fx.getTitle(),
                taskIds
        );
    }

    default List<ProjectDto> toDtoList(List<ProjectFx> list) {
        return list == null ? List.of() : list.stream().map(this::toDto).toList();
    }
}
