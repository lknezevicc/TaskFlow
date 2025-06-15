package hr.lknezevic.taskflow.taskflowgui.managers;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.config.SceneConfig;
import hr.lknezevic.taskflow.taskflowgui.config.guice.GuiceFXMLLoader;
import hr.lknezevic.taskflow.taskflowgui.enums.SceneType;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static SceneManager instance;

    private final GuiceFXMLLoader fxmlLoader;
    private Stage stage;

    @Inject
    public SceneManager(GuiceFXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
        instance = this;
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("SceneManager not initialized via DI.");
        }
        return instance;
    }

    public static void switchScene(SceneType sceneType) {
        getInstance().switchTo(sceneType);
    }

    public void init(Stage stage) {
        this.stage = stage;
        this.stage.setResizable(false);
        this.stage.setTitle("TaskFlow");
    }

    private void switchTo(SceneType sceneType) {
        stage.setScene(loadScene(sceneType));
        stage.show();
    }

    private Scene loadScene(SceneType sceneType) {
        String scenePath = SceneConfig.getScenePath(sceneType);
        Scene scene = new Scene(fxmlLoader.load(getClass().getResource(scenePath)));
        scene.getStylesheets().add(sceneType.getCssPath());
        return scene;
    }
}
