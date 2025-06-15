package hr.lknezevic.taskflow.taskflowgui.services;

import hr.lknezevic.taskflow.taskflowgui.model.UserSidebarData;
import javafx.scene.control.Label;

public interface SidebarService {
    void loadSidebarData(Label fullName, Label email);
}
