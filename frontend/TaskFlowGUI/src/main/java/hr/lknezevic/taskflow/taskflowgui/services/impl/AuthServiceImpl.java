package hr.lknezevic.taskflow.taskflowgui.services.impl;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.services.BaseService;
import hr.lknezevic.taskflow.taskflowgui.utils.HttpRequestModifier;
import hr.lknezevic.taskflow.taskflowgui.utils.PlistUtil;
import hr.lknezevic.taskflow.taskflowgui.dto.LoginRequestDto;
import hr.lknezevic.taskflow.taskflowgui.dto.LoginResponseDto;
import hr.lknezevic.taskflow.taskflowgui.enums.PlistProperty;
import hr.lknezevic.taskflow.taskflowgui.services.AuthService;
import hr.lknezevic.taskflow.taskflowgui.utils.JsonUtil;

import java.net.http.HttpClient;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class AuthServiceImpl extends BaseService implements AuthService {
    private final PlistUtil plistUtil;

    @Inject
    public AuthServiceImpl(HttpClient httpClient, JsonUtil jsonUtil, PlistUtil plistUtil, HttpRequestModifier httpRequestModifier) {
        super(httpClient, jsonUtil, httpRequestModifier);
        this.plistUtil = plistUtil;
    }

    @Override
    public CompletableFuture<Boolean> authenticate(LoginRequestDto loginRequest) {
        return post("http://localhost:8080/auth/login", loginRequest, LoginResponseDto.class)
                .thenApply(response -> {
                    Optional.ofNullable(response).ifPresent(res -> {
                        plistUtil.writeValue(PlistProperty.ACCESS_TOKEN, res.accessToken());
                        plistUtil.writeValue(PlistProperty.REFRESH_TOKEN, res.refreshToken());
                    });
                    return response != null;
                });
    }

}
