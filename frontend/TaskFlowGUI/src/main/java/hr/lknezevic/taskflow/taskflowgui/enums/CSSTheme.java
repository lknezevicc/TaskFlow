package hr.lknezevic.taskflow.taskflowgui.enums;

import java.util.Objects;

public enum CSSTheme {
    CORE("/hr/lknezevic/taskflow/taskflowgui/style/core.css"),
    LIGHT("/hr/lknezevic/taskflow/taskflowgui/style/light-theme.css"),
    DARK("/hr/lknezevic/taskflow/taskflowgui/style/dark-theme.css"),;

    private final String path;


    CSSTheme(String path) {
        this.path = path;
    }

    public String getPath() {
        return Objects.requireNonNull(CSSTheme.class.getResource(path)).toExternalForm();
    }
}
