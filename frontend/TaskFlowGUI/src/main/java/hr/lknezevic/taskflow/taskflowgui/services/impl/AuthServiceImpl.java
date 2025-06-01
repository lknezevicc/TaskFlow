package hr.lknezevic.taskflow.taskflowgui.services.impl;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.utils.PlistUtil;
import hr.lknezevic.taskflow.taskflowgui.dto.LoginRequestDto;
import hr.lknezevic.taskflow.taskflowgui.dto.LoginResponseDto;
import hr.lknezevic.taskflow.taskflowgui.enums.PlistProperty;
import hr.lknezevic.taskflow.taskflowgui.services.AuthService;
import hr.lknezevic.taskflow.taskflowgui.utils.JsonUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class AuthServiceImpl implements AuthService {
    private final HttpClient httpClient;
    private final JsonUtil jsonUtil;
    private final PlistUtil plistUtil;

    @Inject
    public AuthServiceImpl(HttpClient httpClient, JsonUtil jsonUtil, PlistUtil plistUtil) {
        this.httpClient = httpClient;
        this.jsonUtil = jsonUtil;
        this.plistUtil = plistUtil;
    }

    public CompletableFuture<Boolean> authenticate(LoginRequestDto loginRequest) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/auth/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonUtil.toJson(loginRequest)))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    if (response.statusCode() == 200) {
                        LoginResponseDto responseDto = jsonUtil.fromJson(response.body(), LoginResponseDto.class);
                        plistUtil.writeValue(PlistProperty.ACCESS_TOKEN, responseDto.accessToken());
                        plistUtil.writeValue(PlistProperty.REFRESH_TOKEN, responseDto.refreshToken());
                        return true;
                    } else {
                        return false;
                    }
                });
    }


}
