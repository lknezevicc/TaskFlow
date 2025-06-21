package hr.lknezevic.taskflow.taskflowgui.controllers;

import hr.lknezevic.taskflow.taskflowgui.enums.SceneType;
import hr.lknezevic.taskflow.taskflowgui.factory.alert.AlertFactory;
import hr.lknezevic.taskflow.taskflowgui.managers.SceneManager;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextInputControl;

import java.util.ResourceBundle;

public abstract class BaseController {
    protected final AlertFactory alertFactory;

    protected BaseController(AlertFactory alertFactory) {
        this.alertFactory = alertFactory;
    }

    protected boolean isEmpty(String... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) return true;
        }
        return false;
    }

    protected void switchScene(SceneType sceneType) {
        Platform.runLater(() -> SceneManager.switchScene(sceneType));
    }

    protected void i18n(ResourceBundle i18n, Node... nodes) {
        for (Node node : nodes) {
            String key = node.getUserData().toString();
            String translation = i18n.getString(key);

            if (node instanceof Labeled labeled) {
                labeled.setText(translation);
            } else if (node instanceof TextInputControl) {
            }

        }
    }
}
