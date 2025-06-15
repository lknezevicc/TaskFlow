package hr.lknezevic.taskflow.taskflowgui.services.impl;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.dto.TaskDto;
import hr.lknezevic.taskflow.taskflowgui.services.BaseService;
import hr.lknezevic.taskflow.taskflowgui.services.TaskService;
import hr.lknezevic.taskflow.taskflowgui.utils.HttpRequestModifier;
import hr.lknezevic.taskflow.taskflowgui.utils.JsonUtil;

import java.net.http.HttpClient;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TaskServiceImpl extends BaseService implements TaskService {

    @Inject
    public TaskServiceImpl(HttpClient httpClient, JsonUtil jsonUtil, HttpRequestModifier httpRequestModifier) {
        super(httpClient, jsonUtil, httpRequestModifier);
    }

    @Override
    public CompletableFuture<List<TaskDto>> findAllTasks() {
        return getList("http://localhost:3000/tasks", TaskDto.class);
    }
}
