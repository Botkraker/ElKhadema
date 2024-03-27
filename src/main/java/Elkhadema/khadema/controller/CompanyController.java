package Elkhadema.khadema.controller;

import java.io.IOException;
import java.util.List;

import Elkhadema.khadema.App;
import Elkhadema.khadema.DAO.DAOImplemantation.CompanyDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.CompetanceDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.ExperienceDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.PersonDAO;
import Elkhadema.khadema.Service.ServiceImplemantation.FollowServiceImp;
import Elkhadema.khadema.Service.ServiceImplemantation.GenerateCVServiceImp;
import Elkhadema.khadema.Service.ServiceImplemantation.JobServiceImp;
import Elkhadema.khadema.Service.ServiceImplemantation.UserServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.FollowService;
import Elkhadema.khadema.Service.ServiceInterfaces.GenerateCVService;
import Elkhadema.khadema.Service.ServiceInterfaces.JobService;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.domain.Competance;
import Elkhadema.khadema.domain.Experience;
import Elkhadema.khadema.domain.JobOffre;
import Elkhadema.khadema.domain.Media;
import Elkhadema.khadema.domain.Person;
import Elkhadema.khadema.domain.Company;
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

public class CompanyController extends NavbarController {
    User session = Session.getUser();
    Company company;
    Person currentUser;
    PersonDAO personDAO = new PersonDAO();
    CompanyDAO companyDAO = new CompanyDAO();
    JobService jobService=new JobServiceImp();
    FollowService followService=new FollowServiceImp();
    UserService userService = new UserServiceImp();
    ExperienceDAO experienceDAO = new ExperienceDAO();
    CompetanceDAO competanceDAO = new CompetanceDAO();
    GenerateCVService cvService = new GenerateCVServiceImp();
    @FXML
    Text motoField;
    @FXML
    Text smallDesText;
    @FXML
    Text locationText;
    @FXML
    Text numberFollowersText;
    @FXML
    Text numberEmployeesText;
    @FXML
    Text websiteText;
    @FXML
    Text industryText;
    @FXML
    Text founded;
    @FXML
    Text description;

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
    Button generateCVbutton;

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
    public void init(User user) {
        company = companyDAO.get(user.getId()).get();
        // TODO remove later
        Person person = new Person(0, "null", "null");
        currentUser = person;
        if (company.getId() != session.getId()) {
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
        profileImg.getStyleClass().add("round-image");

        afficheBio(company);
        afficheabout(company);

        List<JobOffre> joboffres=jobService.getAllJobOffresByCompany(company);
        joboffres.stream().limit(2).forEach(joboffre -> {
            VBox jobOffreBox = new VBox();
            afficheJobOffre(joboffre, jobOffreBox);
            experienceVBox.getChildren().add(jobOffreBox);
            Separator separator = new Separator();
            competanceVBox.getChildren().add(separator);
        });
        //TODO continue after this

        List<Competance> competances = competanceDAO.getAll(user);
        for (int i = 0; i < competances.size() - 1; i++) {
            VBox competanceBox = new VBox();
            afficheCompetance(competances.get(i), competanceBox);
            competanceVBox.getChildren().add(competanceBox);
            Separator separator = new Separator();
            competanceVBox.getChildren().add(separator);
        }
        if (competances.size() > 0) {
            VBox competanceBox = new VBox();
            afficheCompetance(competances.get(competances.size() - 1), competanceBox);
            competanceVBox.getChildren().add(competanceBox);
        }
        // event
        aboutTextArea.setOnKeyPressed(event -> {
            if (event.getCode().isWhitespaceKey() && !event.isShiftDown()) {
                person.setAbout(aboutTextArea.getText());
                try {
                    userService.EditUser(person, person);
                } catch (UserNotFoundException e) {
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
                    e.printStackTrace();
                }
                aboutTextArea.setDisable(true);
            }
        });
    }
    private void afficheJobOffre(JobOffre jobOffre,VBox vBox){

    }
    private void afficheBio(Company company) {
        if(company.getMoto().strip().isEmpty()){
            motoField.setVisible(false);
        }
        else
            motoField.setText(company.getMoto());
        String description=company.getIndustry().concat(" · ");
        description=description.concat(company.getAddress()).concat(" · ");
        description=description.concat(String.valueOf(followService.getFollowers(company).size())).concat(" · ");
        description=description.concat(String.valueOf(company.getComapnySize()));
        smallDesText.setText(description);
        smallDesText.getStyleClass().add("disabled-text");
        motoField.getStyleClass().add("disabled-text");
    }

    private void afficheabout(Company company ) {
        if (company.getDescription() == null) {
            aboutTextArea.setText("");

        }
        aboutTextArea.setText(company.getDescription());
    }

    private void afficheExperience(Experience experience, VBox vBox) {
        Text technologieText = new Text(experience.getTechnologie());
        technologieText.setFont(Font.font("SansSerif", 18));
        technologieText.setFill(Color.WHITE);
        HBox titleBox = new HBox(technologieText);
        Text missionText = new Text(experience.getMission() + " · " + experience.getType());
        missionText.setFill(Color.WHITE);
        missionText.setFont(Font.font("SansSerif", 14));

        Text dateText = new Text(experience.getDateExperience());
        dateText.setFont(Font.font("SansSerif", 14));
        dateText.setFill(Color.WHITE);

        TextArea descriptionArea = new TextArea(experience.getDescription());
        descriptionArea.getStyleClass().add("postTxtField");

        VBox innerVBox = new VBox(titleBox, missionText, dateText);

        if (currentUser.getId() == session.getId()) {
            addEditButtonExperience(titleBox, experience);
        }

        vBox.getChildren().add(innerVBox); // Add the new VBox to the provided vBox
    }

    private void afficheCompetance(Competance competance, VBox competanceBox) {
        Text technologieText = new Text(competance.getTechnologie());
        technologieText.setFont(Font.font("SansSerif", 14));
        technologieText.setFill(Color.WHITE);

        VBox innerVBox = new VBox();
        if (currentUser.getId() == session.getId()) {
            addEditButtonCompetance(innerVBox, competance);
        } else {
            Text titreText = new Text(competance.getTitre());
            titreText.setFont(Font.font("SansSerif", 18));
            titreText.setFill(Color.WHITE);
            innerVBox.getChildren().add(titreText);
        }
        innerVBox.getChildren().add(technologieText);
        competanceVBox.getChildren().add(innerVBox);
    }

    private void addEditButtonCompetance(VBox vBox, Competance competance) {
        Button editButton = new Button("🖉");
        editButton.getStyleClass().add("postButton");
        HBox hBox = new HBox();
        Text titreText = new Text(competance.getTitre());
        titreText.setFont(Font.font("SansSerif", 18));
        titreText.setFill(Color.WHITE);
        hBox.getChildren().addAll(titreText, editButton);
        vBox.getChildren().add(hBox);
        editButton.setOnAction(ActionEvent -> {
            Stage popUpStage = new Stage();
            popUpStage.initModality(Modality.APPLICATION_MODAL);
            popUpStage.setTitle("Edit competance");
            FXMLLoader loader = new FXMLLoader(App.class.getResource("editCompetance.fxml"));
            Scene popUpScreen = new Scene(new Pane());
            try {
                popUpScreen = new Scene(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            EditCompetanceController editCompetanceController = loader.getController();
            popUpStage.setScene(popUpScreen);
            editCompetanceController.setStage(popUpStage);
            editCompetanceController.initialize(competance);
            popUpStage.showAndWait();
            Competance newCompetance = editCompetanceController.getCompetance();
            vBox.getChildren().clear();
            afficheCompetance(competance, vBox);
            competanceDAO.update(competance, newCompetance);
        });
    }

    private void addEditButtonExperience(HBox hBox, Experience experience) {
        Button editButton = new Button("🖉");
        editButton.getStyleClass().add("postButton");
        hBox.getChildren().add(editButton);
        editButton.setOnAction(ActionEvent -> {
            Stage popUpStage = new Stage();
            popUpStage.initModality(Modality.APPLICATION_MODAL);
            popUpStage.setTitle("Edit experience");
            FXMLLoader loader = new FXMLLoader(App.class.getResource("editExperience.fxml"));
            Scene popUpScreen = new Scene(new Pane());
            try {
                popUpScreen = new Scene(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            EditExperienceController editExperienceController = loader.getController();
            popUpStage.setScene(popUpScreen);
            editExperienceController.setStage(popUpStage);
            editExperienceController.initialize(experience);

            popUpStage.showAndWait();

            Experience newExperience = editExperienceController.getExperience();
            if (editExperienceController.choix == false) {
                return;
            }
            VBox parentBox = ((VBox) ((VBox) hBox.getParent()));
            parentBox.getChildren().clear();
            afficheExperience(newExperience, parentBox);
            experienceDAO.update(experience, newExperience);
        });
    }

    @FXML
    public void addExperience() throws IOException {
        VBox vBox = new VBox();
        Stage popUpStage = new Stage();
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setTitle("Edit Bio");
        FXMLLoader loader = new FXMLLoader(App.class.getResource("editExperience.fxml"));
        Scene popUpScreen = new Scene(loader.load());
        EditExperienceController editExperienceController = loader.getController();
        popUpStage.setScene(popUpScreen);
        editExperienceController.setStage(popUpStage);
        Experience experience = null;
        editExperienceController.initialize(experience);
        popUpStage.showAndWait();
        if (editExperienceController.choix == false) {
            return;
        }
        experience = editExperienceController.getExperience();
        afficheExperience(experience, vBox);
        experienceDAO.save(experience, currentUser);
        experienceVBox.getChildren().add(vBox);
    }

    @FXML
    public void addCompetance() throws IOException {
        VBox vBox = new VBox();
        Stage popUpStage = new Stage();
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setTitle("Edit Bio");
        FXMLLoader loader = new FXMLLoader(App.class.getResource("editCompetance.fxml"));
        Scene popUpScreen = new Scene(loader.load());
        EditCompetanceController editCompetanceController = loader.getController();
        popUpStage.setScene(popUpScreen);
        editCompetanceController.setStage(popUpStage);
        Competance competance = null;
        editCompetanceController.initialize(competance);
        popUpStage.showAndWait();
        if (editCompetanceController.choix == false) {
            return;
        }
        competance = editCompetanceController.getCompetance();
        afficheCompetance(competance, vBox);
        competanceDAO.save(competance, currentUser);
        competanceVBox.getChildren().add(vBox);
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
        //afficheBio(currentUser);
    }

    @FXML
    private void editAbout() {
        aboutTextArea.setDisable(false);

    }
}
