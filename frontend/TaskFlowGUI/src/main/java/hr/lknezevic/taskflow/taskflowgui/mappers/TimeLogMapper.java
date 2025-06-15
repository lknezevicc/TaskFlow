package hr.lknezevic.taskflow.taskflowgui.mappers;

import hr.lknezevic.taskflow.taskflowgui.dto.TimeLogDto;
import hr.lknezevic.taskflow.taskflowgui.observable.TimeLogFx;
import hr.lknezevic.taskflow.taskflowgui.observable.UserFx;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TimeLogMapper {

    default TimeLogFx toFx(TimeLogDto dto, UserFx userFx) {
        if (dto == null) return null;

        TimeLogFx fx = new TimeLogFx();
        fx.setId(dto.id());
        fx.setStartTime(dto.startTime());
        fx.setDuration(dto.duration());
        fx.setUser(userFx);
        fx.setDescription(dto.description());

        return fx;
    }

    default TimeLogDto toDto(TimeLogFx fx) {
        if (fx == null) return null;

        return new TimeLogDto(
                fx.getId(),
                fx.getStartTime(),
                fx.getDuration(),
                fx.getUser() != null ? fx.getUser().getId() : null,
                fx.getDescription()
        );
    }

    default List<TimeLogDto> toDtoList(List<TimeLogFx> list) {
        return list == null ? List.of() : list.stream().map(this::toDto).toList();
    }
}
