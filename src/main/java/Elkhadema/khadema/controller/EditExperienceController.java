package Elkhadema.khadema.controller;

import java.time.LocalDate;
import Elkhadema.khadema.Service.validateInfo.DateValidator;
import Elkhadema.khadema.Service.validateInfo.JobNameValidator;
import Elkhadema.khadema.domain.Experience;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditExperienceController {
    Experience experience;
    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    @FXML
    private TextField descriptionfField;
    @FXML
    private ComboBox<String> typeField;
    @FXML
    private TextField missionField;
    @FXML
    private TextField tecnologieField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    private Stage stage;
    public boolean choix;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize(Experience experience) {
        this.experience = experience;
        if (experience == null) {
            this.experience = new Experience(0, "", "", "full-time", null, null, "");
        }
        descriptionfField.setText(this.experience.getDescription());
        typeField.setItems(FXCollections.observableArrayList("full-time", "part-time"));
        if (this.experience.getType().equals("full-time"))
            typeField.getSelectionModel().select(0);
        else
            typeField.getSelectionModel().select(1);
        missionField.setText(this.experience.getMission());
        tecnologieField.setText(this.experience.getTechnologie());
        startDatePicker.setValue(this.experience.getStartDate());
        if (this.experience.getStartDate() == null) {
            endDatePicker.setValue(null);
        } else
            endDatePicker.setValue(LocalDate.now());

    }

    public void submitForm() {
        if (!descriptionfField.getText().strip().isEmpty()) {
            return;
        }
        experience.setDescription(descriptionfField.getText());

        if (!JobNameValidator.isValidJobName(tecnologieField.getText())) {
            return;
        }
        experience.setTechnologie(tecnologieField.getText());
        if (!DateValidator.isValidDate(startDatePicker.getValue())) {
            return;
        }
        experience.setStartDate(startDatePicker.getValue());
        if (endDatePicker.getValue().isBefore(experience.getStartDate())) {
            return;
        }
        experience.setEndDate(endDatePicker.getValue());
        if (missionField.getText().strip().isEmpty()) {
            return;
        }
        experience.setMission(missionField.getText());
        choix = true;
        stage.close();

    }

}
