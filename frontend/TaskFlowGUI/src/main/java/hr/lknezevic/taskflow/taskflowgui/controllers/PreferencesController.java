package hr.lknezevic.taskflow.taskflowgui.controllers;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.factory.alert.AlertFactory;
import hr.lknezevic.taskflow.taskflowgui.viewmodel.PreferencesViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PreferencesController extends BaseController implements Initializable {
    @FXML private VBox preferences;
    @FXML private ComboBox<String> languageComboBox;
    @FXML private ComboBox<String> themeComboBox;
    @FXML private CheckBox autoSwitchCheckbox;
    @FXML private Label versionLabel;
    @FXML private Button checkUpdateButton;
    @FXML private ProgressBar downloadProgressBar;
    @FXML private Label downloadProgressLabel;
    @FXML private Button resetPreferencesButton;

    private final PreferencesViewModel preferencesViewModel;
    private boolean suppressListeners = false;

    @Inject
    public PreferencesController(AlertFactory alertFactory, PreferencesViewModel preferencesViewModel) {
        super(alertFactory);
        this.preferencesViewModel = preferencesViewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        i18n(resources, preferences);
        updateUI();
        applyListeners();
        checkUpdateButton.setOnAction(this::download);
        resetPreferencesButton.setOnAction(this::resetPreferences);
    }

    private void downloadVisibility(boolean visible) {
        downloadProgressBar.setVisible(visible);
        downloadProgressLabel.setVisible(visible);
    }

    private void download(ActionEvent actionEvent) {
        Platform.runLater(() -> downloadVisibility(true));
        preferencesViewModel.downloadNewVersion(downloadProgressBar, downloadProgressLabel);
    }

    private void resetPreferences(ActionEvent actionEvent) {
        suppressListeners = true;
        preferencesViewModel.resetPreferences();
        updateUI();
        suppressListeners = false;
    }

    private void updateUI() {
        downloadProgressBar.setProgress(0.0);
        downloadVisibility(false);

        languageComboBox.setItems(preferencesViewModel.getLanguagesProperty());
        themeComboBox.setItems(preferencesViewModel.getThemesProperty());

        String langCode = preferencesViewModel.getLanguage();
        String displayLanguage = displayNameFromLanguageCode(langCode);
        languageComboBox.getSelectionModel().select(displayLanguage);

        String theme = preferencesViewModel.getTheme();
        themeComboBox.getSelectionModel().select(capitalize(theme));

        versionLabel.setText(preferencesViewModel.getAppVersion());

        //autoSwitchCheckbox.setSelected(Boolean.parseBoolean(preferencesViewModel.getAutoSwitch()));
    }

    private String displayNameFromLanguageCode(String code) {
        if (code.equals("hr")) return "Hrvatski";
        return "English";
    }

    private void applyListeners() {
        languageComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (!suppressListeners && newVal != null) {
                newVal = newVal.equals("English") ? "en" : "hr";
                preferencesViewModel.setLanguage(newVal);
            }
        });

        themeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (!suppressListeners && newVal != null) {
                preferencesViewModel.setTheme(newVal.toLowerCase());
            }
        });

//        autoSwitchCheckbox.selectedProperty().addListener((obs, oldVal, newVal) -> {
//            preferencesViewModel.setAutoSwitch(newVal);
//        });
    }

}
