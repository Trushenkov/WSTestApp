package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.DataBaseHandler;
import sample.Helper;
import sample.User;

/**
 * Класс-контроллер для окна "Test Application - SignUp"
 *
 * @author Трушенков Дмитрий
 */
public class SignUpController implements Helper {

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

        //Обработка нажатия на кнопку "Зарегистрироваться"
        buttonSignUp.setOnAction(event -> {

            if (!loginTextField.getText().equals("") && !passwordField.getText().equals("")) {
                sighUpNewUser();

                //Обнуляем поля для ввода
                loginTextField.setText("");
                passwordField.setText("");


                //Перенаправление и открытие новой окна приложения
                changeScreen("/sample/view/alertSignUp.fxml", "Регистрация");
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/sample/view/alertSignUp.fxml"));
//                try {
//                    loader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                Parent root = loader.getRoot();
//                Stage stage = new Stage();
//                stage.setScene(new Scene(root));
//                stage.setTitle("Alert SignUp");
//                stage.setResizable(false);
//                stage.setHeight(250);
//                stage.setWidth(400);
//                stage.showAndWait();
            } else {
                System.out.println("Login and password is empty");
            }


        });

        //Обработка нажатия на кнопку "Авторизация"
        btnLogin.setOnAction(event -> {
            btnLogin.getScene().getWindow().hide();
            changeScreen("/sample/view/login.fxml", "Авторизация");
        });
    }

    /**
     * Метод, в котором собираются данные из полей, заполненных пользователем, и вызывается метод для записи данных в БД.
     */
    private void sighUpNewUser() {
        DataBaseHandler dbHandler = new DataBaseHandler();

        String login = loginTextField.getText();
        String password = passwordField.getText();
        String role = roleTextField.getText();
        String name = nameTextField.getText();

        User user = new User(login, password, role, name);

        dbHandler.signUpUser(user);

    }

    /**
     * Метод для перехода на другое окно приложения
     *
     * @param path  путь к fxml файлу
     * @param title заголовок окна
     */
    @Override
    public void changeScreen(String path, String title) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Test application - " + title + "!");
        stage.setResizable(false);
        stage.show();
    }
}

