package hr.lknezevic.taskflow.taskflowgui.mappers;

import hr.lknezevic.taskflow.taskflowgui.dto.UserDto;
import hr.lknezevic.taskflow.taskflowgui.observable.UserFx;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import org.mapstruct.Mapper;

import java.io.ByteArrayInputStream;
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
        fx.setCountry(dto.country());
        fx.setPhoneNumber(dto.phoneNumber());
        fx.setUsername(dto.username());
        fx.setPassword(dto.password());
        fx.setRole(dto.role());
        fx.setDepartment(dto.department());

        if (dto.profileImage() != null) {
            fx.setProfileImage(new Image(new ByteArrayInputStream(dto.profileImage())));
        }

        return fx;
    }

    default UserDto toDto(UserFx fx) {
        if (fx == null) return null;

        // solution from https://stackoverflow.com/questions/38095984/convert-javafx-image-object-to-byte-array
        byte[] imageBytes = null;
        if (fx.getProfileImage() != null) {
            Image img = fx.getProfileImage();

            // Cache Width and Height to 'int's (because getWidth/getHeight return Double) and getPixels needs 'int's //
            int w = (int)img.getWidth();
            int h = (int)img.getHeight();

            // Create a new Byte Buffer, but we'll use BGRA (1 byte for each channel) //
            imageBytes = new byte[w * h * 4];

            /* Since you can get the output in whatever format with a WritablePixelFormat,
               we'll use an already created one for ease-of-use.
               The Second last parameter is byte offset you want to start in your buffer,
               and the last parameter is stride (in bytes) per line for your buffer. */
            img.getPixelReader().getPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), imageBytes, 0, w * 4);
        }

        return new UserDto(
                fx.getId(),
                fx.getFirstName(),
                fx.getLastName(),
                fx.getEmail(),
                fx.getCountry(),
                fx.getPhoneNumber(),
                fx.getUsername(),
                fx.getPassword(),
                fx.getRole(),
                fx.getDepartment(),
                imageBytes
        );
    }

    default List<UserFx> toFxList(List<UserDto> list) {
        return list == null ? List.of() : list.stream().map(this::toFx).toList();
    }

    default List<UserDto> toDtoList(List<UserFx> list) {
        return list == null ? List.of() : list.stream().map(this::toDto).toList();
    }
}
