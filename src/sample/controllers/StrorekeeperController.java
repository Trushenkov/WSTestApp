package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import sample.Service;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Date: 11.02.2019 (понедельник)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class StrorekeeperController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelWelcome;

    @FXML
    private Button materials;

    @FXML
    private Button buttonExit;

    @FXML
    private Button listOrders;

    @FXML
    private ImageView logo;

    @FXML
    private Button listFurniture;

    @FXML
    void initialize() {
        //Обработка нажатия на кнопку "Выход"
        buttonExit.setOnAction(event -> {
            buttonExit.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/login.fxml", "Авторизация");
        });

        listOrders.setOnAction(event -> {
            listOrders.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/listMaterialsScreen.fxml",
                    "Список материалов");
        });

        listFurniture.setOnAction(event -> {
            listFurniture.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/listFurniture.fxml",
                    "Список фурнитуры");
        });
    }

    public void showIncomingMaterials(ActionEvent actionEvent) {
        materials.getScene().getWindow().hide();
        new Service().changeScreen("/sample/view/incomingOrders.fxml",
                "Поступление материалов");
    }
}
