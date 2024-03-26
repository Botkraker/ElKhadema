package Elkhadema.khadema.controller;

import Elkhadema.khadema.Service.validateInfo.JobNameValidator;
import Elkhadema.khadema.domain.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditBioController {
    private TextField jobField;
    private TextField ageField;
    private ComboBox<String> sexeComboBox;
    User user;
    String job;
    int age;
    String sexe;
    private Stage stage;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize(User user) {
        sexeComboBox.setItems(FXCollections.observableArrayList(
                "male",
                "female"));
    }

    public void submitForm() {
        if (!JobNameValidator.isValidJobName(jobField.getText())) {
            return;
        }
        job = jobField.getText();
        try {
            if (Integer.parseInt(ageField.getText()) < 1) {
                return;
            }

        } catch (Exception e) {
            return;
        }
        age = Integer.parseInt(ageField.getText());
        stage.close();

    }

}
