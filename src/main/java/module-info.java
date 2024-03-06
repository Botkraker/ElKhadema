module Elkhadema.khadema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;
    requires de.jensd.fx.glyphs.commons;
    requires javafx.graphics;
    opens Elkhadema.khadema to javafx.fxml;
    opens Elkhadema.khadema.controller to javafx.fxml;

    exports Elkhadema.khadema;
    exports Elkhadema.khadema.controller;
}
