package Elkhadema.khadema.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ResumeController {
    User session = Session.getUser();
    PersonDAO personDAO = new PersonDAO();
    UserService userService = new UserServiceImp();
    ExperienceDAO experienceDAO = new ExperienceDAO();
    CompetanceDAO competanceDAO = new CompetanceDAO();
    User currentUser;
    User displayUser;
    @FXML
    ImageView profileImg;
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
    @FXML
    VBox experienceVBox;
    @FXML
    VBox competanceVBox;
    @FXML
    Button changeImg;

    @FXML
    public void init(User user) {
        currentUser = user;
        Person person = personDAO.get(user.getId()).get();
        if (person.getUserName() != session.getUserName()) {
            displayUser = person;
            Button followbutton = new Button("follow");
            followbutton.getStyleClass().add("postbtn");
            Button chatButton = new Button("chat");
            chatButton.getStyleClass().add("postbtn");
            btnVbox.getChildren().addAll(followbutton, chatButton);
            changeImg.setDisable(true);
            changeImg.setVisible(true);
        }
        Button generateCVbutton = new Button("get pdf");
        generateCVbutton.getStyleClass().add("postbtn");
        ageText.setText(String.valueOf(person.getAge()));
        jobText.setText(person.getJob());
        sexeText.setText(person.getSexe());
        afficheabout(person);
        List<Experience> experiences = experienceDAO.getAll(user);
        for (int i = 0; i < experiences.size() - 1; i++) {
            afficheExperience(experiences.get(i));
            Separator separator = new Separator();
            experienceVBox.getChildren().add(separator);
        }
        if (experiences.size() > 0) {
            afficheExperience(experiences.get(experiences.size() - 1));
        }
        List<Competance> competances = competanceDAO.getAll(user);
        for (int i = 0; i < competances.size() - 1; i++) {
            afficheCompetance(competances.get(i));
            Separator separator = new Separator();
            competanceVBox.getChildren().add(separator);
        }
        if (competances.size() > 0) {
            afficheCompetance(competances.get(competances.size() - 1));
        }
    }

    public void afficheabout(Person person) {
        TextArea textArea = new TextArea(person.getAbout());
        textArea.getStyleClass().add("postTxtField");
        experienceVBox.getChildren().add(textArea);
    }

    public void afficheExperience(Experience experience) {
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
        VBox vBox = new VBox(technologieText, missionText, dateText);
        if (currentUser.getId() == session.getId()) {
            addEditButtonExperience();
        }
        experienceVBox.getChildren().add(vBox);
    }

    public void afficheCompetance(Competance competance) {
        Text technologieText = new Text(competance.getTechnologie());
        technologieText.setFont(Font.font("SansSerif", 14));
        technologieText.setFill(Color.WHITE);
        Text titreText = new Text(competance.getTitre());
        titreText.setFont(Font.font("SansSerif", 18));
        titreText.setFill(Color.WHITE);
        VBox vBox = new VBox(titreText, technologieText);
        if (currentUser.getId() == session.getId()) {
            addEditButtonCompetance();
        }
        competanceVBox.getChildren().add(vBox);
    }

    private void addEditButtonCompetance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addEditButtonCompetance'");
    }

    private void addEditButtonExperience() {
    }

    private Text getDateExperience(Experience experience) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM yyyy");
        String dateString = simpleDateFormat.format(experience.getStartDate());
        String tmp;
        LocalDate localDate = LocalDate.now();
        if (experience.getEndDate() == null) {
            tmp = "present";
        } else {
            localDate = experience.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            tmp = simpleDateFormat.format(experience.getEndDate());
        }
        dateString.concat(" - " + tmp);
        Period period = Period
                .between(experience.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), localDate);
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
        // TODO later pls make take a image instead of a string
        displayUser.setPhoto(m);
        userService.EditUser(displayUser, displayUser);
    }

    private void openPopUp() throws IOException {
        Stage popUpStage = new Stage();
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setTitle("Edit Bio");
        Scene popUpScreen=new Scene((new FXMLLoader(App.class.getResource("editBio.fxml"))).load(), 200, 200);
        Button closeButton = new Button("Close Pop-up");
        closeButton.setOnAction(e -> popUpStage.close());
        
        popUpStage.setScene(popUpScreen);
        popUpStage.showAndWait();
    }
}
