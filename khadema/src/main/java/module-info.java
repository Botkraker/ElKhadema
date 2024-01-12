module Elkhadema.khadema {
    requires javafx.controls;
    requires javafx.fxml;

    opens Elkhadema.khadema to javafx.fxml;
    exports Elkhadema.khadema;
}
