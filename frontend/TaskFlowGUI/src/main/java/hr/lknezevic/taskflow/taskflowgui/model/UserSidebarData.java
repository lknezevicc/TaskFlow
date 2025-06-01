package hr.lknezevic.taskflow.taskflowgui.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlRootElement(name = "user")
@XmlType(propOrder = { "firstName", "lastName", "email" })
public class UserSidebarData {

    private String firstName;
    private String lastName;
    private String email;

    private transient StringProperty firstNameProperty;
    private transient StringProperty lastNameProperty;
    private transient StringProperty emailProperty;

    public UserSidebarData() {
        this.firstNameProperty = new SimpleStringProperty(firstName);
        this.lastNameProperty = new SimpleStringProperty(lastName);
        this.emailProperty = new SimpleStringProperty(email);
    }

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        if (this.firstNameProperty != null) {
            this.firstNameProperty.set(firstName);
        }
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        if (this.lastNameProperty != null) {
            this.lastNameProperty.set(lastName);
        }
    }

    @XmlElement
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        if (this.emailProperty != null) {
            this.emailProperty.set(email);
        }
    }

    public StringProperty firstNameProperty() {
        if (firstNameProperty == null) {
            firstNameProperty = new SimpleStringProperty(firstName);
        }
        return firstNameProperty;
    }

    public StringProperty lastNameProperty() {
        if (lastNameProperty == null) {
            lastNameProperty = new SimpleStringProperty(lastName);
        }
        return lastNameProperty;
    }

    public StringProperty emailProperty() {
        if (emailProperty == null) {
            emailProperty = new SimpleStringProperty(email);
        }
        return emailProperty;
    }
}
