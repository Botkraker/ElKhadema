package Elkhadema.khadema.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import Elkhadema.khadema.DAO.DAOImplemantation.UserDAO;
import Elkhadema.khadema.Service.ServiceImplemantation.FollowServiceImp;
import Elkhadema.khadema.Service.ServiceImplemantation.UserServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.FollowService;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.Service.ServiceImplemantation.PostServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.PostService;
import Elkhadema.khadema.domain.Post;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.Session;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
public class MainPageController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    FollowService followService=new FollowServiceImp();
    UserService userService=new UserServiceImp();
    UserDAO userDAO=new UserDAO();
    @FXML
    ButtonBar listContact;
    @FXML
    VBox vContacts;
    @FXML
    VBox postholder;
	User session= Session.getUser();
	PostService ps= new PostServiceImp();
	@FXML
	TextArea postcontent;
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
    public void createPost() {
    	String content= postcontent.getText();
    	if (content.length()>0) {
    		Post post=new Post(session, content, null, 0, "text", null, 0);
    		ps.makePost(post);
    		System.out.println("post made");
    		showpost(post);
    	}
    }

    @FXML
    public void likePost() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        List<User>follwing= followService.getfollowing(session);
        List<VBox> hBoxs=new ArrayList<>();
        ps.getPostsByUser(session).forEach(t ->showpost(t));
        for (User user : follwing) {
            User tmp=userDAO.get(user.getId()).get();
            Text text = new Text(tmp.getUserName());
            text.setStyle("-fx-text-fill:white;-fx-font-size:15px;");
            ImageView imageView = new ImageView(new Image("file:src//main//resources//images//user.png"));
            imageView.setFitHeight(46);
            imageView.setFitWidth(46);
            HBox hBox = new HBox(imageView ,text);
            hBox.setAlignment(Pos.BASELINE_RIGHT);
            ButtonBar buttonBar=new ButtonBar();
            buttonBar.getButtons().addAll(hBox);
            VBox vBox=new VBox(buttonBar);
            vBox.getStyleClass().add("posts");
            hBoxs.add(vBox);
        }
        vContacts.getChildren().addAll(hBoxs);
        initContacts();
    }
    public void showpost(Post post) {
		ImageView profileimg=new ImageView(new Image("file:src//main//resources//images//user.png"));
		profileimg.setFitHeight(46);
		profileimg.setFitWidth(46);
		Text profilename=new Text(post.getUser().getUserName());
		profilename.setFont(Font.font("SansSerif",15));
		profilename.setTranslateX(5);
		profilename.setFill(Color.WHITE);
		HBox profilebar=new HBox(profileimg,profilename);
		profilebar.setSpacing(5);
		TextArea postscontent =new TextArea(post.getContent());
		postscontent.setDisable(true);
		postscontent.setWrapText(true);
		postscontent.setOpacity(1);
		postscontent.setMinHeight(150);
		postscontent.setFont(Font.font(13));
		postscontent.getStyleClass().add("postTxtField");
		Text likenumber=new Text("0");
		likenumber.setFont(Font.font(16));
		likenumber.setFill(Color.WHITE);
		Button likebutton=new Button("like ♥");
		likebutton.getStyleClass().add("likebutton");
		likebutton.setFont(Font.font(19));
		likebutton.setTextFill(Color.WHITE);
		Text commentnumber=new Text("0");
		commentnumber.setFont(Font.font(16));
		commentnumber.setFill(Color.WHITE);
		Button commentbutton=new Button("comments ☁");
		commentbutton.getStyleClass().add("likebutton");
		commentbutton.setFont(Font.font(19));
		commentbutton.setTextFill(Color.WHITE);
		HBox likeandcommentBox= new HBox(likenumber,likebutton,commentnumber,commentbutton);
		likeandcommentBox.setAlignment(Pos.CENTER_LEFT);
		likeandcommentBox.setStyle("-fx-padding: 0 0 0 10px;");
		HBox.setMargin(likebutton, new Insets(0,11,0,11));
		HBox.setMargin(commentnumber, new Insets(0,5,0,5));
		HBox.setMargin(commentbutton, new Insets(0,5,0,5));
		likeandcommentBox.setTranslateX(5);
		VBox posts= new VBox(profilebar,postscontent,likeandcommentBox);
		VBox lastlayerBox = new VBox(posts);
		lastlayerBox.setFillWidth(true);
		posts.getStyleClass().add("posts");
		posts.setFillWidth(true);
		profilebar.setAlignment(Pos.CENTER_LEFT);
		postholder.getChildren().add(lastlayerBox);

	}

    private void initContacts() {
        List<User> follwing = followService.getfollowing(Session.getUser());
        List<VBox> hBoxs = new ArrayList<>();

        for (User user : follwing) {
            User tmp = userDAO.get(user.getId()).get();
            Text text = new Text(tmp.getUserName());
            text.setStyle("-fx-fill:white;-fx-font-size:15px;");
            ImageView imageView = new ImageView(new Image("file:src//main//resources//images//user.png"));
            imageView.setFitHeight(46);
            imageView.setFitWidth(46);
            HBox hBox = new HBox(imageView, text);
            hBox.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    try {
                        openprofile(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                 });
            hBox.setAlignment(Pos.BASELINE_RIGHT);
            ButtonBar buttonBar = new ButtonBar();
            buttonBar.getButtons().addAll(hBox);
            VBox vBox = new VBox(buttonBar);
            vBox.getStyleClass().add("posts");
            hBoxs.add(vBox);
        }
        vContacts.getChildren().addAll(hBoxs);
    }
    public void openprofile(MouseEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Elkhadema/khadema/mainpage.fxml"));
        ProfileController profileController=loader.getController();
        HBox hbox=(HBox)event.getSource();
        ObservableList<Node>textList= hbox.getChildren();
        String username=new String();
        for (Node node : textList) {
            if (node instanceof Text) {
                username=((Text)node).getText();
            }
        }
        User user=userDAO.Login(username).get();
        profileController.displayProfile(user);
        root =loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
