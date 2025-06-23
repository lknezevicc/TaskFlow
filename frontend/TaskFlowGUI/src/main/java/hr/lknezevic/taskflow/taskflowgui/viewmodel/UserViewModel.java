package hr.lknezevic.taskflow.taskflowgui.viewmodel;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.dto.UserDto;
import hr.lknezevic.taskflow.taskflowgui.mappers.UserMapper;
import hr.lknezevic.taskflow.taskflowgui.observable.UserFx;
import hr.lknezevic.taskflow.taskflowgui.services.UserService;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.function.Consumer;

public class UserViewModel {
    private final UserService userService;
    private final UserMapper userMapper;

    private final ObservableList<UserFx> users = FXCollections.observableArrayList();
    private final ObjectProperty<UserFx> currentUser = new SimpleObjectProperty<>();
    private final ObjectProperty<UserFx> savedUser = new SimpleObjectProperty<>();

    @Inject
    public UserViewModel(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public void loadUsers() {
    }

    public void loadCurrentUser() {
        userService.getCurrentUser().thenAccept(user ->
                Platform.runLater(() -> currentUser.set(userMapper.toFx(user))));
    }

    public ObjectProperty<UserFx> currentUserProperty() {
        return currentUser;
    }

    public ObjectProperty<UserFx> savedUserProperty() {
        return savedUser;
    }

    public void saveUser(UserFx fx) {
        UserDto userToSave = userMapper.toDto(fx);

        userService.saveUser(userToSave).thenAccept(response -> {
            if (response != null) {
                UserFx userFx = userMapper.toFx(response);
                Platform.runLater(() -> {
                    users.add(userFx);
                    savedUser.set(userFx);
                });
            } else {
                Platform.runLater(() -> savedUser.set(null));
            }
        });
    }

    public void updateUser(Consumer<UserFx> consumer) {

    }
}
