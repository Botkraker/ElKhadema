package Elkhadema.khadema.controller;

import java.io.IOException;

import Elkhadema.khadema.App;
import Elkhadema.khadema.Service.ServiceImplemantation.CompanyServiceImp;
import Elkhadema.khadema.Service.ServiceImplemantation.UserServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.CompanyService;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NavbarController {

	private UserService userService = new UserServiceImp();
	private CompanyService companyService = new CompanyServiceImp();
	Stage stage;
	Scene scene;
	Parent root;

	@FXML
	public void goJobsList() throws IOException {
		App.setRoot("jobs");
	}

	@FXML
	public void goResume(Stage stage) throws IOException {
		User user = Session.getUser();
		if (companyService.isCompany(user)) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Elkhadema/khadema/company.fxml"));
			root = loader.load();
			CompanyController companyController = loader.getController();
			companyController.init(user);

		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Elkhadema/khadema/resmue.fxml"));
			root = loader.load();
			ResumeController resumeController = loader.getController();
			resumeController.init(user);
		}
		this.stage = stage;
		scene = new Scene(root);
		this.stage.setScene(scene);
		this.stage.show();
	}

	@FXML
	public void goNotifications() {
		// App.setRoot(null);
	}

	@FXML
	public void goHome() throws IOException {
		App.setRoot("mainpage");
	}

	public void logout() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("logout");
		alert.setHeaderText("your about to logout");
		alert.setContentText("do you really want to exit");
		if (alert.showAndWait().get() == ButtonType.OK) {
			stage.close();
		}
		userService.logOut(Session.getUser());
	}
}
