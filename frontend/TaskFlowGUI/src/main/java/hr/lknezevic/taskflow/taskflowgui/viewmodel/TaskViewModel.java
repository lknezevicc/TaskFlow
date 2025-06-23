package hr.lknezevic.taskflow.taskflowgui.viewmodel;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.mappers.TaskMapper;
import hr.lknezevic.taskflow.taskflowgui.observable.TaskFx;
import hr.lknezevic.taskflow.taskflowgui.services.TaskService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.List;

public class TaskViewModel {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    private final ObservableList<TaskFx> allTasks = FXCollections.observableArrayList();
    private final FilteredList<TaskFx> filteredTasks = new FilteredList<>(allTasks);


    @Inject
    public TaskViewModel(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    public ObservableList<TaskFx> getAllTasks() {
        return allTasks;
    }

    public FilteredList<TaskFx> getFilteredTasks() {
        return filteredTasks;
    }

    public void loadTasksAsyncSimple() {
        taskService.findAllTasks().thenAccept(tasks -> {

            List<TaskFx> taskFxList = tasks.stream()
                    .map(task -> taskMapper.toFx(task, null, null, null, null))
                    .toList();

            Platform.runLater(() -> allTasks.setAll(taskFxList));
        });
    }

    public void applyFilter(String search, String priority, String status) {
        filteredTasks.setPredicate(task -> {
            boolean matchesSearch = search == null || search.isBlank()
                    || task.getTitle().toLowerCase().contains(search.toLowerCase())
                    || task.getDescription().toLowerCase().contains(search.toLowerCase());

            boolean matchesPriority = priority == null || priority.equals("ALL")
                    || task.getPriority().name().equalsIgnoreCase(priority);

            boolean matchesStatus = status == null || status.equals("ALL")
                    || task.getStatus().name().equalsIgnoreCase(status);

            return matchesSearch && matchesPriority && matchesStatus;
        });
    }

    public void saveToTemp(TaskFx task) {
        taskService.saveToTemp(taskMapper.toDto(task));
    }
}
