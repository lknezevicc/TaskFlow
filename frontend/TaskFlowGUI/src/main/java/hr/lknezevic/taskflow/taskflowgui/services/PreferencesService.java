package hr.lknezevic.taskflow.taskflowgui.services;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.util.List;

public interface PreferencesService {
    List<String> getI18nLanguages();
    List<String> getThemes();
    String getLanguage();
    void setLanguage(String language);
    String getTheme();
    void setTheme(String theme);
    //Boolean getAutoSwitchTheme();
    //void setAutoSwitchTheme(Boolean autoSwitchTheme);
    String getApplicationVersion();
    void resetPreferences();
    void downloadNewVersion(ProgressBar progressBar, Label statusLabel);
}
