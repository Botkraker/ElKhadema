module Elkhadema.khadema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Elkhadema.khadema to javafx.fxml;
    exports Elkhadema.khadema;
}
