package sample.controllers;

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
import sample.Const;
import sample.DataBaseHandler;
import sample.Helper;
import sample.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * Класс-контроллер для окна "Test Application - Login"
 *
 * @author Трушенков Дмитрий
 */
public class LoginController implements Helper {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonSignUp;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button buttonLogin;

    @FXML
    private Label labelLogIn;

    @FXML
    private ImageView logo;

    @FXML
    private TextField loginTextField;

    @FXML
    void initialize() {
        //Обработка нажатия на кнопку "Регистрация"
        buttonSignUp.setOnAction(event -> {
            buttonSignUp.getScene().getWindow().hide();
            changeScreen("/sample/view/signUp.fxml", "Регистрация");
        });

        //Обработка нажатия на кнопку "Войти"
        buttonLogin.setOnAction(event -> {
            String loginText = loginTextField.getText().trim();
            String passwordText = passwordTextField.getText().trim();

            if (!loginTextField.equals("") && !passwordTextField.equals("")) {
                try {
                    loginUser(loginText, passwordText); // Проверка на сущ. такого пользователя в БД
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Login and password is empty");
            }
        });
    }

    /**
     * Метод для авторизации пользователя
     *
     * @param loginText    логин
     * @param passwordText пароль
     */
    private void loginUser(String loginText, String passwordText) throws SQLException {
        DataBaseHandler dbHandler = new DataBaseHandler();//Подключение к БД
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(passwordText);
        ResultSet resultSet = dbHandler.getUser(user);

        int countOfUsers = 0;

        try {
            while (resultSet.next()) {
                countOfUsers++;
                Preferences preferences = Preferences.userRoot();
                preferences.put("user_login", resultSet.getString(Const.USER_LOGIN));
                preferences.put("user_password", resultSet.getString(Const.USER_PASSWORD));
                preferences.put("user_role", resultSet.getString(Const.USER_ROLE));

//                loginedUser = new User();
//                loginedUser.setLogin(resultSet.getString(Const.USER_LOGIN));
//                loginedUser.setPassword(resultSet.getString(Const.USER_PASSWORD));
//                loginedUser.setRole(resultSet.getString(Const.USER_ROLE));
//
//                System.out.println(loginedUser.getLogin());
//                System.out.println(loginedUser.getPassword());
//                System.out.println(loginedUser.getRole());

                if (countOfUsers >= 1 && resultSet.getString(Const.USER_ROLE).equals("Заказчик")) {
                    buttonLogin.getScene().getWindow().hide();
//                    System.out.println("You are loggined successfull!!! Congratulations! \n " + loginedUser.toString());

                    changeScreen("/sample/view/orderwindow.fxml", "Экран заказчика");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
