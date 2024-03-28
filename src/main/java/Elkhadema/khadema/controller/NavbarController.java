package Elkhadema.khadema.controller;

import java.io.IOException;

import Elkhadema.khadema.App;
import Elkhadema.khadema.Service.ServiceImplemantation.UserServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.util.Session;
import javafx.fxml.FXML;

public class NavbarController {
	private UserService userService = new UserServiceImp();
	@FXML
    public void goJobsList() throws IOException {
		App.setRoot("jobs");
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
    public void logout() {
        userService.logOut(Session.getUser());
    }
}
