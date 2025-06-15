package hr.lknezevic.taskflow.taskflowgui.factory.alert;

import javafx.scene.control.Alert;

public class DefaultAlertFactory implements AlertFactory {

    @Override
    public Alert createErrorAlert(String header, String content) {
        return createAlert(Alert.AlertType.ERROR, header, content);
    }

    @Override
    public Alert createInfoAlert(String header, String content) {
        return createAlert(Alert.AlertType.INFORMATION, header, content);
    }

    @Override
    public Alert createConfirmationAlert(String header, String content) {
        return createAlert(Alert.AlertType.CONFIRMATION, header, content);
    }

    @Override
    public Alert createWarningAlert(String header, String content) {
        return createAlert(Alert.AlertType.WARNING, header, content);
    }

    private Alert createAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(type.name());
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }
}
