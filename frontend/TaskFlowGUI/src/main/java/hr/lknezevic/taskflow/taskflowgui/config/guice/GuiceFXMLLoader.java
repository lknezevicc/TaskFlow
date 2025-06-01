package hr.lknezevic.taskflow.taskflowgui.config.guice;

import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public class GuiceFXMLLoader {
    private final Injector injector;

    public GuiceFXMLLoader(Injector injector) {
        this.injector = injector;
    }

    public <T> T load(URL resource) {
        FXMLLoader loader = new FXMLLoader(resource);
        loader.setControllerFactory(injector::getInstance);
        try {
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML", e);
        }
    }

}
