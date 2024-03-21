package Elkhadema.khadema.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;


import Elkhadema.khadema.DAO.DAOImplemantation.UserDAO;
import Elkhadema.khadema.Service.ServiceImplemantation.FollowServiceImp;
import Elkhadema.khadema.Service.ServiceImplemantation.UserServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.FollowService;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.Service.ServiceImplemantation.PostServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.PostService;
import Elkhadema.khadema.domain.Post;
import Elkhadema.khadema.domain.Reaction;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.Session;
import javafx.collections.ObservableList;
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
    FollowService followService = new FollowServiceImp();
    UserService userService = new UserServiceImp();
    UserDAO userDAO = new UserDAO();
    @FXML
    ButtonBar listContact;
    @FXML
    VBox vContacts;
    @FXML
    VBox postholder;
    User session = Session.getUser();
    PostServiceImp ps = new PostServiceImp();
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
        String content = postcontent.getText();
        if (content.length() > 0) {
            Post post = new Post(session, content, null, 0, "text", null, 0);
            ps.makePost(post);
            System.out.println("post made");
            resetfeed();
        }
    }

    @FXML
    public void likePost() {

    }
    public void resetfeed() {
    	postholder.getChildren().clear();
        ps.feed().forEach(t -> showpost(t));;
	}
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        initContacts();
        resetfeed();
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
		Text likenumber= new Text(""+ps.getPostReactions(post).size());
		likenumber.setFont(Font.font(16));
		likenumber.setFill(Color.WHITE);
		Button likebutton=new Button("like ♥");
		AtomicBoolean isliked = new AtomicBoolean(false);
		likebutton.setOnAction(event -> {likeapost(post,isliked,likenumber);});
		likebutton.getStyleClass().add("likebutton");
		likebutton.setFont(Font.font(19));
		likebutton.setTextFill(Color.WHITE);
		Text commentnumber=new Text(""+ps.getPostComments(post).size());
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
		HBox.setMargin(profileimg,new Insets(0,5,0,8));
		HBox.setMargin(commentnumber, new Insets(0,5,0,5));
		HBox.setMargin(commentbutton, new Insets(0,5,0,5));
		likeandcommentBox.setTranslateX(5);
		VBox posts= new VBox(profilebar,postscontent,likeandcommentBox);
		VBox lastlayerBox = new VBox(posts);
		lastlayerBox.setFillWidth(true);
		VBox.setMargin(posts,new Insets(2.5f,0,2.5f,0));
		posts.getStyleClass().add("posts");
		posts.setFillWidth(true);
		profilebar.setAlignment(Pos.CENTER_LEFT);
		postholder.getChildren().add(lastlayerBox);

	}
    public void likeapost(Post post,AtomicBoolean isliked,Text likenumber) {
    	if(isliked.get()) {
    		ps.getPostReactions(post).stream().filter(t -> t.getUser().getUserName().compareTo(session.getUserName())==0).forEach( t -> ps.removeReactionFromPost(post, t));
    		likenumber.setText(""+ps.getPostReactions(post).size());
    		isliked.set(false);
    	}
    	else {
    
    		Reaction r=new Reaction(session, post, "like",new Date());
    		ps.addReactionPost(post, r);
    		likenumber.setText(""+ps.getPostReactions(post).size());
			isliked.set(true);;
		}
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
            imageView.setTranslateX(5);
            text.setTranslateX(10);
            HBox hBox = new HBox(imageView, text);
            hBox.setPadding(new Insets(5, 0, 5, 0));
            hBox.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                try {
                    openprofile(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            hBox.setAlignment(Pos.CENTER_LEFT);
            VBox vBox = new VBox(hBox);
            vBox.getStyleClass().add("posts");
            hBoxs.add(vBox);
        }
        vContacts.getChildren().addAll(hBoxs);
    }

    public void openprofile(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Elkhadema/khadema/mainpage.fxml"));
        ProfileController profileController = loader.getController();
        HBox hbox = (HBox) event.getSource();
        ObservableList<Node> textList = hbox.getChildren();
        String username = new String();
        for (Node node : textList) {
            if (node instanceof Text) {
                username = ((Text) node).getText();
            }
        }
        User user = userDAO.Login(username).get();
        profileController.displayProfile(user);
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
