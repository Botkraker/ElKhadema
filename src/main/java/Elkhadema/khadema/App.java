package Elkhadema.khadema;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

import Elkhadema.khadema.Service.ServiceImplemantation.UserServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.util.Exception.UserNotFoundException;
/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        UserService userService=new UserServiceImp();
        try {
            userService.Login("wassimnefzi", "wassimnefzi110");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        scene = new Scene(loadFXML("mainpage"), 1280, 720);
        scene.getStylesheets().add(getClass().getResource("/Elkhadema/khadema/style.css").toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src//main//resources//images//elkhadema.png"));
        stage.show();

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
