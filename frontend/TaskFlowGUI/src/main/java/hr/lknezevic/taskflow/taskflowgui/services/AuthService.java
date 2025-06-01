package hr.lknezevic.taskflow.taskflowgui.services;

import hr.lknezevic.taskflow.taskflowgui.dto.LoginRequestDto;

import java.util.concurrent.CompletableFuture;

public interface AuthService {
    CompletableFuture<Boolean> authenticate(LoginRequestDto loginRequest);
}
