module sch.spoty.jdbcada {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.base;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires javafx.media;


    opens sch.spoty.jdbcada to javafx.fxml, javafx.base;
    opens controllers to javafx.fxml, javafx.base;
    opens dbconnection to javafx.fxml, javafx.base;
    exports sch.spoty.jdbcada;
    exports controllers;
    exports dbconnection;

}