package sample.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.util.converter.IntegerStringConverter;
import sample.Const;
import sample.DataBaseHandler;
import sample.Service;
import sample.entities.IncomingMaterial;
import sample.entities.Material;

/**
 * Date: 14.02.2019 (четверг)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class IncomingMaterialsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView logo;

    @FXML
    private Button exitButton;

    @FXML
    private ComboBox provider;

    @FXML
    private TextField material;

    @FXML
    private TextField count;

    @FXML
    private TextField purchasePrice;

    @FXML
    private TextField sum;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button addBtn;

    @FXML
    private TableView<IncomingMaterial> tableView;

    @FXML
    private TableColumn<IncomingMaterial, String> providerColumn;

    @FXML
    private TableColumn<IncomingMaterial, String> materialColumn;

    @FXML
    private TableColumn<IncomingMaterial, Integer> countColumn;

    @FXML
    private TableColumn<IncomingMaterial, Integer> purchasePriceColumn;

    @FXML
    private TableColumn<IncomingMaterial, Integer> sumColumn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button backButton;

    private int pricee;

    private int countt;

    private ObservableList<IncomingMaterial> list;

    @FXML
    void initialize() {

        provider.getItems().addAll("Поставщик 1",
                "Поставщик 2",
                "Поставщик 3",
                "Поставщик 4",
                "Поставщик 5");


        //Для редактирования таблицы
        tableView.setEditable(true);
        providerColumn.setCellFactory(TextFieldTableCell
                .forTableColumn());
        materialColumn.setCellFactory(TextFieldTableCell
                .forTableColumn());
        countColumn.setCellFactory(TextFieldTableCell
                .forTableColumn(new IntegerStringConverter()));
        purchasePriceColumn.setCellFactory(TextFieldTableCell
                .forTableColumn(new IntegerStringConverter()));
        sumColumn.setCellFactory(TextFieldTableCell
                .forTableColumn(new IntegerStringConverter()));

        //Определение полей таблицы и соответствие полям объекта Material
        providerColumn.setCellValueFactory(new PropertyValueFactory<>("provider"));
        materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        sumColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));

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

        count.textProperty().addListener((observable, oldValue, newValue) -> {
            countt = Integer.parseInt(newValue);
            sum.setText(String.valueOf(countt * pricee));
        });

        purchasePrice.textProperty().addListener((observable, oldValue, newValue) -> {
            pricee = Integer.parseInt(newValue);
            sum.setText(String.valueOf(countt * pricee));
        });

    }

    /**
     * Метод для обработки события на кнопку "Добавить". Добавляет запись в таблицу
     *
     * @param actionEvent событие
     */
    public void addButtonEvent(ActionEvent actionEvent) {
        IncomingMaterial incomingMaterial = new IncomingMaterial();
        incomingMaterial.setProvider(provider.getSelectionModel().getSelectedItem().toString());
        incomingMaterial.setMaterial(material.getText());
        incomingMaterial.setCount(Integer.parseInt(count.getText()));
        incomingMaterial.setPurchasePrice(Integer.parseInt(purchasePrice.getText()));
        incomingMaterial.setSum(Integer.parseInt(count.getText()) * Integer.parseInt(purchasePrice.getText()));

        tableView.getItems().add(incomingMaterial);
    }

    /**
     * Метод для обработки события на кнопку "Провести". Добавляет данные из таблицы в базу данных MySQL
     *
     * @param actionEvent событие
     */
    public void saveDataToDB(ActionEvent actionEvent) {

        try {
            DataBaseHandler dbhandler = new DataBaseHandler();
            list = tableView.getItems();

            String insert = "INSERT INTO `поступаемые материалы` (`Поставщик`, `Материал`, `Количество`, `Закупочная стоимость`, `Сумма`) VALUES (?,?,?,?,?)";

            for (int i = 0; i < list.size(); i++) {

                try {
                    PreparedStatement preparedStatement = dbhandler.getDbConnection().prepareStatement(insert);
                    preparedStatement.setString(1, list.get(i).getProvider());
                    preparedStatement.setString(2, list.get(i).getMaterial());
                    preparedStatement.setInt(3, list.get(i).getCount());
                    preparedStatement.setInt(4, list.get(i).getPurchasePrice());
                    preparedStatement.setInt(5, list.get(i).getSum());

                    preparedStatement.executeUpdate();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error INSERT Data");
        }
    }

    /**
     * Метод для обработки события для кнопки "Удалить". Удаляет запись из таблицы
     *
     * @param actionEvent событие
     */
    public void deleteFromTable(ActionEvent actionEvent) {
        IncomingMaterial selectedMaterial = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(selectedMaterial);
    }
}
