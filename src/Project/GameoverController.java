/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Advice
 */
public class GameoverController implements Initializable {

    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button retryBtn;

    private int score;
    @FXML
    private Text winnerText;

    private String name;
    @FXML
    private Text scoreText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        name = "";
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream("Data/score.dat"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameoverController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            score = dis.readInt();
        } catch (IOException ex) {
            Logger.getLogger(GameoverController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dis.close();
        } catch (IOException ex) {
            Logger.getLogger(GameoverController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (score > 0) {
            retryBtn.setText("Continue");
        }
        try {
            dis = new DataInputStream(new FileInputStream("Data/winner.dat"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameoverController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            name = dis.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(GameoverController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dis.close();
        } catch (IOException ex) {
            Logger.getLogger(GameoverController.class.getName()).log(Level.SEVERE, null, ex);
        }
        winnerText.setText("The winner is " + name + "!");
        if (score > 0) {
            scoreText.setText("Score: " + Integer.toString(score));
        }
        else scoreText.setText("");
    }

    @FXML
    private void retryBtnPressed(ActionEvent event) throws IOException {
        stackPane.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("INGame.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Game Over!");
        stage.show();
    }

    @FXML
    private void backToMenuPressed(ActionEvent event) throws IOException, ClassNotFoundException {
        if (score > 0) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Data/leaderboard.dat"));
            ArrayList<String> leaderName = (ArrayList<String>) ois.readObject();
            ArrayList<Integer> leaderScore = (ArrayList<Integer>) ois.readObject();
            ois.close();

            for (int i = 0; i < 5; i++) {
                if (score > leaderScore.get(i)) {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Data/leaderboard.dat"));
                    leaderName.add(i, name);
                    leaderScore.add(i, score);

                    leaderName.remove(i + 1);
                    leaderScore.remove(i + 1);

                    oos.writeObject(leaderName);
                    oos.writeObject(leaderScore);
                    oos.close();
                    break;
                }
            }
        }
        stackPane.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("UNO");
        stage.show();
    }

}
