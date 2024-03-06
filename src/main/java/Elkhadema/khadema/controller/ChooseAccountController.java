package Elkhadema.khadema.controller;


import java.io.IOException;

import Elkhadema.khadema.App;
import javafx.fxml.FXML;

public class ChooseAccountController {


    @FXML
    public void user() throws IOException{
        App.setRoot("login");
    }

    @FXML
    public void company() throws IOException{
        App.setRoot("LoginCompany");
    }

    @FXML
    public void signUp() throws IOException{
        App.setRoot("SignUp");
    }


}
