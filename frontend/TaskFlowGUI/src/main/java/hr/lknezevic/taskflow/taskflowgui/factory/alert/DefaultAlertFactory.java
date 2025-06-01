package hr.lknezevic.taskflow.taskflowgui.factory.alert;

import javafx.scene.control.Alert;

public class DefaultAlertFactory implements AlertFactory {

    @Override
    public Alert createErrorAlert(String title, String header, String content) {
        return createAlert(Alert.AlertType.ERROR, title, header, content);
    }

    @Override
    public Alert createInfoAlert(String title, String header, String content) {
        return createAlert(Alert.AlertType.INFORMATION, title, header, content);
    }

    @Override
    public Alert createConfirmationAlert(String title, String header, String content) {
        return createAlert(Alert.AlertType.CONFIRMATION, title, header, content);
    }

    @Override
    public Alert createWarningAlert(String title, String header, String content) {
        return createAlert(Alert.AlertType.WARNING, title, header, content);
    }

    private Alert createAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }
}
