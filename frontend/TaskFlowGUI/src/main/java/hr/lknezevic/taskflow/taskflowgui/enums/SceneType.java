package hr.lknezevic.taskflow.taskflowgui.enums;

import java.util.Objects;

public enum SceneType {
    LOG_IN("/hr/lknezevic/taskflow/taskflowgui/style/log-in-view.css"),
    MAIN("/hr/lknezevic/taskflow/taskflowgui/style/log-in-view.css"),
    DASHBOARD("/hr/lknezevic/taskflow/taskflowgui/style/log-in-view.css");

    private final String cssPath;

    SceneType(String cssPath) {
        this.cssPath = cssPath;
    }

    public String getCssPath() {
        return Objects.requireNonNull(SceneType.class.getResource(cssPath)).toExternalForm();
    }
}
