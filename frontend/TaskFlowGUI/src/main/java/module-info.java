module hr.lknezevic.taskflow.taskflowgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires dd.plist;
    requires com.google.guice;
    requires java.net.http;
    requires com.google.gson;
    requires jakarta.xml.bind;

    opens hr.lknezevic.taskflow.taskflowgui to javafx.fxml, com.google.guice;
    opens hr.lknezevic.taskflow.taskflowgui.controllers to javafx.fxml;
    opens hr.lknezevic.taskflow.taskflowgui.services.impl to com.google.guice;
    opens hr.lknezevic.taskflow.taskflowgui.factory.alert to com.google.guice;
    opens hr.lknezevic.taskflow.taskflowgui.model to jakarta.xml.bind;

    exports hr.lknezevic.taskflow.taskflowgui.controllers;
    exports hr.lknezevic.taskflow.taskflowgui.enums;
    exports hr.lknezevic.taskflow.taskflowgui.services;
    exports hr.lknezevic.taskflow.taskflowgui.utils;
    exports hr.lknezevic.taskflow.taskflowgui.model;
    exports hr.lknezevic.taskflow.taskflowgui.factory.alert;
    exports hr.lknezevic.taskflow.taskflowgui to com.google.guice, javafx.graphics;
    exports hr.lknezevic.taskflow.taskflowgui.services.impl to com.google.guice;
    exports hr.lknezevic.taskflow.taskflowgui.config.guice to com.google.guice;
}
