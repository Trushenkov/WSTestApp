package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import sample.Service;

/**
 * Date: 11.02.2019 (понедельник)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class DirectorScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelWelcome;

    @FXML
    private Button listProductsBtn;

    @FXML
    private ImageView logo;

    @FXML
    private Button btnExit;

    @FXML
    void initialize() {
        //Обработка нажатия на кнопку "Выход"
        btnExit.setOnAction(event -> {
            ((Node) event.getSource()).getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/login.fxml", "Авторизация");
        });

        //Обработка нажатия на кнопку "Список изделий"
        listProductsBtn.setOnAction(event -> {
            ((Node) event.getSource()).getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/listProducts.fxml","Список изделий");
        });
    }

}

