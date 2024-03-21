package Elkhadema.khadema.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Elkhadema.khadema.DAO.DAOImplemantation.UserDAO;
import Elkhadema.khadema.Service.ServiceImplemantation.FollowServiceImp;
import Elkhadema.khadema.Service.ServiceImplemantation.UserServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.FollowService;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainPageController implements Initializable {
    FollowService followService=new FollowServiceImp();
    UserService userService=new UserServiceImp();
    UserDAO userDAO=new UserDAO();
    @FXML
    ButtonBar listContact;
    @FXML
    VBox vContacts;
    @FXML
    public void goHome() {

    }

    @FXML
    public void goJobsList() {

    }

    @FXML
    public void goResume() {

    }

    @FXML
    public void goNotifications() {

    }

    @FXML
    public void logout() {

    }

    @FXML
    public void postMsg() {

    }

    @FXML
    public void likePost() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        List<User>follwing= followService.getfollowing(Session.getUser());
        List<VBox> hBoxs=new ArrayList<>();

        for (User user : follwing) {
            User tmp=userDAO.get(user.getId()).get();
            Text text = new Text(tmp.getUserName());
            text.setStyle("-fx-text-fill:white;-fx-font-size:15px;");
            ImageView imageView = new ImageView(new Image("file:src//main//resources//images//user.png"));
            imageView.setFitHeight(46);
            imageView.setFitWidth(46);
            HBox hBox = new HBox(imageView ,text);
            hBox.setAlignment(Pos.BASELINE_RIGHT);
            ButtonBar buttonBar=new ButtonBar();
            buttonBar.getButtons().addAll(hBox);
            VBox vBox=new VBox(buttonBar);
            vBox.getStyleClass().add("posts");
            hBoxs.add(vBox);
        }
        vContacts.getChildren().addAll(hBoxs);
    }

}
