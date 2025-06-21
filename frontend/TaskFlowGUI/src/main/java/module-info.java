module hr.lknezevic.taskflow.taskflowgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires dd.plist;
    requires com.google.guice;
    requires java.net.http;
    requires jakarta.xml.bind;
    requires com.google.errorprone.annotations;
    requires org.mapstruct;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires jakarta.xml.ws;
    requires jakarta.inject;

    opens hr.lknezevic.taskflow.taskflowgui to javafx.fxml, com.google.guice;
    opens hr.lknezevic.taskflow.taskflowgui.controllers to javafx.fxml;
    opens hr.lknezevic.taskflow.taskflowgui.services.impl to com.google.guice;
    opens hr.lknezevic.taskflow.taskflowgui.factory.alert to com.google.guice;
    opens hr.lknezevic.taskflow.taskflowgui.model to jakarta.xml.bind;

    opens soap.countryinfo to com.sun.xml.bind;
    exports soap.countryinfo to com.sun.xml.ws;

    exports hr.lknezevic.taskflow.taskflowgui.controllers;
    exports hr.lknezevic.taskflow.taskflowgui.enums;
    exports hr.lknezevic.taskflow.taskflowgui.services;
    exports hr.lknezevic.taskflow.taskflowgui.utils;
    exports hr.lknezevic.taskflow.taskflowgui.model;
    exports hr.lknezevic.taskflow.taskflowgui.dto;
    exports hr.lknezevic.taskflow.taskflowgui.viewmodel;
    exports hr.lknezevic.taskflow.taskflowgui.mappers;
    exports hr.lknezevic.taskflow.taskflowgui.observable;
    exports hr.lknezevic.taskflow.taskflowgui.factory.alert;
    exports hr.lknezevic.taskflow.taskflowgui to com.google.guice, javafx.graphics;
    exports hr.lknezevic.taskflow.taskflowgui.services.impl to com.google.guice;
    exports hr.lknezevic.taskflow.taskflowgui.config.guice to com.google.guice;
    exports hr.lknezevic.taskflow.taskflowgui.managers to com.google.guice;

}
