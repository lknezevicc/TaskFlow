package hr.lknezevic.taskflow.taskflowgui.config.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import hr.lknezevic.taskflow.taskflowgui.controllers.LogInController;
import hr.lknezevic.taskflow.taskflowgui.controllers.SidebarController;
import hr.lknezevic.taskflow.taskflowgui.factory.alert.AlertFactory;
import hr.lknezevic.taskflow.taskflowgui.factory.alert.DefaultAlertFactory;

public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AlertFactory.class).to(DefaultAlertFactory.class).in(Singleton.class);
        bind(LogInController.class);
        bind(SidebarController.class);
    }
}
