package hr.lknezevic.taskflow.taskflowgui.factory.alert;

import javafx.scene.control.Alert;

public interface AlertFactory {
    Alert createErrorAlert(String header, String content);
    Alert createInfoAlert(String header, String content);
    Alert createConfirmationAlert(String header, String content);
    Alert createWarningAlert(String header, String content);
}
