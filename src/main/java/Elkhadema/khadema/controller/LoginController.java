package Elkhadema.khadema.controller;

import java.io.IOException;
import Elkhadema.khadema.App;
import Elkhadema.khadema.Service.ServiceImplemantation.UserServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.domain.ContactInfo;
import Elkhadema.khadema.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController {
    private Scene Scene;
    private Stage stage;
    private Parent root;
    // new user to add
    User user = new User(0, null, null);
    ContactInfo contactInfo = new ContactInfo(0);
    // service
    UserService userService = new UserServiceImp();
    // content holder
    @FXML
    TextField name;
    @FXML
    TextField password;
    @FXML
    Text invalid;
    @FXML
    public void login(ActionEvent event) throws IOException{
        try {
            user= userService.Login(name.getText(), password.getText());

        } catch (Exception e) {
            invalid.setText("username n'existe pas");
            return;
        }
        if (user==null) {
            invalid.setText("password invalid");
            return;
        }
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Elkhadema/khadema/mainpage.fxml"));
        MainPageController mainPageController=loader.getController();
        root =loader.load();
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void signUp() throws IOException{
        App.setRoot("ChooseAccount");
    }
}
