package hr.lknezevic.taskflow.taskflowgui.services.impl;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.enums.PlistProperty;
import hr.lknezevic.taskflow.taskflowgui.managers.DownloadManager;
import hr.lknezevic.taskflow.taskflowgui.services.BaseService;
import hr.lknezevic.taskflow.taskflowgui.services.PreferencesService;
import hr.lknezevic.taskflow.taskflowgui.utils.HttpRequestModifier;
import hr.lknezevic.taskflow.taskflowgui.utils.JsonUtil;
import hr.lknezevic.taskflow.taskflowgui.utils.PlistUtil;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.util.List;

public class PreferencesServiceImpl extends BaseService implements PreferencesService {
    private final static String APPLICATION_URL = "https://github.com/lknezevicc/TaskFlow/archive/refs/heads/main.zip";
    private final PlistUtil plistUtil;
    private final DownloadManager downloadManager;

    @Inject
    public PreferencesServiceImpl(HttpClient httpClient, JsonUtil jsonUtil, HttpRequestModifier httpRequestModifier,
                                  PlistUtil plistUtil, DownloadManager downloadManager) {
        super(httpClient, jsonUtil, httpRequestModifier);
        this.plistUtil = plistUtil;
        this.downloadManager = downloadManager;
    }

    @Override
    public List<String> getI18nLanguages() {
        return List.of("English", "Croatian");
    }

    @Override
    public List<String> getThemes() {
        return List.of("Dark", "Light");
    }

    @Override
    public String getApplicationVersion() {
        return plistUtil.readValue(PlistProperty.VERSION);
    }

    @Override
    public String getLanguage() {
        return plistUtil.readValue(PlistProperty.LANGUAGE);
    }

    @Override
    public void setLanguage(String language) {
        plistUtil.writeValue(PlistProperty.LANGUAGE, language);
    }

    @Override
    public String getTheme() {
        return plistUtil.readValue(PlistProperty.THEME);
    }

    @Override
    public void setTheme(String theme) {
        plistUtil.writeValue(PlistProperty.THEME, theme);
    }

    @Override
    public void resetPreferences() {
        plistUtil.resetToDefault();
    }

    @Override
    public void downloadNewVersion(ProgressBar progressBar, Label statusLabel) {
        try {
            downloadManager.download(new URI(APPLICATION_URL).toURL(), new File(System.getProperty("user.dir"), "newVersion.zip"),
                    30, progressBar, statusLabel);
        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
