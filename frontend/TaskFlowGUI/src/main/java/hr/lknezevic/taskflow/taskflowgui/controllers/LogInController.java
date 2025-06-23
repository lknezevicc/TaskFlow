package hr.lknezevic.taskflow.taskflowgui.controllers;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.dto.LoginRequestDto;
import hr.lknezevic.taskflow.taskflowgui.enums.SceneType;
import hr.lknezevic.taskflow.taskflowgui.factory.alert.AlertFactory;
import hr.lknezevic.taskflow.taskflowgui.managers.SceneManager;
import hr.lknezevic.taskflow.taskflowgui.services.AuthService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInController extends BaseController {
    private final AuthService authService;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @Inject
    public LogInController(AlertFactory alertFactory, AuthService authService) {
        super(alertFactory);
        this.authService = authService;
    }

    public void onLogInButtonClick() {
        if (isEmpty(usernameField, passwordField)) {
            alertFactory.createWarningAlert(
                    "Username/password are empty",
                    "Please enter a valid username/password"
            ).showAndWait();
            return;
        }

        LoginRequestDto loginRequest = new LoginRequestDto(usernameField.getText(), passwordField.getText());

        authService.authenticate(loginRequest).thenAccept(isSuccess -> {
            if (isSuccess) {
                Platform.runLater(() -> SceneManager.switchScene(SceneType.MAIN));
            } else {
                Platform.runLater(() ->
                    alertFactory.createWarningAlert(
                            "Login failed",
                            "Please try again"
                    ).showAndWait()
                );
            }
        });
    }
}
