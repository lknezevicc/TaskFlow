package hr.lknezevic.taskflow.taskflowgui.factory.alert;

import javafx.scene.control.Alert;

public interface AlertFactory {
    Alert createErrorAlert(String title, String header, String content);
    Alert createInfoAlert(String title, String header, String content);
    Alert createConfirmationAlert(String title, String header, String content);
    Alert createWarningAlert(String title, String header, String content);
}
