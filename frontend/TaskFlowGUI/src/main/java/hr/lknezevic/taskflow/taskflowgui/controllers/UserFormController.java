package hr.lknezevic.taskflow.taskflowgui.controllers;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.enums.SceneType;
import hr.lknezevic.taskflow.taskflowgui.factory.alert.AlertFactory;
import hr.lknezevic.taskflow.taskflowgui.managers.SceneManager;
import hr.lknezevic.taskflow.taskflowgui.observable.UserFx;
import hr.lknezevic.taskflow.taskflowgui.process.PhoneValidatorRunner;
import hr.lknezevic.taskflow.taskflowgui.viewmodel.UserViewModel;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UserFormController extends BaseController implements Initializable {
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<String> countryComboBox;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private ComboBox<String> departmentComboBox;

    @FXML
    private ImageView profileImageView;

    @FXML
    private Button uploadImageButton;

    @FXML
    private Button submitButton;

    private final UserViewModel userViewModel;
    private final UserFx userFx;

    @Inject
    protected UserFormController(AlertFactory alertFactory, UserViewModel userViewModel) {
        super(alertFactory);
        this.userViewModel = userViewModel;
        userFx = new UserFx();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindUserFx();
        uploadImageButton.setOnAction(this::selectImage);
        submitButton.setOnAction(this::submit);

        userViewModel.savedUserProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                alertFactory.createConfirmationAlert(
                        "User successfully saved!",
                        "User successfully saved!"
                ).showAndWait();
                SceneManager.switchScene(SceneType.USER_FORM);
            } else {
                alertFactory.createWarningAlert(
                        "User not saved!",
                        "User not saved!"
                ).showAndWait();
            }
        });
    }

    private void bindUserFx() {
        firstNameField.textProperty().bindBidirectional(userFx.firstNameProperty());
        lastNameField.textProperty().bindBidirectional(userFx.lastNameProperty());
        emailField.textProperty().bindBidirectional(userFx.emailProperty());
        countryComboBox.valueProperty().bindBidirectional(userFx.countryProperty());
        phoneNumberField.textProperty().bindBidirectional(userFx.phoneNumberProperty());
        usernameField.textProperty().bindBidirectional(userFx.usernameProperty());
        passwordField.textProperty().bindBidirectional(userFx.passwordProperty());
        roleComboBox.valueProperty().bindBidirectional(userFx.roleProperty());
        departmentComboBox.valueProperty().bindBidirectional(userFx.departmentProperty());
        profileImageView.imageProperty().bindBidirectional(userFx.profileImageProperty());
    }

    private void selectImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Supported image formats","*.jpg", "*.jpeg", "*.png"));
        File file = fileChooser.showOpenDialog(uploadImageButton.getScene().getWindow());
        if (file != null) {
            System.out.println(file.getAbsolutePath());
            Image image = new Image(file.toURI().toString());
            profileImageView.setImage(image);
        }
    }

    private void submit(ActionEvent event) {
        if (super.isEmpty(firstNameField, lastNameField, emailField, usernameField, passwordField)) {
            Platform.runLater(() -> alertFactory
                    .createWarningAlert("Fields are empty!", "Please fill in all fields").showAndWait());
            return;
        }

        if (profileImageView.getImage() == null) {
            Platform.runLater(() -> alertFactory
                    .createWarningAlert("Image is empty!", "Please upload a profile image").showAndWait());
            return;
        }

        String phoneNumber = phoneNumberField.getText();
        submitButton.setDisable(true);

        Task<Boolean> validationTask = getValidationTask(phoneNumber);
        new Thread(validationTask).start();


//        userViewModel.saveUser(userFx,
//                savedUser -> Platform.runLater(() -> {
//                    alertFactory.createConfirmationAlert("User successfully saved!", "User successfully saved!").showAndWait();
//                    SceneManager.switchScene(SceneType.USER_FORM);
//                }),
//                ignored -> Platform.runLater(() ->
//                        alertFactory.createWarningAlert("User not saved!", "User not saved!").showAndWait())
//        );
    }

    private Task<Boolean> getValidationTask(String phoneNumber) {
        Task<Boolean> validationTask = new Task<>() {
            @Override
            protected Boolean call() {
                System.out.println("Running validation " + Thread.currentThread().getName());
                PhoneValidatorRunner phoneValidatorRunner = new PhoneValidatorRunner();
                return phoneValidatorRunner.run(phoneNumber, "+385");
            }
        };

        validationTask.setOnSucceeded(e -> {
            submitButton.setDisable(false);

            boolean valid = validationTask.getValue();
            if (valid) {
                userViewModel.saveUser(userFx);
            } else {
                alertFactory.createWarningAlert(
                        "Invalid phone number",
                        "Please enter a valid phone number."
                ).showAndWait();
            }
        });
        return validationTask;
    }

}
