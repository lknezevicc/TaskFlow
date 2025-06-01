package hr.lknezevic.taskflow.taskflowgui.utils;

import hr.lknezevic.taskflow.taskflowgui.model.UserSidebarData;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class XmlUtil {
    private final static String XML_PATH = "src/main/resources/hr/lknezevic/taskflow/taskflowgui/cache/user-sidebar-data-cache.xml";

    public void writeUserSidebarData(UserSidebarData userSidebarData) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UserSidebarData.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(userSidebarData, new File(XML_PATH));
    }

    public UserSidebarData readUserSidebarData() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UserSidebarData.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return (UserSidebarData) jaxbUnmarshaller.unmarshal(new File(XML_PATH));
    }

    public void clearUserSidebarData() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UserSidebarData.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        UserSidebarData userSidebarData = new UserSidebarData();
        jaxbMarshaller.marshal(userSidebarData, new File(XML_PATH));
    }

}
