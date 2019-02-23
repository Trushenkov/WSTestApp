package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Const;
import sample.DataBaseHandler;
import sample.Service;
import sample.entities.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.prefs.Preferences;

/**
 * Date: 16.02.2019 (суббота)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class ListProductsController {

    @FXML
    private Button exitButton;

    @FXML
    private TableColumn<Product, Integer> widthColumn;

    @FXML
    private TableColumn<Product, Integer> heightColumn;

    @FXML
    private TableColumn<Product, String> imageColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, String> commentColumn;

    @FXML
    private TableColumn<Product, Integer> lengthColumn;

    @FXML
    private TableColumn<Product, Integer> articulColumn;

    private static final String SELECT = "select * from " + Const.TABLE_PRODUCTS;
    private String pathToParentWindow = "";

    @FXML
    void initialize() {

        //Определение полей таблицы и соответствие полям объекта Furniture
        articulColumn.setCellValueFactory(new PropertyValueFactory<>("articul"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
        widthColumn.setCellValueFactory(new PropertyValueFactory<>("width"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));

        ObservableList<Product> list = FXCollections.observableArrayList();

        try {
            DataBaseHandler handler = new DataBaseHandler();

            PreparedStatement statement = handler.getDbConnection().prepareStatement(SELECT);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product(resultSet.getInt("Артикул"),
                        resultSet.getString("Наименование"),
                        resultSet.getInt("Длина, мм"),
                        resultSet.getInt("Ширина, мм"),
                        resultSet.getInt("Высота, мм"),
                        resultSet.getString("Изображение"),
                        resultSet.getString("Комментарий"));

                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

        tableView.setItems(list);


        Preferences preferences = Preferences.userRoot();
        String login = preferences.get("user_login", "");
        String password = preferences.get("user_password", "");
        String role = preferences.get("user_role", "");

        //Обработка нажатия на кнопку "Назад"
        backButton.setOnAction(event -> {
            if (role.equals("Менеджер")) {
                backButton.getScene().getWindow().hide();
                new Service().changeScreen("/sample/view/managerScreen.fxml", "Экран менеджера");
            } else {
                backButton.getScene().getWindow().hide();
                new Service().changeScreen("/sample/view/directorScreen.fxml", "Экран директора");
            }
        });

        //Обработка нажатия на кнопку "Выход"
        exitButton.setOnAction(event -> {
            ((Node) event.getSource()).getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/login.fxml", "Авторизация");
        });
    }

    public void setPathToParentWindow(String pathToParentWindow) {
        this.pathToParentWindow = pathToParentWindow;
    }
}
