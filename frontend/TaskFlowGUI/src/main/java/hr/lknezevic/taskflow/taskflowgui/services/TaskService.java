package hr.lknezevic.taskflow.taskflowgui.services;

import hr.lknezevic.taskflow.taskflowgui.dto.TaskDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TaskService {
    CompletableFuture<List<TaskDto>> findAllTasks();
    CompletableFuture<List<TaskDto>> findTasksByProjectId(Long projectId);
    void saveToTemp(TaskDto taskDto);
}
