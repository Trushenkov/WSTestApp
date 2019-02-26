package sample.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Date: 24.02.2019 (воскресенье)
 * Project name: TestApplication
 * Package name: sample.animation
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Shake {

    private TranslateTransition tt;

    public Shake(Node node) {
        node.setStyle("-fx-border-color: red;");
        tt = new TranslateTransition(Duration.millis(100), node);
        tt.setFromX(0);
        tt.setByX(10);
        tt.setCycleCount(3);
        tt.setAutoReverse(true);
    }

    public void playAnimation() {
        tt.playFromStart();
    }
}
