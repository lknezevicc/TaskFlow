package hr.lknezevic.taskflow.taskflowgui.mappers;

import hr.lknezevic.taskflow.taskflowgui.dto.CommentDto;
import hr.lknezevic.taskflow.taskflowgui.observable.CommentFx;
import hr.lknezevic.taskflow.taskflowgui.observable.UserFx;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    default CommentFx toFx(CommentDto dto, UserFx userFx) {
        if (dto == null) return null;

        CommentFx fx = new CommentFx();
        fx.setId(dto.id());
        fx.setCommentedAt(dto.commentedAt());
        fx.setUser(userFx); // dobavljeno izvana
        fx.setDescription(dto.description());
        return fx;
    }

    default CommentDto toDto(CommentFx fx) {
        if (fx == null) return null;

        return new CommentDto(
                fx.getId(),
                fx.getCommentedAt(),
                fx.getUser() != null ? fx.getUser().getId() : null,
                fx.getDescription()
        );
    }

    default List<CommentDto> toDtoList(List<CommentFx> list) {
        return list == null ? List.of() : list.stream().map(this::toDto).toList();
    }
}
