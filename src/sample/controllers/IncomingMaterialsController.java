package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DataBaseHandler;
import sample.Service;
import sample.entities.IncomingMaterial;
import sample.entities.Material;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 14.02.2019 (четверг)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class IncomingMaterialsController {

    @FXML
    private Button exitButton;

    @FXML
    private ComboBox<String> provider;

    @FXML
    private ComboBox<String> material;

    @FXML
    private TextField count;

    @FXML
    private TextField purchasePrice;

    @FXML
    private TextField sum;

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
    private Button backButton;

    @FXML
    private Button saveBtn;

    private int pricee;

    private int countt;

    private ObservableList<IncomingMaterial> list;

    private ObservableList<IncomingMaterial> listMaterials;

    @FXML
    void initialize() {
        listMaterials = FXCollections.observableArrayList();

        count.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                count.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        purchasePrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                purchasePrice.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        provider.getItems().addAll("Поставщик 1",
                "Поставщик 2",
                "Поставщик 3",
                "Поставщик 4",
                "Поставщик 5");

        try {
            String select = "SELECT `Наименование` FROM `материал`";

            DataBaseHandler dbhandler = new DataBaseHandler();
            ResultSet set = dbhandler.getDbConnection().createStatement().executeQuery(select);

            while (set.next()) {
                material.getItems().add(new Material(set.getString(1)).getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error INSERT Data");
        }

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
            try {
                countt = Integer.parseInt(newValue);
                sum.setText(String.valueOf(countt * pricee));
            } catch (Exception ignored) {}
        });

        purchasePrice.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                pricee = Integer.parseInt(newValue);
                sum.setText(String.valueOf(countt * pricee));
            } catch (Exception ignored) {}
        });

        provider.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//                System.out.println(newValue);
//                System.out.println(listMaterials);
            tableView.getItems().clear();

            for (int i = 0; i < listMaterials.size(); i++) {
                if (listMaterials.get(i).getProvider().equals(newValue)) {
                    tableView.getItems().add(listMaterials.get(i));
                }
            }
        });
    }

    /**
     * Метод для обработки события на кнопку "Добавить". Добавляет запись в таблицу
     */
    public void addButtonEvent() {
        IncomingMaterial incomingMaterial = new IncomingMaterial();
        incomingMaterial.setProvider(provider.getSelectionModel().getSelectedItem());
        incomingMaterial.setMaterial(material.getSelectionModel().getSelectedItem());
        incomingMaterial.setCount(Integer.parseInt(count.getText()));
        incomingMaterial.setPurchasePrice(Integer.parseInt(purchasePrice.getText()));
        incomingMaterial.setSum(Integer.parseInt(count.getText()) * Integer.parseInt(purchasePrice.getText()));

        listMaterials.add(incomingMaterial);

        tableView.getItems().add(incomingMaterial);


        count.setText("");
        purchasePrice.setText("");
        sum.setText("");
    }

    /**
     * Метод для обработки события на кнопку "Провести". Добавляет данные из таблицы в базу данных MySQL
     */
    public void saveDataToDB(ActionEvent event) {

        try {
            DataBaseHandler dbhandler = new DataBaseHandler();
            list = tableView.getItems();

            String insert = "INSERT INTO `поступаемые материалы` (`Поставщик`, `Материал`, `Количество`, `Закупочная стоимость`, `Сумма`) VALUES (?,?,?,?,?)";

            for (IncomingMaterial instance : list) {
                try {
                    PreparedStatement preparedStatement = dbhandler.getDbConnection().prepareStatement(insert);
                    preparedStatement.setString(1, instance.getProvider());
                    preparedStatement.setString(2, instance.getMaterial());
                    preparedStatement.setInt(3, instance.getCount());
                    preparedStatement.setInt(4, instance.getPurchasePrice());
                    preparedStatement.setInt(5, instance.getSum());


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
     */
    public void deleteFromTable() {
        IncomingMaterial selectedMaterial = tableView.getSelectionModel().getSelectedItem();
        listMaterials.remove(selectedMaterial);
        tableView.getItems().remove(selectedMaterial);
    }
}
