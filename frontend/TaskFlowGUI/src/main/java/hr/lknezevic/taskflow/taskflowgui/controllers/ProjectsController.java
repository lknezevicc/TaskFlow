package hr.lknezevic.taskflow.taskflowgui.controllers;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.enums.ProjectPriority;
import hr.lknezevic.taskflow.taskflowgui.enums.SceneType;
import hr.lknezevic.taskflow.taskflowgui.factory.alert.AlertFactory;
import hr.lknezevic.taskflow.taskflowgui.observable.ProjectFx;
import hr.lknezevic.taskflow.taskflowgui.viewmodel.ProjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectsController extends BaseController implements Initializable {
    @FXML private VBox projects;
    @FXML private TextField searchField;
    @FXML private ComboBox<ProjectPriority> statusFilterComboBox;
    @FXML private Button addProjectButton;
    @FXML private ListView<ProjectFx> projectListView;

    private final ProjectViewModel projectViewModel;


    @Inject
    protected ProjectsController(AlertFactory alertFactory, ProjectViewModel projectViewModel) {
        super(alertFactory);
        this.projectViewModel = projectViewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        i18n(resources, projects);
        projectViewModel.loadAllProjects();
        statusFilterComboBox.getItems().addAll(ProjectPriority.values());
        applyListeners();

        projectListView.setItems(projectViewModel.getFilteredProjects());
        addProjectButton.setOnAction(this::projectButtonAction);

        projectListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                ProjectFx selectedProject = projectListView.getSelectionModel().getSelectedItem();
                projectViewModel.saveToTemp(selectedProject);
                switchScene(SceneType.PROJECT_DETAIL);
            }
        });
    }

    private void applyListeners() {
        statusFilterComboBox.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> applyFilters());
        searchField.textProperty().addListener((observable, oldValue, newValue) -> applyFilters());
    }

    private void applyFilters() {
        String search = searchField.getText();
        ProjectPriority selectedProjectPriority = statusFilterComboBox.getSelectionModel().getSelectedItem();

        projectViewModel.applyFilter(search, selectedProjectPriority);
    }

    private void projectButtonAction(ActionEvent actionEvent) {
        switchScene(SceneType.PROJECT_FORM);
    }
}
