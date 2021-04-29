/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Advice
 */
public class InputNameController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private StackPane stackPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void saveBtnPressed(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (!nameTextField.getText().isEmpty()) {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Data/name.dat"));
            DataOutputStream score = new DataOutputStream(new FileOutputStream("Data/score.dat"));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Data/nameOfBot.dat"));
            DataInputStream dis = new DataInputStream(new FileInputStream("Data/numberOfPlayer.dat"));

            ArrayList<String> a = new ArrayList<>();

            int num = dis.readInt();
            dis.close();

            ArrayList<String> botName = (ArrayList<String>) ois.readObject();
            ois.close();

            a.add(nameTextField.getText());
            for (int i = 1; i <= num; i++) {
                String st = botName.get((int) (Math.random() * botName.size()));
                a.add(st);
                botName.remove(a);
            }
            oos.writeObject(a);
            oos.close();
            
            score.writeInt(0);
            score.close();

            stackPane.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("INGame.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("UNO");
            stage.show();
        }
    }

    @FXML
    private void backBtnPressed(ActionEvent event) throws IOException {
        stackPane.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("UNO");
        stage.show();
    }

}
