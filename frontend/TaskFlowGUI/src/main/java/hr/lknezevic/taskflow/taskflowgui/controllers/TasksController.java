package hr.lknezevic.taskflow.taskflowgui.controllers;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.components.TaskListCell;
import hr.lknezevic.taskflow.taskflowgui.enums.SceneType;
import hr.lknezevic.taskflow.taskflowgui.factory.alert.AlertFactory;
import hr.lknezevic.taskflow.taskflowgui.observable.TaskFx;
import hr.lknezevic.taskflow.taskflowgui.viewmodel.TaskViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TasksController extends BaseController implements Initializable {
    @FXML private VBox tasks;
    @FXML private TextField searchTextField;
    @FXML private ComboBox<String> priorityComboBox;
    @FXML private ComboBox<String> statusComboBox;
    @FXML private ListView<TaskFx> tasksListView;

    private final TaskViewModel taskViewModel;

    @Inject
    protected TasksController(AlertFactory alertFactory, TaskViewModel taskViewModel) {
        super(alertFactory);
        this.taskViewModel = taskViewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        i18n(resources, tasks);
        taskViewModel.loadTasksAsyncSimple();
        tasksListView.setCellFactory(listView -> new TaskListCell());
        tasksListView.setItems(taskViewModel.getFilteredTasks());

        // Popuni ComboBoxe
        priorityComboBox.getItems().addAll("ALL", "LOW", "MEDIUM", "HIGH");
        priorityComboBox.getSelectionModel().select("ALL");

        statusComboBox.getItems().addAll("ALL", "TODO", "IN_PROGRESS", "COMPLETED");
        statusComboBox.getSelectionModel().select("ALL");

        // Listeners za filtraciju
        searchTextField.textProperty().addListener((obs, oldVal, newVal) -> applyFilters());
        priorityComboBox.valueProperty().addListener((obs, oldVal, newVal) -> applyFilters());
        statusComboBox.valueProperty().addListener((obs, oldVal, newVal) -> applyFilters());

        tasksListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                TaskFx selectedTask = tasksListView.getSelectionModel().getSelectedItem();
                taskViewModel.saveToTemp(selectedTask);
                switchScene(SceneType.PREFERENCES);
            }
        });
    }

    private void applyFilters() {
        String search = searchTextField.getText();
        String priority = priorityComboBox.getValue();
        String status = statusComboBox.getValue();
        taskViewModel.applyFilter(search, priority, status);
    }



}
