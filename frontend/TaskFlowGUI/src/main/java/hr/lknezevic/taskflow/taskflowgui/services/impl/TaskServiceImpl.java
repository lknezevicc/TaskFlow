package hr.lknezevic.taskflow.taskflowgui.services.impl;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.dto.TaskDto;
import hr.lknezevic.taskflow.taskflowgui.services.BaseService;
import hr.lknezevic.taskflow.taskflowgui.services.TaskService;
import hr.lknezevic.taskflow.taskflowgui.utils.HttpRequestModifier;
import hr.lknezevic.taskflow.taskflowgui.utils.JsonUtil;
import hr.lknezevic.taskflow.taskflowgui.utils.SerializationUtil;

import java.net.http.HttpClient;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TaskServiceImpl extends BaseService implements TaskService {
    private final SerializationUtil serializationUtil;

    @Inject
    public TaskServiceImpl(HttpClient httpClient, JsonUtil jsonUtil, HttpRequestModifier httpRequestModifier, SerializationUtil serializationUtil) {
        super(httpClient, jsonUtil, httpRequestModifier);
        this.serializationUtil = serializationUtil;
    }

    @Override
    public CompletableFuture<List<TaskDto>> findAllTasks() {
        return getList("http://localhost:3000/tasks", TaskDto.class);
    }

    @Override
    public CompletableFuture<List<TaskDto>> findTasksByProjectId(Long projectId) {
        return getList("http://localhost:3000/tasks?projectId=" + projectId, TaskDto.class);
    }

    @Override
    public void saveToTemp(TaskDto taskDto) {
        serializationUtil.saveTask(taskDto);
    }


}
