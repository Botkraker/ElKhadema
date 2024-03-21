package Elkhadema.khadema.controller;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.scene.control.TextArea;
public class MainPageController implements Initializable {
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
		Text likenumber=new Text(""+post.getCountReactions()); 
		likenumber.setFont(Font.font(16));
		likenumber.setFill(Color.WHITE);
		Button likebutton=new Button("like ♥"); 
		likebutton.getStyleClass().add("likebutton");
		likebutton.setOnAction(e -> likepost(post));
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
    public void likepost(Post post) {
		
	}

}
