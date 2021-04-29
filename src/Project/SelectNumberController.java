/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Advice
 */
public class SelectNumberController implements Initializable {
    
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void drag(MouseEvent event) throws IOException {
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
    private void btn1Pressed(ActionEvent event) throws FileNotFoundException, IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("Data/numberOfPlayer.dat"));
        dos.writeInt(1);
        dos.close();
        stackPane.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("InputName.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Enter your name");
        stage.show();
    }
    
    @FXML
    private void btn2Pressed(ActionEvent event) throws FileNotFoundException, IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("Data/numberOfPlayer.dat"));
        dos.writeInt(2);
        dos.close();
        stackPane.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("InputName.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Enter your name");
        stage.show();
    }
    
    @FXML
    private void btn3Pressed(ActionEvent event) throws FileNotFoundException, IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("Data/numberOfPlayer.dat"));
        dos.writeInt(3);
        dos.close();
        stackPane.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("InputName.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Enter your name");
        stage.show();
    }
    
}
