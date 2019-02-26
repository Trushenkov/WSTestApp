package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import sample.DataBaseHandler;
import sample.Service;
import sample.entities.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 25.02.2019 (понедельник)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class EvaluationCostsController {

    @FXML
    public Label labelFurniture;

    @FXML
    public Label labelCanNot;

    @FXML
    public Label labelNotEnought;

    @FXML
    private Label labelWelcome;

    @FXML
    private ListView<Order> listView;

    @FXML
    private Button buttonExit;

    @FXML
    private ImageView logo;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<Integer> comboBox;


    @FXML
    void initialize() {

        ObservableList list = FXCollections.observableArrayList();
        ObservableList list2 = FXCollections.observableArrayList();
        ObservableList list3 = FXCollections.observableArrayList();

        labelFurniture.setVisible(false);
        labelCanNot.setVisible(false);
        labelNotEnought.setVisible(false);

        ObservableList<Order> orders = FXCollections.observableArrayList();

        try {
            String select = "select `public`.`заказ`.`Номер заказа`,`Дата`,`Этап выполнения`,`Заказчик`,`Менеджер`,`Стоимость`, `Количество` from `public`.`заказанные изделия`, `public`.`заказ` where `public`.`заказанные изделия`.`Номер заказа` = `public`.`заказ`.`Номер заказа` ;";

            DataBaseHandler dbhandler = new DataBaseHandler();
            ResultSet resultSet = dbhandler.getDbConnection().createStatement().executeQuery(select);

            while (resultSet.next()) {
                Order order = new Order();
                order.setNumber(resultSet.getInt(1));
                order.setDate(resultSet.getDate(2));
                order.setStep(resultSet.getString(3));
                order.setOrder(resultSet.getString(4));
                order.setManager(resultSet.getString(5));
                order.setSumma(resultSet.getInt(6));
                order.setCount(resultSet.getInt(7));

                orders.add(order);

                listView.setItems(orders);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error INSERT Data");
        }

        listView.setOnMouseClicked(event -> {

            labelNotEnought.setText("");

            list.clear();
            list2.clear();
            list3.clear();

            DataBaseHandler handler = new DataBaseHandler();
            listView.getSelectionModel().getSelectedItem().getOrder();

            String selectArticulProduct = "SELECT `Артикул` FROM public.`заказанные изделия` where `public`.`заказанные изделия`.`Номер заказа` =?";

            String select = "SELECT `Артикул фурнитуры`, `Количество` FROM public.`фурнитура изделия` where `Артикул изделия` =?";

            String selectCountFurniture = "SELECT `Количество` FROM public.`склад фурнитуры` WHERE `Артикул фурнитуры` =?";

            try {
                PreparedStatement getArticul = handler.getDbConnection().prepareStatement(selectArticulProduct);
                getArticul.setInt(1, listView.getSelectionModel().getSelectedItem().getNumber());

                ResultSet res = getArticul.executeQuery();

                while (res.next()) {
                    System.out.println("Артикул изделия = " + res.getInt(1));

                    PreparedStatement getFurniture = handler.getDbConnection().prepareStatement(select);
                    getFurniture.setInt(1, res.getInt(1));

                    ResultSet resultSet = getFurniture.executeQuery();

                    while (resultSet.next()) {
                        list3.add(resultSet.getString(1));
                        System.out.println("Артикул фурнитуры = " + resultSet.getString(1) + " Количество = " + resultSet.getInt(2));

                        PreparedStatement statementGet = handler.getDbConnection().prepareStatement(selectCountFurniture);
                        statementGet.setString(1, resultSet.getString(1));

                        ResultSet resultCountFurniture = statementGet.executeQuery();

                        while (resultCountFurniture.next()) {
                            System.out.println(resultCountFurniture.getInt(1));

                            System.out.println(resultSet.getInt(2));

                            if (resultCountFurniture.getInt(1) - resultSet.getInt(2) > 0) {
                                list.add(resultSet.getString(1));
                            } else {
                                list2.add(resultSet.getString(1));
                            }
                        }


                    }
                }

                if (list3.size() == list.size()){
                    labelFurniture.setText("Наличие фурнитуры: Достаточно");
                    labelFurniture.setVisible(true);
                } else {
                    labelFurniture.setText("Наличие фурнитуры: Недостаточно");
                    labelFurniture.setVisible(true);

                    labelNotEnought.setText("Недостаточно фурнитуры: " + list2);
                    labelNotEnought.setVisible(true);                }

                System.out.println(list);
                System.out.println(list2);
                System.out.println(list3);

//                    labelFurniture.setText("Наличие фурнитуры: Достаточно");
//                    labelFurniture.setVisible(true);
//                    labelFurniture.setText("Наличие фурнитуры: Недостаточно");
//                    labelFurniture.setVisible(true);
//                    labelNotEnought.setText(labelNotEnought.getText() + arrayListNotEnought);
//                    labelNotEnought.setVisible(true);

//                if (enought){
//                    labelFurniture.setText("Наличие фурнитуры: Достаточно");
//                    labelFurniture.setVisible(true);
//                } else {
//                    labelFurniture.setText("Наличие фурнитуры: Недостаточно");
//                    labelFurniture.setVisible(true);
//                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });


        //Обработка нажатия на кнопку "Выход"
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/managerScreen.fxml", "Экран менеджера");
        });

        //Обработка нажатия на кнопку "Выход"
        buttonExit.setOnAction(event -> {
            buttonExit.getScene().getWindow().hide();
            new Service().changeScreen("/sample/view/login.fxml", "Авторизация");
        });
    }

}
