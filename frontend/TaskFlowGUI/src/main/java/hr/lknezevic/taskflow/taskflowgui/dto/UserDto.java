package hr.lknezevic.taskflow.taskflowgui.dto;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String country,
        String phoneNumber,
        String username,
        String password,
        String role,
        String department,
        byte[] profileImage
) {}
