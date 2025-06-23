package hr.lknezevic.taskflow.taskflowgui.controllers;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.enums.SceneType;
import hr.lknezevic.taskflow.taskflowgui.factory.alert.AlertFactory;
import hr.lknezevic.taskflow.taskflowgui.services.SidebarService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SidebarController extends BaseController implements Initializable {
    @FXML private VBox sidebar;
    @FXML private ImageView profileImage;
    @FXML private Label fullName;
    @FXML private Label email;
    @FXML private Label department;
    @FXML private Button dashboard;
    @FXML private Button tasks;
    @FXML private Button projects;
    @FXML private Button administrations;
    @FXML private Button preferences;
    @FXML private Button logout;

    private final SidebarService sidebarService;

    @Inject
    public SidebarController(AlertFactory alertFactory, SidebarService sidebarService) {
        super(alertFactory);
        this.sidebarService = sidebarService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        i18n(resources, sidebar);
        sidebarService.loadSidebarData(fullName, email);
        dashboard.setOnAction(this::dashboardAction);
        tasks.setOnAction(this::tasksAction);
        projects.setOnAction(this::projectsAction);
        administrations.setOnAction(this::administrationsAction);
        preferences.setOnAction(this::preferencesAction);
    }

    private void dashboardAction(ActionEvent event) {
        switchScene(SceneType.DASHBOARD);
    }

    private void tasksAction(ActionEvent event) {
        switchScene(SceneType.TASKS);
    }

    private void projectsAction(ActionEvent event) {
        switchScene(SceneType.PROJECTS);
    }

    private void administrationsAction(ActionEvent event) {
        switchScene(SceneType.ADMINISTRATION);
    }

    private void preferencesAction(ActionEvent event) {
        switchScene(SceneType.PREFERENCES);
    }

}
