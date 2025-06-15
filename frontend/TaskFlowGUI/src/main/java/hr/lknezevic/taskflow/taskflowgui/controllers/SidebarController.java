package hr.lknezevic.taskflow.taskflowgui.controllers;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.factory.alert.AlertFactory;
import hr.lknezevic.taskflow.taskflowgui.services.SidebarService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class SidebarController extends BaseController implements Initializable {
    private AlertFactory alertFactory;
    private final SidebarService sidebarService;

    @FXML private ImageView profileImage;
    @FXML private Label fullName;
    @FXML private Label email;
    @FXML private Label department;
    @FXML private Button dashboard;
    @FXML private Button tasks;
    @FXML private Button projects;
    @FXML private Button administrations;
    @FXML private Button notifications;
    @FXML private Button requests;
    @FXML private Button preferences;
    @FXML private Button logout;

    @Inject
    public SidebarController(AlertFactory alertFactory, SidebarService sidebarService) {
        super(alertFactory);
        this.sidebarService = sidebarService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebarService.loadSidebarData(fullName, email);
    }
}
