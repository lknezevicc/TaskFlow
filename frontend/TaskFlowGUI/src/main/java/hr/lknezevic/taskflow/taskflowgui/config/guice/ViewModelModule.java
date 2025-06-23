package hr.lknezevic.taskflow.taskflowgui.config.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import hr.lknezevic.taskflow.taskflowgui.managers.UdpClientManager;
import hr.lknezevic.taskflow.taskflowgui.mappers.*;
import hr.lknezevic.taskflow.taskflowgui.viewmodel.PreferencesViewModel;
import hr.lknezevic.taskflow.taskflowgui.viewmodel.TaskViewModel;
import hr.lknezevic.taskflow.taskflowgui.viewmodel.UserViewModel;
import org.mapstruct.factory.Mappers;

public class ViewModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserViewModel.class);
        bind(TaskViewModel.class);
        bind(PreferencesViewModel.class);
        bind(UdpClientManager.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    public UserMapper provideUserMapper() {
        return Mappers.getMapper(UserMapper.class);
    }

    @Provides
    @Singleton
    public TaskMapper provideTaskMapper() {
        return Mappers.getMapper(TaskMapper.class);
    }

    @Provides
    @Singleton
    public CommentMapper provideCommentMapper() {
        return Mappers.getMapper(CommentMapper.class);
    }

    @Provides
    @Singleton
    public ProjectMapper provideProjectMapper() {
        return Mappers.getMapper(ProjectMapper.class);
    }

    @Provides
    @Singleton
    public TimeLogMapper provideTimeLogMapper() {
        return Mappers.getMapper(TimeLogMapper.class);
    }

}
