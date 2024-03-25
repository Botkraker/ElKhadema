package Elkhadema.khadema.controller;

import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ResumeController {
    User currentUser=Session.getUser();
    User displayUser;
    @FXML
    Text nameText;
    @FXML
    VBox btnVbox;
    @FXML
    TextField ageText;
    @FXML
    TextField sexeText;
    @FXML
    TextField jobText;
    public void init(User user){
        if (user.getUserName()!=Session.getUser().getUserName()) {
            displayUser=user;
            Button followbutton=new Button("follow");
            followbutton.getStyleClass().add("postbtn");
            Button chatButton=new Button("chat");
            chatButton.getStyleClass().add("postbtn");
        }
        Button generateCVbutton=new Button("get pdf");
        generateCVbutton.getStyleClass().add("postbtn");
        

    }

}
