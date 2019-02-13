package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.DataBaseHandler;
import sample.Helper;
import sample.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Date: 01.02.2019 (пятница)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class WelcomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelWelcome;

    @FXML
    private Label loginAsField;

    @FXML
    private Button buttonExit;

    @FXML
    private Label passwordAsField;

    @FXML
    private ImageView logo;

    @FXML
    void initialize() {
        Preferences preferences = Preferences.userRoot();
        String login = preferences.get("user_login", "");
        String password = preferences.get("user_password", "");
        loginAsField.setText("Ваш логин: " + login);
        passwordAsField.setText("Ваш пароль: " + password);

        System.out.print(preferences.get("user_login", ""));
        System.out.print(preferences.get("user_password", ""));

        buttonExit.setOnAction(event -> {
            buttonExit.getScene().getWindow().hide();
            try {
                preferences.clear();
            } catch (BackingStoreException e) {
                e.printStackTrace();
            }

            System.out.print(preferences.get("user_login", ""));
            System.out.print(preferences.get("user_password", ""));

            new Service().changeScreen("/sample/view/login.fxml", "Авторизация");
        });
    }
}

