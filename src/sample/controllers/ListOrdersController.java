package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import sample.DataBaseHandler;
import sample.Service;
import sample.entities.Order;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Date: 12.02.2019 (вторник)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class ListOrdersController {

    private static final String SELECT = "select `public`.`заказ`.`Номер заказа`,`Дата`,`Этап выполнения`,`Заказчик`,`Менеджер`,`Количество` from `public`.`заказанные изделия`, `public`.`заказ` where `public`.`заказанные изделия`.`Номер заказа` = `public`.`заказ`.`Номер заказа` ;";
    @FXML
    private Button btnBack;
    @FXML
    private TableColumn<Order, Integer> countColumn;
    @FXML
    private Label labelScreen;
    @FXML
    private TableColumn<Order, String> stepColumn;
    @FXML
    private Button buttonExit;
    @FXML
    private TableColumn<Order, String> managerColumn;
    @FXML
    private ImageView logo;
    @FXML
    private TableColumn<Order, Date> dateColumn;
    @FXML
    private TableColumn<Order, Integer> numberColumn;
    @FXML
    private TableView<Order> table;
    @FXML
    private TableColumn<Order, String> orderColumn;

//    public static final String SELECT_COUNT = "select `Количество` ";

    @FXML
    void initialize() {

        //Определение полей таблицы и соответствие полям объекта Furniture
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        stepColumn.setCellValueFactory(new PropertyValueFactory<>("step"));
        orderColumn.setCellValueFactory(new PropertyValueFactory<>("order"));
        managerColumn.setCellValueFactory(new PropertyValueFactory<>("manager"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        ObservableList<Order> list = FXCollections.observableArrayList();

        try {
            DataBaseHandler dbhandler = new DataBaseHandler();

            PreparedStatement ps = dbhandler.getDbConnection().prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();

//            Statement statement = dbhandler.getDbConnection().createStatement();
//            ResultSet resultSet = statement.executeQuery(SELECT);

            while (rs.next()) {
                Order order = new Order(rs.getInt("Номер заказа"),
                        rs.getDate("Дата"),
                        rs.getString("Этап выполнения"),
                        rs.getString("Заказчик"),
                        rs.getString("Менеджер"),
                        rs.getInt("Количество")
                );

                list.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

        table.setItems(list);


        btnBack.setOnAction(event -> {
            btnBack.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/managerScreen.fxml", "Экран менеджера");
        });

        //Обработка нажатия на кнопку "Выход"
        buttonExit.setOnAction(event -> {
            buttonExit.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/login.fxml", "Авторизация");
        });
    }
}
