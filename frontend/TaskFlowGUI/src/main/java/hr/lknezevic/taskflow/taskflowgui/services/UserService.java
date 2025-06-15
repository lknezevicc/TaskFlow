package hr.lknezevic.taskflow.taskflowgui.services;

import hr.lknezevic.taskflow.taskflowgui.dto.UserDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserService {
    CompletableFuture<List<UserDto>> getUsers();
    CompletableFuture<UserDto> getCurrentUser();
}
