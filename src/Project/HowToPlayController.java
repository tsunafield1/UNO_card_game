/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Advice
 */
public class HowToPlayController implements Initializable {

    @FXML
    private StackPane stackPane;
    @FXML
    private ImageView imgView;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void previousBtnPressed(ActionEvent event) {
    }

    @FXML
    private void backBtnPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        Scene scene = pane.getScene();
        pane.setTranslateY(0);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(pane.translateYProperty(), -pane.getHeight(), Interpolator.EASE_OUT);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.75), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stackPane.getChildren().remove(pane);
                stackPane.getChildren().add(root);
            }
        });
        timeline.play();
    }

    @FXML
    private void nextBtnPressed(ActionEvent event) {
    }

}
