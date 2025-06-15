package hr.lknezevic.taskflow.taskflowgui.services.impl;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.model.UserSidebarData;
import hr.lknezevic.taskflow.taskflowgui.services.SidebarService;
import hr.lknezevic.taskflow.taskflowgui.utils.XmlUtil;
import jakarta.xml.bind.JAXBException;
import javafx.scene.control.Label;

public class SidebarServiceImpl implements SidebarService {
    private final XmlUtil xmlUtil;

    @Inject
    public SidebarServiceImpl(XmlUtil xmlUtil) {
        this.xmlUtil = xmlUtil;
    }

    @Override
    public void loadSidebarData(Label fullName, Label email) {
        try {
            UserSidebarData userSidebarData = xmlUtil.readUserSidebarData();
            System.out.println(userSidebarData.toString());
            fullName.setText(userSidebarData.getFirstName() + " " + userSidebarData.getLastName());
            email.setText(userSidebarData.getEmail());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
