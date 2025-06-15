package hr.lknezevic.taskflow.taskflowgui.services;

import hr.lknezevic.taskflow.taskflowgui.utils.HttpRequestModifier;
import hr.lknezevic.taskflow.taskflowgui.utils.JsonUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class BaseService {
    protected final HttpClient httpClient;
    protected final JsonUtil jsonUtil;
    protected final HttpRequestModifier httpRequestModifier;

    public BaseService(HttpClient httpClient, JsonUtil jsonUtil, HttpRequestModifier httpRequestModifier) {
        this.httpClient = httpClient;
        this.jsonUtil = jsonUtil;
        this.httpRequestModifier = httpRequestModifier;
    }

    protected <T> CompletableFuture<T> get(String url, Class<T> responseType) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();

        HttpRequest request = addAuthHeader(builder, url);

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> jsonUtil.fromJson(response.body(), responseType));
    }

    protected <T> CompletableFuture<List<T>> getList(String url, Class<T> clazz) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();

        HttpRequest request = addAuthHeader(builder, url);

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    if (response.statusCode() == 200) {
                        return jsonUtil.fromJsonList(response.body(), clazz);
                    } else {
                        return List.of();
                    }
                });
    }

    protected <T, R> CompletableFuture<R> post(String url, T body, Class<R> responseType) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonUtil.toJson(body)));

        HttpRequest request = addAuthHeader(builder, url);

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> jsonUtil.fromJson(response.body(), responseType));
    }

    protected <T, R> CompletableFuture<R> put(String url, T body, Class<R> responseType) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonUtil.toJson(body)));

        HttpRequest request = addAuthHeader(builder, url);

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> jsonUtil.fromJson(response.body(), responseType));
    }

    protected CompletableFuture<Void> delete(String url) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE();

        HttpRequest request = addAuthHeader(builder, url);

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> null);
    }

    private HttpRequest addAuthHeader(HttpRequest.Builder builder, String url) {
        return httpRequestModifier.applyDefaults(builder, url).build();
    }

}
