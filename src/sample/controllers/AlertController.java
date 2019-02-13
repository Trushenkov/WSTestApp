package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Класс-контроллер для окна "Alert SignUp"
 * <p>
 * Date: 24.01.2019 (четверг)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class AlertController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonCloseAlert;

    @FXML
    private Label labelSignUp;

    @FXML
    void initialize() {
        buttonCloseAlert.setOnAction(event -> {
            buttonCloseAlert.getScene().getWindow().hide();
        });
    }
}
