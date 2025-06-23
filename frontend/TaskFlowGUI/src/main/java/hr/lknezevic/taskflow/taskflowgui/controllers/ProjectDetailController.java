package hr.lknezevic.taskflow.taskflowgui.controllers;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.components.TaskListCell;
import hr.lknezevic.taskflow.taskflowgui.enums.ProjectPriority;
import hr.lknezevic.taskflow.taskflowgui.enums.SceneType;
import hr.lknezevic.taskflow.taskflowgui.enums.TaskStatus;
import hr.lknezevic.taskflow.taskflowgui.factory.alert.AlertFactory;
import hr.lknezevic.taskflow.taskflowgui.observable.ProjectFx;
import hr.lknezevic.taskflow.taskflowgui.observable.TaskFx;
import hr.lknezevic.taskflow.taskflowgui.viewmodel.ProjectViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ProjectDetailController extends BaseController implements Initializable {
    @FXML private VBox projectDetail;
    @FXML private Label titleLabel;
    @FXML private TextArea descriptionTextArea;
    @FXML private ComboBox<ProjectPriority> priorityComboBox;
    @FXML private Label createdAtLabel;
    @FXML private Label deadlineLabel;
    @FXML private Label taskCountLabel;
    @FXML private ListView<TaskFx> taskListView;
    @FXML private BarChart<String, Number> statusChart;
    @FXML private Button generatePdfButton;

    private final ProjectViewModel projectViewModel;

    @Inject
    protected ProjectDetailController(AlertFactory alertFactory, ProjectViewModel projectViewModel) {
        super(alertFactory);
        this.projectViewModel = projectViewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        i18n(resources, projectDetail);
        projectViewModel.loadTempProject();

        projectViewModel.tempProjectProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                bindToProject(newVal);
            }
        });
    }

    private void bindToProject(ProjectFx project) {
        titleLabel.textProperty().bind(project.titleProperty());
        descriptionTextArea.setEditable(false);
        descriptionTextArea.textProperty().bind(project.descriptionProperty());
        priorityComboBox.setItems(FXCollections.observableArrayList(ProjectPriority.values()));
        priorityComboBox.valueProperty().bindBidirectional(project.priorityProperty());
        createdAtLabel.setText(project.getCreatedAt().toString());
        deadlineLabel.setText(project.getDeadline().toString());

        ObservableList<TaskFx> tasks = FXCollections.observableArrayList(project.getTasks());
        taskListView.setItems(tasks);
        taskListView.setCellFactory(list -> new TaskListCell());
        taskListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                TaskFx selectedTask = taskListView.getSelectionModel().getSelectedItem();
                projectViewModel.saveTaskToTemp(selectedTask);
                switchScene(SceneType.TASK_DETAIL);
            }
        });

        taskCountLabel.setText(String.valueOf(tasks.size()));

        statusChart.setData(generateStatusChartData(tasks));
        statusChart.getXAxis().setTickLabelRotation(45);
        statusChart.setLegendSide(Side.RIGHT);

        generatePdfButton.setOnAction(e -> projectViewModel.generatePdf(project));
    }

    private ObservableList<XYChart.Series<String, Number>> generateStatusChartData(List<TaskFx> tasks) {
        Map<TaskStatus, Integer> statusCount = new HashMap<>();

        for (TaskStatus status : TaskStatus.values()) {
            statusCount.put(status, 0);
        }

        for (TaskFx task : tasks) {
            TaskStatus status = task.getStatus();
            statusCount.put(status, statusCount.get(status) + 1);
        }

        ObservableList<XYChart.Series<String, Number>> chartData = FXCollections.observableArrayList();

        for (TaskStatus status : TaskStatus.values()) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(status.name());
            int count = statusCount.getOrDefault(status, 0);
            series.getData().add(new XYChart.Data<>(status.name(), count));
            chartData.add(series);
        }

        return chartData;
    }

}
