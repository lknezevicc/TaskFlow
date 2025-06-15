package hr.lknezevic.taskflow.taskflowgui.config.guice;

import com.google.inject.Inject;
import com.google.inject.Injector;
import hr.lknezevic.taskflow.taskflowgui.enums.PlistProperty;
import hr.lknezevic.taskflow.taskflowgui.utils.PlistUtil;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class GuiceFXMLLoader {
    private final Injector injector;
    private final PlistUtil plistUtil;

    @Inject
    public GuiceFXMLLoader(Injector injector, PlistUtil plistUtil) {
        this.injector = injector;
        this.plistUtil = plistUtil;
    }

    public <T> T load(URL resource) {
        FXMLLoader loader = new FXMLLoader(resource);
        loader.setControllerFactory(injector::getInstance);

        String language = plistUtil.readValue(PlistProperty.LANGUAGE);
        Locale locale = Locale.forLanguageTag(language);
        loader.setResources(ResourceBundle.getBundle("hr.lknezevic.taskflow.taskflowgui.languages", locale));

        try {
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML", e);
        }
    }
}
