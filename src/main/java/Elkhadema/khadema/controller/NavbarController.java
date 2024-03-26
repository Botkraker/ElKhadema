package Elkhadema.khadema.controller;

import java.io.IOException;

import Elkhadema.khadema.App;
import javafx.fxml.FXML;

public class NavbarController {
	@FXML
    public void goJobsList() {
		//App.setRoot(null);
    }

    @FXML
    public void goResume() throws IOException {
    	App.setRoot("resmue");
    }

    @FXML
    public void goNotifications() {
    	//App.setRoot(null);
    }
    @FXML
    public void goHome() throws IOException {
    	App.setRoot("mainpage");
    }
    @FXML
    public void logout() {

    }
}
