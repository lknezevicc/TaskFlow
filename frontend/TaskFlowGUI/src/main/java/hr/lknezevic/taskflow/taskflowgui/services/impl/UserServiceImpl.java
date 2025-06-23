package hr.lknezevic.taskflow.taskflowgui.services.impl;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.dto.UserDto;
import hr.lknezevic.taskflow.taskflowgui.services.BaseService;
import hr.lknezevic.taskflow.taskflowgui.services.UserService;
import hr.lknezevic.taskflow.taskflowgui.utils.HttpRequestModifier;
import hr.lknezevic.taskflow.taskflowgui.utils.JsonUtil;

import java.net.http.HttpClient;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UserServiceImpl extends BaseService implements UserService {

    @Inject
    public UserServiceImpl(HttpClient httpClient, JsonUtil jsonUtil, HttpRequestModifier httpRequestModifier) {
        super(httpClient, jsonUtil, httpRequestModifier);
    }

    @Override
    public CompletableFuture<List<UserDto>> getUsers() {
        return getList("http://localhost:3000/users", UserDto.class);
    }

    @Override
    public CompletableFuture<UserDto> getCurrentUser() {
        return get("http://localhost:3000/current-user", UserDto.class);
    }

    @Override
    public CompletableFuture<UserDto> saveUser(UserDto userDto) {
        return post("http://localhost:3000/users", userDto, UserDto.class);
    }

    @Override
    public CompletableFuture<UserDto> updateUser(UserDto userDto) {
        return put("http://localhost:3000/users", userDto, UserDto.class);
    }
}
