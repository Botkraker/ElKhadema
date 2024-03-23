package Elkhadema.khadema.controller;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import Elkhadema.khadema.DAO.DAOImplemantation.UserDAO;
import Elkhadema.khadema.Service.ServiceImplemantation.FollowServiceImp;
import Elkhadema.khadema.Service.ServiceImplemantation.MessageServiceIMP;
import Elkhadema.khadema.Service.ServiceImplemantation.UserServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.FollowService;
import Elkhadema.khadema.Service.ServiceInterfaces.MessageService;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.domain.Message;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.Session;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * ChatRoomController
 */
public class ChatRoomController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private User currentMessageReciver;
    private long lastMessageId = 0;
    private MessageService messageService = new MessageServiceIMP();
    private FollowService followService = new FollowServiceImp();
    private UserDAO userDAO = new UserDAO();
    private UserService userService = new UserServiceImp();
    private List<User> contacts = followService.getfollowing(Session.getUser());
    private int parentMessageId;

    @FXML
    VBox vContacts;
    @FXML
    TextArea messageText;
    @FXML
    Button sendBtn;
    @FXML
    VBox messageVBox;
    @FXML
    ScrollPane messagePane;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        messageText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.trim().isEmpty()) {
                sendBtn.setDisable(false);
            } else {
                sendBtn.setDisable(true);
            }
        });
        initContacts();
        try {
            currentMessageReciver = contacts.get(0);
            messageVBox.getChildren().clear();
            loadMessages(currentMessageReciver);
        } catch (Exception e) {
            currentMessageReciver = null;
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            List<Message> messages = messageService.chat(Session.getUser(), currentMessageReciver);
            messages.stream().dropWhile(message -> message.getId() != lastMessageId)
                    .skip(1)
                    .forEach(message -> afficheMessage(message));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void loadMessages(User user) {
        List<Message> messages = messageService.chat(Session.getUser(), user);
        if (messages.isEmpty()) {
            return;
        }
        for (Message message : messages) {
            afficheMessage(message);
        }
        Platform.runLater(() -> messagePane.setVvalue(1.0));
    }

    private void afficheMessage(Message message) {
        boolean tmp = messagePane.getVvalue() == 1.0;
        ImageView imageView = new ImageView(new Image("file:src//main//resources//images//user.png"));
        imageView.setFitHeight(46);
        imageView.setFitWidth(46);
        HBox hBox;
        if (message.getSender().equals(Session.getUser())) {
            Text text = new Text(Session.getUser().getUserName());
            text.setFill(Color.WHITE);
            text.setFont(new Font("SansSerif", 15));
            text.setTranslateX(-Session.getUser().getUserName().length() + 3);
            hBox = new HBox(text, imageView);
            hBox.setAlignment(Pos.CENTER_RIGHT);
        } else {
            Text text = new Text(currentMessageReciver.getUserName());
            text.setFill(Color.WHITE);
            text.setTranslateX(currentMessageReciver.getUserName().length() - 3);
            text.setFont(new Font("SansSerif", 15));
            hBox = new HBox(imageView, text);
            hBox.setAlignment(Pos.CENTER_LEFT);
        }
        TextArea contentText = new TextArea(message.getContent());
        contentText.setDisable(true);
        contentText.setWrapText(true);
        contentText.setOpacity(1);
        contentText.setStyle(
                "-fx-font-family: 'Helvetica';" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 10px;" +
                        "-fx-background-color: #f5f6f7;" +
                        "-fx-border-width: 1px;" +
                        "-fx-text-fill: black;" +
                        "-fx-background : transparent;" +
                        "-fx-background-radius: 10px;");
        VBox vBox = new VBox(hBox, contentText);
        messageVBox.getChildren().add(vBox);
        if (message.getSender() != Session.getUser()) {
            messageService.MessageRead(message, Session.getUser());
        }
        lastMessageId = message.getId();
        Platform.runLater(() -> {
            if (tmp)
                messagePane.setVvalue(1.0);
        });
    }

    private void initContacts() {
        List<VBox> hBoxs = new ArrayList<>();
        contacts = contacts.stream().map(user -> userDAO.get(user.getId()).get()).collect(Collectors.toList());
        for (User user : contacts) {
            Text text = new Text(user.getUserName());
            text.setStyle("-fx-fill:white;-fx-font-size:15px;");
            ImageView imageView = new ImageView(new Image("file:src//main//resources//images//user.png"));
            imageView.setFitHeight(46);
            imageView.setFitWidth(46);
            imageView.setTranslateX(5);
            text.setTranslateX(10);
            HBox hBox = new HBox(imageView, text);
            hBox.setPadding(new Insets(5, 0, 5, 0));
            hBox.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                currentMessageReciver = user;
                messageVBox.getChildren().clear();
                loadMessages(currentMessageReciver);
            });
            // add event handler
            hBox.setAlignment(Pos.CENTER_LEFT);
            VBox vBox = new VBox(hBox);
            vBox.getStyleClass().add("posts");
            vBox.setCursor(Cursor.HAND);
            vBox.setOnMouseEntered(e -> vBox.setStyle("-fx-background-color: #0099ff93; -fx-text-fill: white;"));
            vBox.setOnMouseExited(e -> vBox.setStyle("-fx-background-color:#0000002a ; -fx-text-fill: white;"));
            hBoxs.add(vBox);
        }
        vContacts.getChildren().addAll(hBoxs);
    }

    public void goHome() {

    }

    public void goJobsList() {

    }

    public void goNotifications() {

    }

    public void goResume() {

    }

    public void logout() {
        userService.logOut(Session.getUser());
    }

    @FXML
    public void postMsg() {
        Message message = new Message(0, Session.getUser(), messageText.getText(), null, parentMessageId);
        messageService.sendMessage(currentMessageReciver, message);
        afficheMessage(message);
        messageText.setText("");

    }

    public void returnHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Elkhadema/khadema/mainpage.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}