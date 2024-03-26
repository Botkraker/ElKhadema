package Elkhadema.khadema.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import Elkhadema.khadema.App;
import Elkhadema.khadema.DAO.DAOImplemantation.UserDAO;
import Elkhadema.khadema.Service.ServiceImplemantation.FollowServiceImp;
import Elkhadema.khadema.Service.ServiceImplemantation.UserServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.FollowService;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.Service.ServiceImplemantation.PostServiceImp;
import Elkhadema.khadema.domain.Media;
import Elkhadema.khadema.domain.Post;
import Elkhadema.khadema.domain.Reaction;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.MediaChooser;
import Elkhadema.khadema.util.Session;
import javafx.application.Platform;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;

public class MainPageController implements Initializable {
    public void customizescrollpane() {
        CC.getStyleClass().add("custom-scroll-pane");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(0, 0, 0, 0.3));
        dropShadow.setWidth(6);
        dropShadow.setHeight(6);
        dropShadow.setRadius(6);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(0);
        dropShadow.setSpread(0);
        dropShadow.setBlurType(BlurType.GAUSSIAN);
        CC.setEffect(dropShadow);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        postcontent.setWrapText(true);
        customizescrollpane();
        initContacts();
        Platform.runLater(() -> {
            resetfeed();
        });
    }

    private Stage stage;
    private Scene scene;
    private Parent root;
    FollowService followService = new FollowServiceImp();
    UserService userService = new UserServiceImp();
    UserDAO userDAO = new UserDAO();
    @FXML
    private ScrollPane CC;

    @FXML
    ButtonBar listContact;

    @FXML
    VBox vContacts;
    @FXML
    HBox mother;
    @FXML
    VBox postholder;
    User session = Session.getUser();
    PostServiceImp ps = new PostServiceImp();
    @FXML
    TextArea postcontent;
    @FXML
    VBox mothersofmother;
    @FXML
    private HBox vidcontainer;
    List<Media> attachedMedias = new ArrayList<Media>();

    @FXML
    public void goChat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Elkhadema/khadema/chatroom.fxml"));
        ChatRoomController chatRoomController = loader.getController();
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goHome() {

    }

    @FXML
    private HBox HboxforAttachments;

    @FXML
    private Button buttontoaddattach;

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
            post.setPostMedias(attachedMedias);
            ps.makePost(post);
            System.out.println("post made");
            attachedMedias = new ArrayList<Media>();
            resetfeed();
            try {
                HboxforAttachments.getChildren().clear();
            } catch (Exception e) {
                System.out.println(e);
            }
            postcontent.setText("");
        }
    }

    @FXML
    public void likePost() {

    }

    boolean loadingMorePosts = false;
    int postindex = 15;
    int maxPosts = 20;
    int sum = 0;
    int loadPosts = 0;

    List<Post> posts = ps.feed();

    public void resetfeed() {
        postholder.getChildren().clear();
        initPostShow();
        // TODO still working on it
        CC.vvalueProperty().addListener((observable, oldValue, newValue) -> {
            double scrollValue = newValue.doubleValue();
            if (scrollValue >= 1 && postindex < posts.size()) {
                posts.stream().skip(postindex).limit(5).map(t -> showpost(t)).forEach(t -> {
                    if (maxPosts <= loadPosts) {
                        postholder.getChildren().remove(0);
                        loadPosts--;
                    }
                    postholder.getChildren().add(t);
                    loadPosts++;
                    postindex++;
                    Platform.runLater(() -> {CC.setVvalue(0.8);});
                });
            }
            //TODO change it lags like hell when you scroll up
            if (scrollValue <= 0.2 && loadPosts >15) {
                addPostTop(posts.get(postindex - loadPosts));

            }
        });
    }

    public void addPostTop(Post post) {
        postholder.getChildren().add(0, showpost(post));
        loadPosts++;
        if (loadPosts>=20) {
            postholder.getChildren().remove(postholder.getChildren().size()-1);
            loadPosts--;
            postindex--;
        }
    }

    public void initPostShow() {
        List<Post> posts = ps.feed();
        posts.stream().limit(postindex).map(t -> showpost(t)).forEach(t -> {
            postholder.getChildren().add(t);
            loadPosts++;
        });
    }

    public VBox showpost(Post post) {
        Image image = post.getUser().getPhoto().getImage();
        ImageView profileimg;
        if (image == null) {
            profileimg = new ImageView(new Image("file:src//main//resources//images//user.png"));
        } else {
            profileimg = new ImageView(image);
        }
        profileimg.setStyle("-fx-border-radius: 20px");
        profileimg.setFitWidth(46);
        profileimg.setPreserveRatio(true);
        Text profilename = new Text(post.getUser().getUserName());
        profilename.setFont(Font.font("SansSerif", 15));
        profilename.setTranslateX(5);
        profilename.setFill(Color.WHITE);
        HBox profilebar = new HBox(profileimg, profilename);
        profilebar.setSpacing(5);
        Text postscontent = new Text(post.getContent());
        postscontent.setDisable(true);
        postscontent.setFill(Color.WHITE);
        postscontent.setOpacity(1);
        postscontent.setFont(Font.font(13));
        postscontent.getStyleClass().add("postTxtField");
        postscontent.setStyle("-fx-border-width: 0;");
        List<HBox> displayedimges = displayimages(post);
        displayedimges.forEach(t -> {
            t.setSpacing(5);
            t.setAlignment(Pos.TOP_CENTER);
        });
        VBox iMGHOLDER = new VBox(displayedimges.toArray(new HBox[0]));
        iMGHOLDER.getStyleClass().add("postTxtField");
        iMGHOLDER.setAlignment(Pos.CENTER);
        iMGHOLDER.setSpacing(5);
        try {
            MediaPlayer mediaPlayer = post.getPostMedias().stream().filter(t -> t.getMediatype().equals("vid"))
                    .map(Elkhadema.khadema.domain.Media::getVideo).findFirst().get();
            MediaView mediaView = new MediaView(mediaPlayer);
            iMGHOLDER.getChildren().add(mediaView);
            System.out.println("fama" + post.getContent());
        } catch (Exception e) {
            System.out.println(e);
        }
        Text likenumber = new Text("" + ps.getPostReactions(post).size());
        likenumber.setFont(Font.font(16));
        likenumber.setFill(Color.WHITE);
        Button likebutton = new Button("like ♥");
        AtomicBoolean isliked = new AtomicBoolean(false);
        likebutton.setOnAction(event -> {
            likeapost(post, isliked, likenumber);
        });
        likebutton.getStyleClass().add("likebutton");
        likebutton.setFont(Font.font(19));
        likebutton.setTextFill(Color.WHITE);
        Text commentnumber = new Text("" + ps.getPostComments(post).size());
        commentnumber.setFont(Font.font(16));
        commentnumber.setFill(Color.WHITE);
        Button commentbutton = new Button("comments ☁");
        commentbutton.getStyleClass().add("likebutton");
        commentbutton.setFont(Font.font(19));
        commentbutton.setTextFill(Color.WHITE);
        commentbutton.setOnAction(event -> {
            try {
                commentToPost(post);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        HBox likeandcommentBox = new HBox(likenumber, likebutton, commentnumber, commentbutton);
        likeandcommentBox.setAlignment(Pos.CENTER_LEFT);
        likeandcommentBox.setStyle("-fx-padding: 0 0 0 10px;");
        HBox.setMargin(likebutton, new Insets(0, 11, 0, 11));
        HBox.setMargin(profileimg, new Insets(0, 5, 0, 8));
        HBox.setMargin(commentnumber, new Insets(0, 5, 0, 5));
        HBox.setMargin(commentbutton, new Insets(0, 5, 0, 5));
        likeandcommentBox.setTranslateX(5);
        VBox posts = new VBox(profilebar, postscontent, iMGHOLDER, likeandcommentBox);
        VBox lastlayerBox = new VBox(posts);
        lastlayerBox.setFillWidth(true);
        VBox.setMargin(postscontent, new Insets(5, 0, 5, 10));
        VBox.setMargin(posts, new Insets(2.5f, 0, 2.5f, 0));
        posts.getStyleClass().add("posts");
        System.out.println(posts.getWidth());
        System.out.println(postholder.getWidth());

        postscontent.setWrappingWidth(postholder.getWidth());
        posts.setMinWidth(CC.getWidth() - 50);
        CC.widthProperty().addListener((observable, oldValue, newValue) -> {
            // Update the wrapping width of the Text node
            System.out.println(CC.getWidth());
            posts.setMinWidth(CC.getWidth() - 50);
            postscontent.setWrappingWidth(CC.getWidth());
        });

        posts.setFillWidth(true);
        profilebar.setAlignment(Pos.CENTER_LEFT);
        return posts;
    }

    public void commentToPost(Post post) throws IOException {
        CommentsPageController.setCommentedpost(post);
        App.setRoot("comment");
    }

    public List<HBox> displayimages(Post post) {
        List<Image> imgs = post.getPostMedias().stream().map(Elkhadema.khadema.domain.Media::getImage)
                .filter(t -> t != null).collect(Collectors.toList());
        List<HBox> imgsview = new ArrayList<HBox>();
        List<ImageView> imgViews = new ArrayList<ImageView>();
        ImageView tempimg;
        int displayforthree = imgs.size() / 3;
        for (int i = 0; i < displayforthree; i++) {
            for (int j = i; j < i + 3; j++) {
                tempimg = new ImageView(imgs.get(j));
                tempimg.setFitWidth(CC.getWidth() / 3 - 50);
                tempimg.setPreserveRatio(true);
                imgViews.add(tempimg);
                HBox.setHgrow(tempimg, javafx.scene.layout.Priority.ALWAYS);
            }
            imgsview.add(new HBox(imgViews.toArray(new ImageView[0])));
        }
        imgViews = new ArrayList<ImageView>();
        for (int i = displayforthree * 3; i < imgs.size(); i++) {
            tempimg = new ImageView(imgs.get(i));
            tempimg.setFitWidth((CC.getWidth() - 50) / (imgs.size() - displayforthree * 3));
            tempimg.setPreserveRatio(true);

            imgViews.add(tempimg);
        }
        imgsview.add(new HBox(imgViews.toArray(new ImageView[0])));
        return imgsview;
    }

    public void likeapost(Post post, AtomicBoolean isliked, Text likenumber) {
        if (isliked.get()) {
            ps.getPostReactions(post).stream()
                    .filter(t -> t.getUser().getUserName().compareTo(session.getUserName()) == 0)
                    .forEach(t -> ps.removeReactionFromPost(post, t));
            likenumber.setText("" + ps.getPostReactions(post).size());
            isliked.set(false);
        } else {

            Reaction r = new Reaction(session, post, "like", new Date());
            ps.addReactionPost(post, r);
            likenumber.setText("" + ps.getPostReactions(post).size());
            isliked.set(true);
            ;
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
                    openprofile(event, tmp);
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

    @FXML
    void AddMediabutton(ActionEvent event) {
        Media m = MediaChooser.Choose(event);
        if (m.getMediatype().equals("img")) {
            attachedMedias.add(m);
            ImageView img = new ImageView(m.getImage());
            HboxforAttachments.getChildren().add(img);
            HboxforAttachments.getChildren().forEach(t -> ((ImageView) t)
                    .setFitWidth(HboxforAttachments.getWidth() / (double) attachedMedias.size() / 3));
            img.setPreserveRatio(true);
        } else {
            try {
                MediaPlayer mediaPlayer = m.getVideo();
                MediaView mediaView = new MediaView(mediaPlayer);
                attachedMedias.add(m);
                vidcontainer.getChildren().add(mediaView);
                mediaView.setFitWidth(vidcontainer.getWidth() / 2);
                mediaView.setPreserveRatio(true);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void openprofile(MouseEvent event, User tmp) throws IOException {
        User user = tmp;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Elkhadema/khadema/mainpage.fxml"));
        ResumeController profileController = loader.getController();
        profileController.init(user);
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
