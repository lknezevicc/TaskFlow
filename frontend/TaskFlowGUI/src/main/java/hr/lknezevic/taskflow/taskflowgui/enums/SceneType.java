package hr.lknezevic.taskflow.taskflowgui.enums;

import java.util.Objects;

public enum SceneType {
    LOG_IN("/hr/lknezevic/taskflow/taskflowgui/style/log-in-view.css"),
    MAIN("/hr/lknezevic/taskflow/taskflowgui/style/log-in-view.css"),
    DASHBOARD("/hr/lknezevic/taskflow/taskflowgui/style/log-in-view.css"),
    TASKS("/hr/lknezevic/taskflow/taskflowgui/style/tasks-view.css"),
    PROJECT_DETAIL("/hr/lknezevic/taskflow/taskflowgui/style/project-detail-view.css"),
    ADMINISTRATION("/hr/lknezevic/taskflow/taskflowgui/style/administration-view.css"),
    PREFERENCES("/hr/lknezevic/taskflow/taskflowgui/style/preferences-view.css"),
    USER_FORM("/hr/lknezevic/taskflow/taskflowgui/style/user-form-view.css"),
    PROJECTS("/hr/lknezevic/taskflow/taskflowgui/style/user-form-view.css"),
    TASK_FORM("/hr/lknezevic/taskflow/taskflowgui/style/user-form-view.css"),
    PROJECT_FORM("/hr/lknezevic/taskflow/taskflowgui/style/user-form-view.css"),
    TASK_DETAIL("/hr/lknezevic/taskflow/taskflowgui/style/user-form-view.css");

    private final String cssPath;

    SceneType(String cssPath) {
        this.cssPath = cssPath;
    }

    public String getCssPath() {
        return Objects.requireNonNull(SceneType.class.getResource(cssPath)).toExternalForm();
    }
}
