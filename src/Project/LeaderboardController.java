/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Advice
 */
public class LeaderboardController implements Initializable {

    @FXML
    private StackPane stackPane;
    @FXML
    private Text name1;
    @FXML
    private Text name2;
    @FXML
    private Text name3;
    @FXML
    private Text name4;
    @FXML
    private Text name5;
    @FXML
    private Text score1;
    @FXML
    private Text score2;
    @FXML
    private Text score3;
    @FXML
    private Text score4;
    @FXML
    private Text score5;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObjectInputStream ois = null;
        ArrayList<String> leaderName = null;
        ArrayList<Integer> leaderScore = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("Data/leaderboard.dat"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeaderboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeaderboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            leaderName = (ArrayList<String>) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(LeaderboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LeaderboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            leaderScore = (ArrayList<Integer>) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(LeaderboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LeaderboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ois.close();
        } catch (IOException ex) {
            Logger.getLogger(LeaderboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        name1.setText(leaderName.get(0));
        name2.setText(leaderName.get(1));
        name3.setText(leaderName.get(2));
        name4.setText(leaderName.get(3));
        name5.setText(leaderName.get(4));

        score1.setText(Integer.toString(leaderScore.get(0)));
        score2.setText(Integer.toString(leaderScore.get(1)));
        score3.setText(Integer.toString(leaderScore.get(2)));
        score4.setText(Integer.toString(leaderScore.get(3)));
        score5.setText(Integer.toString(leaderScore.get(4)));
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
