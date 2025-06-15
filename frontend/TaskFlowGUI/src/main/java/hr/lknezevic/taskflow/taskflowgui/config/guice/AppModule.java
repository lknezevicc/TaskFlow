package hr.lknezevic.taskflow.taskflowgui.config.guice;

import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ServiceModule());
        install(new ControllerModule());
        install(new ViewModelModule());
    }
}
