package Elkhadema.khadema.controller;

import java.io.IOException;

import Elkhadema.khadema.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}