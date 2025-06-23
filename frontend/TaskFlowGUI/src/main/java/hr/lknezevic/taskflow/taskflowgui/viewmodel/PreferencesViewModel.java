package hr.lknezevic.taskflow.taskflowgui.viewmodel;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.services.PreferencesService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class PreferencesViewModel {
    private final PreferencesService preferencesService;
    private final ObservableList<String> languages = FXCollections.observableArrayList("English", "Hrvatski");
    private final ObservableList<String> themes = FXCollections.observableArrayList("Light", "Dark");

    @Inject
    public PreferencesViewModel(PreferencesService preferencesService) {
        this.preferencesService = preferencesService;
    }

    public void downloadNewVersion(ProgressBar progressBar, Label statusLabel) {
        preferencesService.downloadNewVersion(progressBar, statusLabel);
    }

    public ObservableList<String> getLanguagesProperty() {
        return languages;
    }

    public ObservableList<String> getThemesProperty() {
        return themes;
    }

    public String getLanguage() {
        return preferencesService.getLanguage();
    }

    public void setLanguage(String language) {
        preferencesService.setLanguage(language);
    }

    public String getTheme() {
        return preferencesService.getTheme();
    }

    public void setTheme(String theme) {
        preferencesService.setTheme(theme);
    }

    //public Boolean getAutoSwitchTheme() {}

    //public void setAutoSwitchTheme(Boolean autoSwitchTheme) {}

    public String getAppVersion() {
        return preferencesService.getApplicationVersion();
    }

    public void resetPreferences() {
        preferencesService.resetPreferences();
    }

}
