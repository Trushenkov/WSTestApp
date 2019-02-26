package sample.controllers.alerts;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Date: 12.02.2019 (вторник)
 * Project name: TestApplication
 * Package name: sample.controllers
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class AlertTemplateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label detailsLabel;

    @FXML
    private HBox actionParent;

    @FXML
    private Button okButton;

    @FXML
    private HBox okParent;

    @FXML
    private Label messageLabel;

    @FXML
    void initialize() {
        okButton.setOnAction(event -> {
            okButton.getScene().getWindow().hide();
        });
    }
}
