package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import sample.Const;
import sample.DataBaseHandler;
import sample.Service;
import sample.entities.Material;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * Date: 12.02.2019 (вторник)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class ListMaterialsController {

    private static final String SELECT = "select * from " + Const.TABLE_MATERIAL;
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
    private TextField searchTextField;
    @FXML
    private Button updateBtn;

    @FXML
    private TextField tf_width;
    @FXML
    private TextField tf_marka;
    @FXML
    private TextField tf_length;
    @FXML
    private TextField tf_color;
    @FXML
    private TextField tf_articul;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_price;


    //    private ObservableList<ObservableList> data;
    @FXML
    private ImageView logo;
    private ObservableList<Material> list;

    private Material material;


    @FXML
    void initialize() {

        //Определение полей таблицы и соответствие полям объекта Material
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

        //множественная выборка из таблицы
//        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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


        FilteredList<Material> filteredList = new FilteredList<Material>(list, e -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate((Predicate<? super Material>) materialPredicate -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseNewValue = newValue.toLowerCase();
                if (materialPredicate.getArticul().toLowerCase().contains(lowerCaseNewValue)){
                    return true;
                } else if (materialPredicate.getName().toLowerCase().contains(lowerCaseNewValue)) {
                    return true;
                }

                return false;
            });
        });

        SortedList<Material> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!= null) {
                tf_articul.setText(tableView.getSelectionModel().getSelectedItem().getArticul());
                tf_name.setText(tableView.getSelectionModel().getSelectedItem().getName());
                tf_color.setText(tableView.getSelectionModel().getSelectedItem().getColor());
                tf_length.setText(String.valueOf(tableView.getSelectionModel().getSelectedItem().getLength()));
                tf_width.setText(String.valueOf(tableView.getSelectionModel().getSelectedItem().getWidth()));
                tf_marka.setText(tableView.getSelectionModel().getSelectedItem().getMarka());
                tf_price.setText(String.valueOf(tableView.getSelectionModel().getSelectedItem().getPrice()));
            }
        });

    }

    public void update(ActionEvent event) throws SQLException, ClassNotFoundException {
        String articul = tableView.getSelectionModel().getSelectedItem().getArticul();

        String update = " UPDATE `материал` SET `Артикул`=?, `Наименование`=?, `Марка`=?, " +
                "`Цвет`=?, `Длина, мм`=?, `Ширина, мм`=?, `Цена`=? where `Артикул`= '" + articul + "'";

        DataBaseHandler handler =  new DataBaseHandler();
        PreparedStatement preparedStatement = handler.getDbConnection().prepareStatement(update);
        preparedStatement.setString(1, tf_articul.getText());
        preparedStatement.setString(2, tf_name.getText());
        preparedStatement.setString(3, tf_marka.getText());
        preparedStatement.setString(4, tf_color.getText());
        preparedStatement.setInt(5, Integer.parseInt(tf_length.getText()));
        preparedStatement.setInt(6, Integer.parseInt(tf_width.getText()));
        preparedStatement.setInt(7, Integer.parseInt(tf_price.getText()));
        preparedStatement.executeUpdate();

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getArticul().equals(tf_articul.getText())){
                list.get(i).setColor(tf_name.getText());
                list.get(i).setColor(tf_color.getText());
                list.get(i).setColor(tf_marka.getText());
                list.get(i).setColor(tf_length.getText());
                list.get(i).setColor(tf_width.getText());
                list.get(i).setColor(tf_price.getText());
            }
        }


        System.out.println("Updated item  with Артикул=" + tf_articul.getText());

        tf_articul.setText("");
        tf_name.setText("");
        tf_color.setText("");
        tf_length.setText("");
        tf_width.setText("");
        tf_marka.setText("");
        tf_price.setText("");

        list.clear();

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
    }
}
