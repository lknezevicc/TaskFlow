package hr.lknezevic.taskflow.taskflowgui.managers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import hr.lknezevic.taskflow.taskflowgui.config.SceneConfig;
import hr.lknezevic.taskflow.taskflowgui.config.guice.AppModule;
import hr.lknezevic.taskflow.taskflowgui.config.guice.GuiceFXMLLoader;
import hr.lknezevic.taskflow.taskflowgui.enums.SceneType;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static SceneManager instance;
    private final GuiceFXMLLoader fxmlLoader;
    private Stage stage;

    private SceneManager() {
        Injector injector = Guice.createInjector(new AppModule());
        this.fxmlLoader = new GuiceFXMLLoader(injector);
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }

        return instance;
    }

    public void switchScene(SceneType sceneType) {
        stage.setScene(loadScene(sceneType));
        stage.show();
    }

    public void init(Stage stage) {
        this.stage = stage;
        this.stage.setResizable(false);
        this.stage.setTitle("TaskFlow");
    }

    private Scene loadScene(SceneType sceneType) {
        String scenePath = SceneConfig.getScenePath(sceneType);
        Scene scene = new Scene(fxmlLoader.load(getClass().getResource(scenePath)));
        String cssFile = sceneType.getCssPath();
        scene.getStylesheets().add(cssFile);
        
        return scene;
    }
}
