package hr.lknezevic.taskflow.taskflowgui;

import com.google.inject.Guice;
import com.google.inject.Injector;
import hr.lknezevic.taskflow.taskflowgui.config.guice.AppModule;
import hr.lknezevic.taskflow.taskflowgui.enums.SceneType;
import hr.lknezevic.taskflow.taskflowgui.managers.SceneManager;
import hr.lknezevic.taskflow.taskflowgui.model.UserSidebarData;
import hr.lknezevic.taskflow.taskflowgui.utils.XmlUtil;
import jakarta.xml.bind.JAXBException;
import javafx.application.Application;
import javafx.stage.Stage;

public class TaskFlow extends Application {
    @Override
    public void start(Stage stage) {
        XmlUtil xmlUtil = new XmlUtil();
        UserSidebarData userSidebarData = new UserSidebarData();
        userSidebarData.setFirstName("Leon");
        userSidebarData.setLastName("Knežević");
        userSidebarData.setEmail("leon.knezevic@gmail.com");
        try {
            xmlUtil.writeUserSidebarData(userSidebarData);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        Injector injector = Guice.createInjector(new AppModule());

        // SceneManager je singleton, ali sada se injecta pa static setuje sam sebe
        SceneManager sceneManager = injector.getInstance(SceneManager.class);
        sceneManager.init(stage);
        SceneManager.switchScene(SceneType.DASHBOARD);
    }

    public static void main(String[] args) {
        launch();
    }
}