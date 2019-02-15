package sample.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sample.DataBaseHandler;
import sample.Service;
import sample.entities.User;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Класс-контроллер для окна "Test Application - SignUp"
 *
 * @author Трушенков Дмитрий
 */
public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonSignUp;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField nameTextField;

    @FXML
    private ImageView logo;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField roleTextField;

    @FXML
    private TextField loginTextField;

    @FXML
    private Label labelSignUp;

    @FXML
    void initialize() {

        //Обработка нажатия на кнопку "Авторизация"
        btnLogin.setOnAction(event -> {
            btnLogin.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/login.fxml", "Авторизация");
        });
    }

    /**
     * Метод, в котором собираются данные из полей, заполненных пользователем, и вызывается метод для записи данных в БД.
     */
    private void sighUpNewUser(ActionEvent actionEvent) {
        DataBaseHandler dbHandler = new DataBaseHandler();

        String login = loginTextField.getText();
        String password = passwordField.getText();
        String role = roleTextField.getText();
        String name = nameTextField.getText();

        User user = new User(login, password, role, name);

        dbHandler.signUpUser(user, actionEvent);

    }

    public void signUpAction(ActionEvent actionEvent) {

        if (!loginTextField.getText().equals("") &&
                !passwordField.getText().equals("") &&
                !nameTextField.getText().equals("")) {

            if (!passwordField.getText()
                    .matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^])(?=\\S+$).{6,}$")) {
                System.out.println("Неправильно введен пароль");
                return;
            }

            sighUpNewUser(actionEvent);

            //Обнуляем поля для ввода
            loginTextField.setText("");
            passwordField.setText("");
            nameTextField.setText("");
        } else {
            System.out.println("Не заполнены поля ввода");
        }

    }
}

