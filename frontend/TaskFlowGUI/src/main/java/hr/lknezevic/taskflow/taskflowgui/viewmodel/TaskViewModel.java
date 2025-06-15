package hr.lknezevic.taskflow.taskflowgui.viewmodel;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.mappers.TaskMapper;
import hr.lknezevic.taskflow.taskflowgui.observable.TaskFx;
import hr.lknezevic.taskflow.taskflowgui.services.TaskService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.function.Consumer;

public class TaskViewModel {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Inject
    public TaskViewModel(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    public void loadTasksSimple(Consumer<ObservableList<TaskFx>> onTasksLoaded) {
        taskService.findAllTasks().thenAccept(tasks -> {

            List<TaskFx> taskFxList = tasks.stream()
                    .map(task -> taskMapper.toFx(task, null, null, null, null))
                    .toList();

            ObservableList<TaskFx> taskFxObservableList = FXCollections.observableList(taskFxList);
            onTasksLoaded.accept(taskFxObservableList);
        });
    }
}
