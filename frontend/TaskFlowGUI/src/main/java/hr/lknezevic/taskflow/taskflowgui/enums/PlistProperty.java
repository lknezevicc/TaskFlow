package hr.lknezevic.taskflow.taskflowgui.enums;

public enum PlistProperty {
    LANGUAGE("language"),
    ACCESS_TOKEN("accessToken"),
    REFRESH_TOKEN("refreshToken"),
    VERSION("appVersion");

    private final String key;

    PlistProperty(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
