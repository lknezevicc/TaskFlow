package hr.lknezevic.taskflow.taskflowgui.mappers;

import hr.lknezevic.taskflow.taskflowgui.dto.UserDto;
import hr.lknezevic.taskflow.taskflowgui.observable.UserFx;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    default UserFx toFx(UserDto dto) {
        if (dto == null) return null;

        UserFx fx = new UserFx();
        fx.setId(dto.id());
        fx.setFirstName(dto.firstName());
        fx.setLastName(dto.lastName());
        fx.setEmail(dto.email());
        fx.setUsername(dto.username());
        return fx;
    }

    default UserDto toDto(UserFx fx) {
        if (fx == null) return null;

        return new UserDto(
                fx.getId(),
                fx.getFirstName(),
                fx.getLastName(),
                fx.getEmail(),
                fx.getUsername()
        );
    }

    default List<UserFx> toFxList(List<UserDto> list) {
        return list == null ? List.of() : list.stream().map(this::toFx).toList();
    }

    default List<UserDto> toDtoList(List<UserFx> list) {
        return list == null ? List.of() : list.stream().map(this::toDto).toList();
    }
}
