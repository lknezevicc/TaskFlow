package hr.lknezevic.taskflow.taskflowgui.controllers;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.components.TaskListCell;
import hr.lknezevic.taskflow.taskflowgui.enums.TaskStatus;
import hr.lknezevic.taskflow.taskflowgui.observable.TaskFx;
import hr.lknezevic.taskflow.taskflowgui.viewmodel.TaskViewModel;
import hr.lknezevic.taskflow.taskflowgui.viewmodel.UserViewModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.text.DateFormat;
import java.util.*;

public class DashboardController implements Initializable {
    private final UserViewModel userViewModel;
    private final TaskViewModel taskViewModel;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private ListView<TaskFx> userTasksListView;

    @FXML
    private ComboBox<String> projectComboBox;

    @FXML
    private PieChart taskStatusPieChart;

    @Inject
    public DashboardController(UserViewModel userViewModel, TaskViewModel taskViewModel) {
        this.userViewModel = userViewModel;
        this.taskViewModel = taskViewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurePieChart();

        userViewModel.loadCurrentUser(user -> Platform.runLater(() ->
                welcomeLabel.setText("Welcome back " + user.getFirstName()))
        );

        dateLabel.setText(DateFormat.getDateInstance().format(new Date()));
        userTasksListView.setCellFactory(listView -> new TaskListCell());

        taskViewModel.loadTasksSimple(tasks -> Platform.runLater(() -> {
            userTasksListView.setItems(tasks);
            taskStatusPieChart.setData(calculatePieChartData(tasks));
        }));

    }

    private ObservableList<PieChart.Data> calculatePieChartData(ObservableList<TaskFx> userTasks) {
        Map<TaskStatus, Integer> statusCount = new HashMap<>();

        for (TaskStatus status : TaskStatus.values()) {
            statusCount.put(status, 0);
        }

        for (TaskFx task : userTasks) {
            TaskStatus status = task.getStatus();
            statusCount.put(status, statusCount.get(status) + 1);
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<TaskStatus, Integer> entry : statusCount.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey().name(), entry.getValue().doubleValue()));
        }

        Locale locale = new Locale("hr", "HR");
        System.out.println("Država (na engleskom): " + locale.getDisplayCountry(Locale.ENGLISH));

        return pieChartData;
    }

    private void configurePieChart() {
        taskStatusPieChart.setLabelLineLength(10);
        taskStatusPieChart.setLegendVisible(true);
        taskStatusPieChart.setLegendSide(Side.LEFT);
    }

}
