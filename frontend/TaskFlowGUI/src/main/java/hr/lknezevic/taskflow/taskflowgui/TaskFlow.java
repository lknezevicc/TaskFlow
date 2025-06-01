package hr.lknezevic.taskflow.taskflowgui;

import hr.lknezevic.taskflow.taskflowgui.enums.SceneType;
import hr.lknezevic.taskflow.taskflowgui.managers.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskFlow extends Application {
    @Override
    public void start(Stage stage) {
        SceneManager.getInstance().init(stage);
        SceneManager.getInstance().switchScene(SceneType.LOG_IN);
    }

    public static void main(String[] args) {
        launch();
    }
}