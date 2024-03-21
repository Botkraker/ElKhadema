package Elkhadema.khadema.controller;

import java.net.URL;
import java.util.ResourceBundle;

import Elkhadema.khadema.Service.ServiceImplemantation.PostServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.PostService;
import Elkhadema.khadema.domain.Post;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class MainPageController {
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
    	}
    }
    
    @FXML
    public void likePost() {
    	
    }

}
