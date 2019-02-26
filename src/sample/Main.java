package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        primaryStage.setTitle("Test application - Login");
        primaryStage.setScene(new Scene(root, 1085, 700));
        primaryStage.getScene().getStylesheets().add("/sample/css/style.css");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
