package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Const;
import sample.DataBaseHandler;
import sample.Service;
import sample.animation.Shake;
import sample.entities.User;

import javax.swing.event.HyperlinkEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;

/**
 * Класс-контроллер для окна "Test Application - Login"
 *
 * @author Трушенков Дмитрий
 */
public class LoginController {

    @FXML
    private Button buttonSignUp;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button buttonLogin;

    @FXML
    private TextField loginTextField;

    @FXML
    private Label labelBadLoginData;

    private int countOfUsers = 0;

    @FXML
    void initialize() {
        labelBadLoginData.setVisible(false);
        loginTextField.setText("");
        passwordTextField.setText("");

        //Обработка нажатия на кнопку "Регистрация"
        buttonSignUp.setOnAction(event -> {
            buttonSignUp.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/signUp.fxml", "Регистрация");
        });

    }

    /**
     * Метод для авторизации пользователя
     *
     * @param loginText    логин
     * @param passwordText пароль
     */
    private void loginUser(String loginText, String passwordText, ActionEvent actionEvent) throws SQLException {
        DataBaseHandler dbHandler = new DataBaseHandler();//Подключение к БД
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(passwordText);
        ResultSet resultSet = dbHandler.getUser(user);

        try {
            while (resultSet.next()) {
                countOfUsers++;
                Preferences preferences = Preferences.userRoot();
                preferences.put("user_login", resultSet.getString(Const.USER_LOGIN));
                preferences.put("user_password", resultSet.getString(Const.USER_PASSWORD));
                preferences.put("user_role", resultSet.getString(Const.USER_ROLE));
                preferences.put("user_name", resultSet.getString(Const.USER_NAME));

                switch (resultSet.getString(Const.USER_ROLE)) {
                    case "Заказчик":
                        buttonLogin.getScene().getWindow().hide();
                        new Service().changeScreen("/sample/view/orderwindow.fxml", "Экран заказчика");
                        break;
                    case "Менеджер":
                        buttonLogin.getScene().getWindow().hide();
                        new Service().changeScreen("/sample/view/managerScreen.fxml", "Экран менеджера");
                        break;
                    case "Кладовщик":
                        buttonLogin.getScene().getWindow().hide();
                        new Service().changeScreen("/sample/view/storekeeperScreen.fxml", "Экран кладовщика");
                        break;
                    case "Директор":
                        buttonLogin.getScene().getWindow().hide();
                        new Service().changeScreen("/sample/view/directorScreen.fxml", "Экран директора");
                        break;
                }

            }

            if (countOfUsers == 0) {
                Shake userLoginAnimation = new Shake(loginTextField);
                Shake userPasswordAnimation = new Shake(passwordTextField);

                userLoginAnimation.playAnimation();
                userPasswordAnimation.playAnimation();

                labelBadLoginData.setVisible(true);
//                new Service().hangeScreenModalWindow("/sample/view/alerts/alertBadLogin.fxml", "Ошибка входа", actionEvent);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод для обработки события при нажатии кнопки "Войти"
     *
     * @param actionEvent событие (нажатие на кнопку)
     */
    public void loginActionMethod(ActionEvent actionEvent) {
        String loginText = loginTextField.getText().trim();
        String passwordText = passwordTextField.getText().trim();

        if (!loginText.equals("") && !passwordText.equals("")) {
            try {
                loginUser(loginText, passwordText, actionEvent); // Проверка на сущ. такого пользователя в БД
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Не заполнены поля ввода");
        }


    }
}
