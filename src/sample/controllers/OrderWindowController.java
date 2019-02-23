package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Service;

import java.util.prefs.Preferences;

/**
 * Date: 01.02.2019 (пятница)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class OrderWindowController {


    @FXML
    private Label passwordTextField;

    @FXML
    private Button buttonExit;

    @FXML
    private Label loginTextField;

    @FXML
    void initialize() {
        Preferences preferences = Preferences.userRoot();
        String login = preferences.get("user_login", "");
        String password = preferences.get("user_password", "");

        loginTextField.setText(loginTextField.getText() + login);
        passwordTextField.setText(passwordTextField.getText() + password);

        buttonExit.setOnAction(event -> {
            buttonExit.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/login.fxml", "Авторизация");
        });
    }

}

