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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NavbarController {

	private UserService userService = new UserServiceImp();
	private CompanyService companyService = new CompanyServiceImp();
	Stage stage;
	Scene scene;
	Parent root;
	
	@FXML
    void postMsg(MouseEvent event) {

    }
	@FXML
	public void goJobsList() throws IOException {
		App.setRoot("jobs");
	}
	@FXML TextField searchbar;
	@FXML
	public void GoSearch() throws IOException {
		if (searchbar.getText().length()>0) {
			SearchPage.searchString=searchbar.getText();
			App.setRoot("saechplace");
		}
	}
	@FXML
	public void goResume() throws IOException {
		Stage stage=App.stage;
		User user = Session.getUser();
		System.out.println();
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
    public void goChat(ActionEvent event) throws IOException{
        goChat(event,null);
    }
    @FXML
    public void goChat(ActionEvent event, User user) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Elkhadema/khadema/chatroom.fxml"));
        root = loader.load();
        ChatRoomController chatRoomController = loader.getController();
        chatRoomController.init(user);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

	@FXML
	public void goHome() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Elkhadema/khadema/mainpage.fxml"));
		root = loader.load();
		MainPageController mainPageController = loader.getController();
		mainPageController.initialize(null, null);
		stage = App.stage;
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void logout() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("logout");
		alert.setHeaderText("your about to logout");
		alert.setContentText("do you really want to exit");
		if (alert.showAndWait().get() == ButtonType.OK) {
			((Stage)App.scene.getWindow()).close();
		}
		userService.logOut(Session.getUser());
	}
        public void openprofile(MouseEvent event, User tmp) throws IOException {
        User user = tmp;
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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        @FXML
   	 public void sessionOpenProfile(MouseEvent event) throws IOException {
   	        openprofile(event, Session.getUser());
   	 }
}
