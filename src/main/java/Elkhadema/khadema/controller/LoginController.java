package Elkhadema.khadema.controller;

import java.io.IOException;

import Elkhadema.khadema.Service.ServiceImplemantation.UserServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.Service.validateInfo.EmailValidator;
import Elkhadema.khadema.Service.validateInfo.PasswordValidator;
import Elkhadema.khadema.Service.validateInfo.PhoneNumberValidate;
import Elkhadema.khadema.Service.validateInfo.UsernameValidator;
import Elkhadema.khadema.domain.ContactInfo;
import Elkhadema.khadema.domain.User;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
// TODO this class still needs a way to add images
public class LoginController {
    // new user to add
    User user = new User(0, null, null);
    ContactInfo contactInfo = new ContactInfo(0);
    // service
    UserService userService=new UserServiceImp();
    // content holder
    @FXML
    HBox infoUserBox;
    @FXML
    StackPane signUpStack;
    @FXML
    VBox contactBox;

    // counts
    @FXML
    Text usersText;
    @FXML
    Text companiesText;
    @FXML
    Text postsText;
    @FXML
    Text jobText;

    // check box for terms of service
    @FXML
    CheckBox check;

    // user info
    @FXML
    TextField phoneNumber;
    @FXML
    TextField address;
    @FXML
    TextField emailInput;
    @FXML
    TextField usernameInput;
    @FXML
    PasswordField passwordInput;
    @FXML
    PasswordField confirmPasswordInput;

    // to tell the user about invalid info
    // TODO change the name later to be more readable
    @FXML
    Text pCheck;
    @FXML
    Text error;
    @FXML
    Text invalid;

    private void init() {
        // TODO later to init the counts using the dao
    }

    @FXML
    private void addUserInfo() {
        String email = emailInput.getText();
        if (!EmailValidator.isValidEmail(email)) {
            invalid.setText("*email invalid");
            return;
        }
        contactInfo.setEmail(email);
        String username = usernameInput.getText();
        if (!UsernameValidator.isValidUsername(username)) {
            invalid.setText("*username invalid");
            return;
        }
        user.setUserName(username);
        String password = passwordInput.getText();
        if (!PasswordValidator.isValidPassword(password)) {
            invalid.setText(
                    "*password invalid: requires length \n more than 8 and at least one special char digit and letter ");
            return;
        }
        String confirmPassword = confirmPasswordInput.getText();
        if (!confirmPassword.equals(password)) {
            invalid.setText("confirm your password");
            return;
        }
        user.setPassword(confirmPassword);
        infoUserBox.visibleProperty().set(false);
        contactBox.visibleProperty().set(true);

    }

    @FXML
    private void signUp() {
        if (!check.isSelected()) {
            pCheck.setText("*accept the terms of service ");
            return;
        }
        signUpStack.visibleProperty().set(false);
        infoUserBox.visibleProperty().set(true);
    }

    @FXML
    private void addContactInfo() throws IOException {
        String phone = phoneNumber.getText();
        if (!PhoneNumberValidate.isValidPhoneNumber(phone)) {
            error.setText("phone number invalid");
            return;
        }
        contactInfo.setPhoneNumber(Integer.parseInt(phone));
        String addressString = address.getText();
        if (addressString.isEmpty()) {
            error.setText("address invalid");
            return;
        }
        contactInfo.setAddress(addressString);
        user.setContactInfo(contactInfo);
        error.setText("");
        this.userService.Signin(user, "person");

        // TODO add the change to the login page
        System.out.println("user = " + user);
    }
}
