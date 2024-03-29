package Elkhadema.khadema.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Elkhadema.khadema.App;
import Elkhadema.khadema.Service.ServiceImplemantation.CompanyServiceImp;
import Elkhadema.khadema.Service.ServiceImplemantation.NotificationServiceImp;
import Elkhadema.khadema.Service.ServiceImplemantation.PostServiceImp;
import Elkhadema.khadema.Service.ServiceImplemantation.UserServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.CompanyService;
import Elkhadema.khadema.Service.ServiceInterfaces.NotificationService;
import Elkhadema.khadema.Service.ServiceInterfaces.PostService;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.domain.Notification;
import Elkhadema.khadema.domain.Post;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NavbarController implements Initializable {

	private UserService userService = new UserServiceImp();
	private CompanyService companyService = new CompanyServiceImp();
	private NotificationService notificationService = new NotificationServiceImp();
	PostService postService = new PostServiceImp();
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

	@FXML
	TextField searchbar;

	@FXML
	public void GoSearch() throws IOException {
		if (searchbar.getText().length() > 0) {
			SearchPage.searchString = searchbar.getText();
			App.setRoot("saechplace");
		}
	}

	@FXML
	public void goResume() throws IOException {
		Stage stage = App.stage;
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
		if (notifList.isVisible()) {
			notifList.setVisible(false);
			notifList.setDisable(true);
			notifBox.getChildren().clear();
			return;
		}
		notifList.setVisible(true);
		notifList.setDisable(false);
		initNotif();
	}

	private void initNotif() {
		notificationService.allNotifications(Session.getUser()).forEach(notification -> afficheNotif(notification));
	}

	private void afficheNotif(Notification notification) {
		Text titleText = new Text();
		TextArea contentArea = new TextArea();
		contentArea.setDisable(true);
		contentArea.getStyleClass().add("postTxtField");
		titleText.setFill(Color.WHITE);
		titleText.setFont(new Font("SansSerif", 14));
		switch (notification.getType()) {
			case "post":
				titleText.setText("new " + notification.getType() + " from " + notification.getUser().getUserName());
				contentArea.setText(limitString(notification.getContent()));
				break;
			case "message":
				titleText.setText("new " + notification.getType() + " from " + notification.getUser().getUserName());
				contentArea.setText(limitString(notification.getContent()));
				break;
			default:
				break;
		}
		VBox vBox = new VBox(titleText, contentArea);
		switch (notification.getType()) {
			case "post":
				vBox.setOnMouseClicked(event -> {
					CommentsPageController.setCommentedpost(postService.getPostById(new Post(notification.getId())));
					try {
						App.setRoot("comment");
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				break;
			case "message":
				vBox.setOnMouseClicked(event -> {
					try {
						goChat(null, notification.getUser());
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

			default:
				break;
		}
		notifBox.getChildren().add(vBox);

	}

	@FXML
	public void goChat(ActionEvent event) throws IOException {
		goChat(event, null);
	}

	@FXML
	public void goChat(ActionEvent event, User user) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Elkhadema/khadema/chatroom.fxml"));
		root = loader.load();
		ChatRoomController chatRoomController = loader.getController();
		chatRoomController.init(user);
		stage = App.stage;
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
        Stage stage = App.stage;
        stage.close();
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

	@FXML
	VBox notifList;
	@FXML
	VBox notifBox;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		notifList.setVisible(true);
		notifList.setVisible(false);
	}

	private String limitString(String string) {
		if (string.length() <= 50)
			return string;
		else
			return string.substring(0, 50).concat("...");
	}

}
