package sample;

import javafx.event.ActionEvent;
import sample.entities.User;

import java.sql.*;

/**
 * Класс для подключения и работы с базой данных.
 * <p>
 * Date: 23.01.2019 (среда)
 * Project name: TestApplication
 * Package name: sample
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class DataBaseHandler extends Config {

    private Connection dbConnection;

    /**
     * Метод для получения подключения к базе данных
     *
     * @return подключение к базе данных
     * @throws ClassNotFoundException exception
     * @throws SQLException           exception
     */
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?serverTimezone=UTC";
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    /**
     * Метод для записи нового пользователя в базу данных
     *
     * @param user пользователь
     */
    public void signUpUser(User user, ActionEvent actionEvent) {
        String insert = "INSERT INTO " + Const.USER_TABLE + " (" + Const.USER_LOGIN + ", " + Const.USER_PASSWORD + ", " + Const.USER_ROLE + ", " + Const.USER_NAME + ")" + " VALUES(?,?,?,?)";

        String selectUserLogin = "SELECT " + Const.USER_LOGIN + " FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=?";
        try {

            PreparedStatement prSt = getDbConnection().prepareStatement(selectUserLogin);
            prSt.setString(1, user.getLogin());
            ResultSet resultSet = prSt.executeQuery();
            if (resultSet.next()) {
                System.out.println("Пользователь с таким логином уже существует");
                new Service().changeScreenModalWindow("/sample/view/alerts/alertExistLogin.fxml", "Ошибка при регистрации", actionEvent);
            } else {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getRole());
                preparedStatement.setString(4, user.getName());

                preparedStatement.executeUpdate();

                //Перенаправление и открытие нового окна приложения
                new Service().changeScreenModalWindow("/sample/view/alerts/alertTemplate.fxml", "Регистрация", actionEvent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для получения пользователя с указанным логином и паролем из базы данных
     *
     * @param user пользователь из базы данных
     * @return пользователь с указанным логином и паролем
     */
    public ResultSet getUser(User user) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=? AND " + Const.USER_PASSWORD + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

}


