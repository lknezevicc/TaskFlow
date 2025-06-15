package hr.lknezevic.taskflow.taskflowgui.viewmodel;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.mappers.UserMapper;
import hr.lknezevic.taskflow.taskflowgui.observable.UserFx;
import hr.lknezevic.taskflow.taskflowgui.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.function.Consumer;

public class UserViewModel {
    private final UserService userService;
    private final UserMapper userMapper;

    private final ObservableList<UserFx> users = FXCollections.observableArrayList();
    private UserFx currentUser;

    @Inject
    public UserViewModel(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public void loadUsers(Consumer<List<UserFx>> consumer) {
        userService.getUsers().thenAccept(usersDto -> {
            users.clear();
            List<UserFx> usersFx = userMapper.toFxList(usersDto);
            users.addAll(usersFx);
            consumer.accept(users);
        });
    }

    public void loadCurrentUser(Consumer<UserFx> consumer) {
        userService.getCurrentUser().thenAccept(user -> {
            UserFx userFx = userMapper.toFx(user);
            currentUser = userFx;
            consumer.accept(userFx);
        });
    }

    public UserFx getCurrentUser() {
        return currentUser;
    }
}
