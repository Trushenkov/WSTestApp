package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import sample.Service;

import java.util.prefs.Preferences;

/**
 * Date: 11.02.2019 (понедельник)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class ManagerScreenController {

    @FXML
    public Button listProducts;

    @FXML
    private Button buttonExit;

    @FXML
    private Button listOrders;

    @FXML
    private Button toDoOrder;

    @FXML
    void initialize() {

        //Обработка нажатия на кнопку "Список изделий"
        listProducts.setOnAction(event -> {
            listProducts.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/listProducts.fxml", "Список изделий");
        });

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