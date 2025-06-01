package hr.lknezevic.taskflow.taskflowgui.controllers;

import hr.lknezevic.taskflow.taskflowgui.enums.SceneType;
import hr.lknezevic.taskflow.taskflowgui.factory.alert.AlertFactory;
import hr.lknezevic.taskflow.taskflowgui.managers.SceneManager;
import javafx.application.Platform;

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
        Platform.runLater(() -> SceneManager.getInstance().switchScene(sceneType));
    }
}
