package Elkhadema.khadema.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import Elkhadema.khadema.App;
import Elkhadema.khadema.Service.ServiceImplemantation.UserServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.Service.validateInfo.EmailValidator;
import Elkhadema.khadema.Service.validateInfo.PasswordValidator;
import Elkhadema.khadema.Service.validateInfo.PhoneNumberValidate;
import Elkhadema.khadema.Service.validateInfo.UsernameValidator;
import Elkhadema.khadema.domain.ContactInfo;
import Elkhadema.khadema.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SignUpUserController implements Initializable {
    UserService userService = new UserServiceImp();

    User user = new User(0, null, null);
    ContactInfo contactInfo = new ContactInfo(0);
    @FXML
    TextField userNameField;
    @FXML
    TextField firstname;
    @FXML
    TextField lastname;
    @FXML
    TextField email;
    @FXML
    TextField code;
    @FXML
    TextField phone;
    @FXML
    TextField password;
    @FXML
    TextField confirm;
    @FXML
    Text invalid;
    @FXML
    ComboBox<String> country;

    @FXML
    public void register() throws IOException {
        String vemail = email.getText();
        if (!EmailValidator.isValidEmail(vemail)) {
            invalid.setText("*email invalid");
            return;
        }
        contactInfo.setEmail(vemail);
        String username = firstname.getText() + " " + lastname.getText();
        if (!UsernameValidator.isValidUsername(username)) {
            invalid.setText("*username invalid");
            return;
        }
        if(country.getValue()==null){
            invalid.setText("choose a country");
            return;
        }
        user.setUserName(username);
        if (PasswordValidator.isValidPassword(password.getText())) {
            invalid.setText("password invalid");
            return;
        }
        String confirmPassword = confirm.getText();
        if (!confirmPassword.equals(password.getText())) {
            invalid.setText("confirm your password");
            return;
        }
        user.setPassword(confirmPassword);
        String phoneNumber = phone.getText();
        if (!PhoneNumberValidate.isValidPhoneNumber(phoneNumber)) {
            invalid.setText("phone number invalid");
            return;
        }
        contactInfo.setPhoneNumber(Integer.parseInt(phoneNumber));
        this.userService.SignUp(user, "person");
        App.setRoot("login");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<String> countries=FXCollections.observableArrayList();
        String[] countryCodes = Locale.getISOCountries();
        for (String countryCode : countryCodes) {
            Locale locale = new Locale("", countryCode);
            countries.add( locale.getDisplayCountry());
        }
        country.setPromptText("choose a country");
        country.setItems(countries);
    }


}
