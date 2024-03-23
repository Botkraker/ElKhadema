module Elkhadema.khadema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
	requires kernel;
	requires io;
	requires layout;
	requires javafx.media;
	requires java.desktop;
    opens Elkhadema.khadema to javafx.fxml;
    opens Elkhadema.khadema.controller to javafx.fxml;

    exports Elkhadema.khadema;
    exports Elkhadema.khadema.controller;
}
