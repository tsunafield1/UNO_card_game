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
    @FXML
    private Button preBtn;
    @FXML
    private Button nextBtn;
    private int page;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        page = 1;
        try {
            // TODO
            imgView.setImage(new Image(new File("Image/HowToPlay/page1.png").toURI().toURL().toString()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(HowToPlayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        preBtn.setVisible(false);
    }

    @FXML
    private void previousBtnPressed(ActionEvent event) throws MalformedURLException, MalformedURLException {
        nextBtn.setVisible(true);
        if (page > 1) {
            page--;
            imgView.setImage(new Image(new File("Image/HowToPlay/page" + Integer.toString(page) + ".png").toURI().toURL().toString()));
        }
        if (page == 1) {
            preBtn.setVisible(false);
        }
    }

    @FXML
    private void nextBtnPressed(ActionEvent event) throws MalformedURLException {
        preBtn.setVisible(true);
        if (page < 7) {
            page++;
            imgView.setImage(new Image(new File("Image/HowToPlay/page" + Integer.toString(page) + ".png").toURI().toURL().toString()));
        }
        if (page == 7) {
            nextBtn.setVisible(false);
        }
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

}
