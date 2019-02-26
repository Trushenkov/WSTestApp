package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sample.DataBaseHandler;
import sample.Service;
import sample.entities.NewOrder;
import sample.entities.Order;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * Date: 24.02.2019 (воскресенье)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class OrderController {


    @FXML
    public Button addBtn;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private ImageView logo;
    @FXML
    private Label loginTextField;
    @FXML
    private Label passwordTextField;
    @FXML
    private Button buttonExit;
    @FXML
    private Label label;
    @FXML
    private Label labelForSumma;
    @FXML
    private Label summa;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField countTextField;
    @FXML
    private TextField priceTextField;

    private Order order;

//    @FXML
//    private TableView<NewOrder> tableView;
//
//    @FXML
//    private TableColumn<NewOrder, String> productColumn;
//
//    @FXML
//    private TableColumn<NewOrder, Integer> countColumn;
//
//    @FXML
//    private TableColumn<NewOrder, Integer> priceColumn;

    @FXML
    private Button backButton;

    @FXML
    private Button toDoOrderBtn;

    private ObservableList<NewOrder> list;

    private ObservableList<Integer> list2;

    private String userName;

    @FXML
    void initialize() {
        ObservableList<Integer> list2 = FXCollections.observableArrayList();

        comboBox.setPromptText("Выберите изделие");

        //Ввод только чисел
        countTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                countTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        priceTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                priceTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        countTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                summa.setText(String.valueOf(Integer.parseInt(priceTextField.getText()) * Integer.parseInt(newValue)));
            } catch (Exception ignored) {
            }
        });

        priceTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                summa.setText(String.valueOf(Integer.parseInt(countTextField.getText()) * Integer.parseInt(newValue)));
            } catch (Exception ignored) {
            }
        });

        //Определение полей таблицы и соответствие полям объекта Material
//        productColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
//        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
//        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            String select = "SELECT `Наименование` FROM `изделие`";

            DataBaseHandler dbhandler = new DataBaseHandler();
            ResultSet resultSet = dbhandler.getDbConnection().createStatement().executeQuery(select);

            while (resultSet.next()) {
                comboBox.getItems().add(resultSet.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error INSERT Data");
        }


        Preferences preferences = Preferences.userRoot();
        String login = preferences.get("user_login", "");
        String password = preferences.get("user_password", "");
        String role = preferences.get("user_role", "");
        userName = preferences.get("user_name", "");

        loginTextField.setText(loginTextField.getText() + login);
        passwordTextField.setText(passwordTextField.getText() + password);


        buttonExit.setOnAction(event -> {
            buttonExit.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/login.fxml", "Авторизация");
        });

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            if (role.equals("Заказчик")) {
                new Service().changeScreen("/sample/view/orderwindow.fxml", "Экран заказщика");
            } else {
                new Service().changeScreen("/sample/view/managerScreen.fxml", "Экран менеджера");
            }
        });

    }

    /**
     * Метод для обработки события на кнопку "Добавить". Добавляет запись в таблицу
     */
    public void addButtonEvent() {

        int summaFromTable = 0;

        NewOrder order = new NewOrder();
        order.setProduct(comboBox.getSelectionModel().getSelectedItem());
        order.setCount(Integer.parseInt(countTextField.getText()));
        order.setPrice(Integer.parseInt(priceTextField.getText()));

//        tableView.getItems().add(order);

        countTextField.setText("");
        priceTextField.setText("");

//        summa.setText("");
//
//        for (int i = 0; i < tableView.getItems().size(); i++) {
//            summaFromTable += tableView.getItems().get(i).getPrice() * tableView.getItems().get(i).getCount();
//        }
//
//        summa.setText(String.valueOf(summaFromTable));
    }

    /**
     * Метод для обработки события на кнопку "Оформить заказ". Добавляет данные из таблицы в базу данных MySQL
     */
    public void saveToDb(ActionEvent actionEvent) {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try {
            DataBaseHandler dbhandler = new DataBaseHandler();

            String insert = "INSERT INTO `заказ` (`Номер заказа`, `Дата`, `Этап выполнения`, `Заказчик`, `Менеджер`, `Стоимость`) VALUES (?,?,?,?,?,?)";
            String select = "SELECT `Номер заказа` FROM `заказ` ORDER BY `Номер заказа` ASC";

            try {
                Statement stmt = dbhandler.getDbConnection().createStatement();
                ResultSet set = stmt.executeQuery(select);

                ObservableList<Integer> arrayList = FXCollections.observableArrayList();

                while (set.next()) {
                    arrayList.add(set.getInt(1));
                }

                int number = arrayList.get(arrayList.size() - 1);

                order = new Order();
                order.setNumber(number + 1);
                order.setDate(sqlDate);
                order.setOrder(userName);
                order.setStep("Новый");
                order.setManager("Сидоров");
                order.setSumma(Integer.parseInt(summa.getText()));

                PreparedStatement preparedStatement = dbhandler.getDbConnection().prepareStatement(insert);
                preparedStatement.setInt(1, order.getNumber());
                preparedStatement.setString(2, String.valueOf(order.getDate()));
                preparedStatement.setString(3, order.getStep());
                preparedStatement.setString(4, order.getOrder());
                preparedStatement.setString(5, order.getManager());
                preparedStatement.setInt(6, Integer.parseInt(summa.getText()));

                preparedStatement.executeUpdate();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        } catch (
                Exception e)

        {
            e.printStackTrace();
            System.out.println("Error INSERT Data");
        }


        try

        {
            DataBaseHandler dbhandler = new DataBaseHandler();


            String select = "SELECT `Артикул` FROM `изделие` WHERE `Наименование` =?";
            String insert = "INSERT INTO `заказанные изделия` (`Номер заказа`,`Артикул`, `Количество`) VALUES (?,?,?)";

            try {
                PreparedStatement ps = dbhandler.getDbConnection().prepareStatement(select);
                ps.setString(1, comboBox.getSelectionModel().getSelectedItem());

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    PreparedStatement preparedStatement = dbhandler.getDbConnection().prepareStatement(insert);
                    preparedStatement.setInt(1, order.getNumber());
                    preparedStatement.setInt(2, rs.getInt(1));
                    preparedStatement.setInt(3, Integer.parseInt(countTextField.getText()));

                    preparedStatement.executeUpdate();

                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error INSERT Data");
        }
    }
}
