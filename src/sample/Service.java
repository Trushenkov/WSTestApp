package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Date: 12.02.2019 (вторник)
 * Project name: TestApplication
 * Package name: sample
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Service {

    /**
     * Метод для перехода на другое окно приложения
     *
     * @param path  путь к fxml файлу
     * @param title заголовок окна
     */
    public void changeScreen(String path, String title) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add("/sample/css/style.css");
        stage.setTitle("Test application - " + title + "!");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Метод для открытия модального окна
     *
     * @param path        путь к файлу разметки (fxml-file)
     * @param title       заголовок модального окна
     * @param actionEvent событие
     */
    public void changeScreenModalWindow(String path, String title, ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Test application - " + title + "!");
        if (actionEvent != null) {
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        }
        stage.setResizable(false);
        stage.show();
    }

    public void changeScreenModal(String path, String title, MouseEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Test application - " + title + "!");
        if (actionEvent != null) {
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        }
        stage.setResizable(false);
        stage.show();
    }
}
