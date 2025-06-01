package hr.lknezevic.taskflow.taskflowgui.config;

import hr.lknezevic.taskflow.taskflowgui.enums.SceneType;

import java.io.IOException;
import java.util.Properties;

public class SceneConfig {
    private static final Properties scenes = new Properties();

    static {
        try {
            scenes.load(SceneConfig.class.getResourceAsStream("/scene.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load scenes", e);
        }
    }

    public static String getScenePath(SceneType sceneType) {
        String scenePath = scenes.getProperty(sceneType.name());
        System.out.println("Scene path: " + scenePath);
        if (scenePath == null) {
            throw new IllegalArgumentException("Scene not found: " + sceneType);
        }

        return scenePath;
    }
}
