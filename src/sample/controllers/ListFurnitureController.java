package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Const;
import sample.DataBaseHandler;
import sample.Service;
import sample.entities.Furniture;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Date: 15.02.2019 (пятница)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class ListFurnitureController {

    private static final String SELECT = "select * from " + Const.TABLE_FURNITURE;
    @FXML
    private TableColumn<Furniture, String> widthColumn;
    @FXML
    private TableColumn<Furniture, String> imageColumn;
    @FXML
    private TableView<Furniture> tableView;
    @FXML
    private TableColumn<Furniture, String> lengthColumn;
    @FXML
    private TableColumn<Furniture, String> articulColumn;
    @FXML
    private TableColumn<Furniture, String> typeColumn;
    @FXML
    private Button exitButton;
    @FXML
    private TableColumn<Furniture, String> nameColumn;
    @FXML
    private Button backButton;
    @FXML
    private TableColumn<Furniture, String> weigthColumn;
    @FXML
    private TableColumn<Furniture, Integer> priceColumn;

    @FXML
    void initialize() {

        //Определение полей таблицы и соответствие полям объекта Furniture
        articulColumn.setCellValueFactory(new PropertyValueFactory<>("articul"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
        widthColumn.setCellValueFactory(new PropertyValueFactory<>("width"));
        weigthColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Furniture> list = FXCollections.observableArrayList();

        try {
            DataBaseHandler dbhandler = new DataBaseHandler();

            PreparedStatement ps = dbhandler.getDbConnection().prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Furniture furniture = new Furniture(rs.getString("Артикул фурнитуры"),
                        rs.getString("Наименование"),
                        rs.getString("Тип"),
                        rs.getString("Длина, мм"),
                        rs.getString("Ширина, мм"),
                        rs.getString("Вес"),
                        rs.getString("Изображение"),
                        rs.getInt("Цена"));

                list.add(furniture);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

        tableView.setItems(list);

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/storekeeperScreen.fxml", "Экран кладовщика");
        });

        //Обработка нажатия на кнопку "Выход"
        exitButton.setOnAction(event -> {
            exitButton.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/login.fxml", "Авторизация");
        });
    }
}
