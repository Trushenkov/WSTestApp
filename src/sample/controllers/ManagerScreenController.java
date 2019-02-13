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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Helper;
import sample.Service;

/**
 * Date: 11.02.2019 (понедельник)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class ManagerScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelWelcome;

    @FXML
    private Button buttonExit;

    @FXML
    private Button listOrders;

    @FXML
    private ImageView logo;

    @FXML
    private Button toDoOrder;

    @FXML
    void initialize() {
        //Обработка нажатия на кнопку "Список заказов"
        listOrders.setOnAction(event -> {
            listOrders.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/listOrdersScreen.fxml", "Список заказов");
        });

        //Обработка нажатия на кнопку "Оформление заказа"
        toDoOrder.setOnAction(event -> {
            toDoOrder.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/toDoOrderScreen.fxml", "Оформление заказа");
        });

        //Обработка нажатия на кнопку "Выход"
        buttonExit.setOnAction(event -> {
            buttonExit.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/login.fxml", "Авторизация");
        });

    }
}