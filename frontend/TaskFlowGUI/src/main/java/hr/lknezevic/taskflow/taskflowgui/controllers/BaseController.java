package hr.lknezevic.taskflow.taskflowgui.controllers;

import hr.lknezevic.taskflow.taskflowgui.enums.SceneType;
import hr.lknezevic.taskflow.taskflowgui.factory.alert.AlertFactory;
import hr.lknezevic.taskflow.taskflowgui.managers.SceneManager;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextInputControl;

import java.util.ResourceBundle;

public abstract class BaseController {
    protected final AlertFactory alertFactory;

    protected BaseController(AlertFactory alertFactory) {
        this.alertFactory = alertFactory;
    }

    protected boolean isEmpty(TextInputControl... fields) {
        for (TextInputControl field : fields) {
            if (field.getText() == null || field.getText().trim().isEmpty()) {
                return true;
            }
        }

        return false;
    }

    protected void switchScene(SceneType sceneType) {
        Platform.runLater(() -> SceneManager.switchScene(sceneType));
    }

    protected void i18n(ResourceBundle bundle, Parent root) {
        for (Node node : root.lookupAll("*")) {
            Object key = node.getUserData();
            if (key == null) continue;

            String translation = bundle.getString(key.toString());
            if (node instanceof Labeled labeled) {
                labeled.setText(translation);
            } else if (node instanceof TextInputControl input) {
                input.setPromptText(translation);
            } else if (node instanceof ComboBox<?> comboBox) {
                comboBox.setPromptText(translation);
            }
        }
    }

    protected String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
