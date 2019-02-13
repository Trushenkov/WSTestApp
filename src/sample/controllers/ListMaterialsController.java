package sample.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import sample.*;

/**
 * Date: 12.02.2019 (вторник)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class ListMaterialsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitButton;

    @FXML
    private Button backButton;

    @FXML
    private Label labelWelcome;

    @FXML
    private TableColumn<Material, String> articul;

    @FXML
    private TableColumn<Material, String> name;

    @FXML
    private TableColumn<Material, String> marka;

    @FXML
    private TableColumn<Material, String> color;

    @FXML
    private TableColumn<Material, Integer> length;

    @FXML
    private TableColumn<Material, Integer> width;

    @FXML
    private TableColumn<Material, Integer> price;

    @FXML
    private TableView<Material> tableView;

    @FXML
    private ImageView logo;

//    private ObservableList<ObservableList> data;

    private static final String SELECT = "select * from материал";

    private ObservableList<Material> list;

    private Material material;

    @FXML
    void initialize() {

        articul.setCellValueFactory(new PropertyValueFactory<>("articul"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        marka.setCellValueFactory(new PropertyValueFactory<>("marka"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        length.setCellValueFactory(new PropertyValueFactory<>("length"));
        width.setCellValueFactory(new PropertyValueFactory<>("width"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        list = FXCollections.observableArrayList();

        try {
            DataBaseHandler dbhandler = new DataBaseHandler();

            Connection conn = dbhandler.getDbConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                material = new Material(rs.getString("Артикул"),
                        rs.getString("Наименование"),
                        rs.getString("Марка"),
                        rs.getString("Цвет"),
                        rs.getInt("Длина, мм"),
                        rs.getInt("Ширина, мм"),
                        rs.getInt("Цена"));

                list.add(material);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }


        tableView.setItems(list);

//        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        Material selectedMaterial = tableView.getSelectionModel().getSelectedItem();
//        System.out.println(selectedMaterial);

        //Обработка нажатия на кнопку "Выход"
        exitButton.setOnAction(event -> {
            exitButton.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/login.fxml", "Авторизация");
        });

        //Обработка нажатия на кнопку "Назад"
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/storekeeperScreen.fxml", "Экран кладовщика");
        });
    }
}
