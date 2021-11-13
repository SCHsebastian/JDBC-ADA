module sch.spoty.jdbcada {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens sch.spoty.jdbcada to javafx.fxml;
    exports sch.spoty.jdbcada;
    exports controllers;
    opens controllers to javafx.fxml;
}