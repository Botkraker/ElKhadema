package Elkhadema.khadema.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import Elkhadema.khadema.App;
import Elkhadema.khadema.DAO.DAOImplemantation.CompetanceDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.ExperienceDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.PersonDAO;
import Elkhadema.khadema.Service.ServiceImplemantation.UserServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.domain.Competance;
import Elkhadema.khadema.domain.Experience;
import Elkhadema.khadema.domain.Media;
import Elkhadema.khadema.domain.Person;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.MediaChooser;
import Elkhadema.khadema.util.Session;
import Elkhadema.khadema.util.Exception.UserNotFoundException;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ResumeController extends NavbarController{
    User session = Session.getUser();
    PersonDAO personDAO = new PersonDAO();
    UserService userService = new UserServiceImp();
    ExperienceDAO experienceDAO = new ExperienceDAO();
    CompetanceDAO competanceDAO = new CompetanceDAO();
    Person currentUser;
    @FXML
    ImageView profileImg;
    @FXML
    Text nameText;
    @FXML
    HBox btnVbox;
    @FXML
    TextField ageText;
    @FXML
    TextField sexeText;
    @FXML
    TextField jobText;
    @FXML
    VBox experienceVBox;
    @FXML
    VBox competanceVBox;
    @FXML
    Button changeImgbtn;
    @FXML
    Button addExperiancebtn;
    @FXML
    Button addSkillsbtn;
    @FXML
    TextArea aboutTextArea;
    @FXML
    Button editBioBtn;
    @FXML
    Button editAboutBtn;

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
    public void postMsg(){

    }

    @FXML
    public void init(User user) {
        Person person = personDAO.get(user.getId()).get();
        currentUser = person;
        if (person.getId() != session.getId()) {
            Button followbutton = new Button("follow");
            followbutton.getStyleClass().add("postButton");
            Button chatButton = new Button("chat");
            chatButton.getStyleClass().add("postButton");
            btnVbox.getChildren().addAll(followbutton, chatButton);
            changeImgbtn.setDisable(true);
            changeImgbtn.setVisible(false);
            editBioBtn.setDisable(true);
            editBioBtn.setVisible(false);
            editBioBtn.setDisable(true);
            editBioBtn.setVisible(false);
            editAboutBtn.setDisable(true);
            editAboutBtn.setVisible(false);
            addExperiancebtn.setDisable(true);
            addExperiancebtn.setVisible(false);
            addSkillsbtn.setDisable(true);
            addSkillsbtn.setVisible(false);
        }
        nameText.setText(person.getUserName());
        profileImg.setImage(person.getPhoto().getImage());
        Button generateCVbutton = new Button("get pdf");
        generateCVbutton.getStyleClass().add("postButton");
        afficheBio(person);
        afficheabout(person);
        List<Experience> experiences = experienceDAO.getAll(user);
        for (int i = 0; i < experiences.size() - 1; i++) {
            VBox experienceBox=new VBox();
            afficheExperience(experiences.get(i),experienceBox);
            experienceVBox.getChildren().add(experienceBox);
            Separator separator = new Separator();
            experienceVBox.getChildren().add(separator);
        }
        if (experiences.size() > 0) {
            VBox experienceBox=new VBox();
            afficheExperience(experiences.get(experiences.size() - 1),experienceBox);
            experienceVBox.getChildren().add(experienceBox);
        }
        List<Competance> competances = competanceDAO.getAll(user);
        for (int i = 0; i < competances.size() - 1; i++) {
            VBox competanceBox=new VBox();
            afficheCompetance(competances.get(i),competanceBox);
            competanceVBox.getChildren().add(competanceBox);
            Separator separator = new Separator();
            competanceVBox.getChildren().add(separator);
        }
        if (competances.size() > 0) {
            VBox competanceBox=new VBox();
            afficheCompetance(competances.get(competances.size() - 1),competanceBox);
            competanceVBox.getChildren().add(competanceBox);
        }
        // event
        aboutTextArea.setOnKeyPressed(event -> {
            if (event.getCode().isWhitespaceKey() && !event.isShiftDown()) {
                person.setAbout(aboutTextArea.getText());
                try {
                    userService.EditUser(person, person);
                } catch (UserNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                aboutTextArea.setDisable(true);
            }
        });
        aboutTextArea.focusedProperty().addListener((observale, oldValue, newValue) -> {
            if (!newValue) {
                person.setAbout(aboutTextArea.getText());
                try {
                    userService.EditUser(person, person);
                } catch (UserNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                aboutTextArea.setDisable(true);
            }
        });
    }

    private void afficheBio(Person person) {
        ageText.setText(String.valueOf(person.getAge()));
        jobText.setText(person.getJob());
        sexeText.setText(person.getSexe());
    }

    private void afficheabout(Person person) {
    if (person.getAbout()==null) {
        aboutTextArea.setText("");

    }
        aboutTextArea.setText(person.getAbout());
    }

    private void afficheExperience(Experience experience, VBox vBox) {
        Text technologieText = new Text(experience.getTechnologie());
        technologieText.setFont(Font.font("SansSerif", 18));
        technologieText.setFill(Color.WHITE);
        Text missionText = new Text(experience.getMission() + " · " + experience.getType());
        missionText.setFill(Color.WHITE);
        missionText.setFont(Font.font("SansSerif", 14));
        Text dateText = getDateExperience(experience);
        dateText.setFont(null);
        dateText.setFont(Font.font("SansSerif", 14));
        dateText.setFill(Color.WHITE);
        TextArea descriptionArea = new TextArea(experience.getDescription());
        descriptionArea.getStyleClass().add("postTxtField");
        vBox = new VBox(technologieText, missionText, dateText);
        if (currentUser.getId() == session.getId()) {
            addEditButtonExperience(vBox);
        }
    }

    private void afficheCompetance(Competance competance, VBox competanceBox) {
        Text technologieText = new Text(competance.getTechnologie());
        technologieText.setFont(Font.font("SansSerif", 14));
        technologieText.setFill(Color.WHITE);
        Text titreText = new Text(competance.getTitre());
        titreText.setFont(Font.font("SansSerif", 18));
        titreText.setFill(Color.WHITE);
        VBox vBox = new VBox(titreText, technologieText);
        if (currentUser.getId() == session.getId()) {
            addEditButtonCompetance(vBox);
        }
    }

    private void addEditButtonCompetance(VBox vBox) {
        Button editButton = new Button("🖉");
        editButton.getStyleClass().add("postButton");
        vBox.getChildren().add(editButton);
        editButton.setOnAction(ActionEvent -> {
            Stage popUpStage = new Stage();
            popUpStage.initModality(Modality.APPLICATION_MODAL);
            popUpStage.setTitle("Edit Bio");
            FXMLLoader loader = new FXMLLoader(App.class.getResource("editBio.fxml"));
            Scene popUpScreen = new Scene(new Pane());
            try {
                popUpScreen = new Scene(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            EditExperienceController editExperienceController = loader.getController();
            popUpStage.setScene(popUpScreen);
            Experience experience = editExperienceController.getExperience();
            vBox.getChildren().clear();
            afficheExperience(experience, vBox);
        });
    }

    private void addEditButtonExperience(VBox vBox) {
        Button editButton = new Button("🖉");
        editButton.getStyleClass().add("postButton");
        vBox.getChildren().add(editButton);
        editButton.setOnAction(ActionEvent -> {
            Stage popUpStage = new Stage();
            popUpStage.initModality(Modality.APPLICATION_MODAL);
            popUpStage.setTitle("Edit Bio");
            FXMLLoader loader = new FXMLLoader(App.class.getResource("editBio.fxml"));
            Scene popUpScreen = new Scene(new Pane());
            try {
                popUpScreen = new Scene(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            EditExperienceController editExperienceController = loader.getController();
            popUpStage.setScene(popUpScreen);
            Experience experience = editExperienceController.getExperience();
            vBox.getChildren().clear();
            afficheExperience(experience, vBox);
        });
    }

    private Text getDateExperience(Experience experience) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM yyyy");
        String dateString = simpleDateFormat.format(experience.getStartDate());
        String tmp;
        LocalDate localDate = LocalDate.now();
        if (experience.getEndDate() == null) {
            tmp = "present";
        } else {
            localDate = experience.getEndDate();
            tmp = simpleDateFormat.format(experience.getEndDate());
        }
        dateString.concat(" - " + tmp);
        Period period = Period
                .between(experience.getStartDate(), localDate);
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();
        if (years != 0)
            dateString.concat(String.valueOf(years)).concat(" year").concat(years != 1 ? "s" : "").concat(" ");
        if (months != 0)
            dateString.concat(String.valueOf(months)).concat(" month").concat(months != 1 ? "s" : "").concat(" ");
        if (years > 0)
            dateString.concat(String.valueOf(days)).concat(" day").concat(days != 1 ? "s" : "");
        Text dateText = new Text(dateString);
        return dateText;
    }

    @FXML
    void addImage(ActionEvent event) throws UserNotFoundException {
        Media m = MediaChooser.Choose(event);
        if (m.getImage() == null) {
            profileImg.setImage(new Image("user.jpg.png"));
            return;
        }
        if (!m.getMediatype().equals("img")) {
            return;
        }
        profileImg.setImage(m.getImage());
        currentUser.setPhoto(m);
        userService.EditUser(currentUser, currentUser);
    }

    @FXML
    private void editBio() throws IOException, UserNotFoundException {
        Stage popUpStage = new Stage();
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setTitle("Edit Bio");
        FXMLLoader loader = new FXMLLoader(App.class.getResource("editBio.fxml"));
        Scene popUpScreen = new Scene(loader.load());
        EditBioController editBioController = loader.getController();
        popUpStage.setScene(popUpScreen);
        editBioController.setStage(popUpStage);
        editBioController.initialize(currentUser);
        popUpStage.showAndWait();
        if (editBioController.isClosedHow() == false) {
            return;
        }
        currentUser.setAge(editBioController.getAge());
        currentUser.setJob(editBioController.getJob());
        currentUser.setSexe(editBioController.getSexe());
        userService.EditUser(currentUser, currentUser);
        afficheBio(currentUser);
    }

    @FXML
    private void editAbout() {
        aboutTextArea.setDisable(false);

    }
}
