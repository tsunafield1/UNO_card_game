/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Advice
 */
public class MenuController implements Initializable {

    @FXML
    private ImageView imgViewMenuLogo;
    @FXML
    private Button playBtn;
    @FXML
    private Button howToPlayBtn;
    @FXML
    private Button leaderboardBtn;
    @FXML
    private Button quitBtn;
    @FXML
    private AnchorPane MenuPane;
    Circle a = new Circle(50);
    @FXML
    private StackPane stackPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            imgViewMenuLogo.setImage(loadImage("Image/MenuLogo.png"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void playBtnPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectNumber.fxml"));
        Parent root = loader.load();
        Scene scene = MenuPane.getScene();
        root.translateYProperty().set(-713);
        stackPane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.75), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        timeline.play();
    }

    @FXML
    private void howToPlayBtnPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HowToPlay.fxml"));
        Parent root = loader.load();
        Scene scene = MenuPane.getScene();
        root.translateYProperty().set(-713);
        stackPane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.75), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        timeline.play();
    }

    @FXML
    private void leaderboardBtnPressed(ActionEvent event) throws MalformedURLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Leaderboard.fxml"));
        Parent root = loader.load();
        Scene scene = MenuPane.getScene();
        root.translateYProperty().set(-713);
        stackPane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.75), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        timeline.play();
    }

    @FXML
    private void quitBtnPressed(ActionEvent event) {
        System.exit(0);
    }

    private Image loadImage(String url) throws MalformedURLException {
        File file = new File(url);
        String imgUrl = file.toURI().toURL().toString();
        return new Image(imgUrl);
    }
}
