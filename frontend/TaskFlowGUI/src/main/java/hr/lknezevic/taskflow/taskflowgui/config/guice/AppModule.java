package hr.lknezevic.taskflow.taskflowgui.config.guice;

import com.google.inject.AbstractModule;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ServiceModule());
        install(new ControllerModule());
        install(new ViewModelModule());
        bind(ExecutorService.class).toInstance(Executors.newFixedThreadPool(4));
    }
}
