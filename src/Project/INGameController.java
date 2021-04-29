/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Advice
 */
public class INGameController implements Initializable {

    @FXML
    private ImageView deckImgView;
    @FXML
    private Text scoreText;
    @FXML
    private Button leftBtn;
    @FXML
    private Button rightBtn;
    @FXML
    private Text numberOfCardsText;
    @FXML
    private ImageView handImgView1;
    @FXML
    private ImageView handImgView2;
    @FXML
    private ImageView handImgView3;
    @FXML
    private ImageView handImgView4;
    @FXML
    private ImageView handImgView5;
    @FXML
    private ImageView handImgView6;
    @FXML
    private ImageView handImgView7;
    @FXML
    private ImageView stockImgView;
    @FXML
    private Text whoseTurn;
    @FXML
    private Text logText;

    private UNOGame uno;
    private ArrayList<Card> cardInHand;
    @FXML
    private StackPane stackPane;

    private int startIndex;
    private boolean isDraw;
    private String[] playerName;
    @FXML
    private BorderPane selectColorPane;
    @FXML
    private Button spBtn;
    @FXML
    private StackPane pane;
    private int currentScore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentScore = 0;
        selectColorPane.setVisible(false);
        leftBtn.setVisible(false);
        rightBtn.setVisible(false);
        spBtn.setVisible(false);
        isDraw = true;
        startIndex = 0;
        ArrayList<String> a = new ArrayList<>();
        ObjectInputStream ois = null;
        DataInputStream score = null;
        try {
            score = new DataInputStream(new FileInputStream("Data/score.dat"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // TODO
            ois = new ObjectInputStream(new FileInputStream("Data/name.dat"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            a = (ArrayList<String>) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ois.close();
        } catch (IOException ex) {
            Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        playerName = a.toArray(new String[0]);
        uno = new UNOGame(playerName);
        try {
            deckImgView.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String buf = uno.start();
        if (uno.getCurrentCard().getValue().equals("Skip")) {
            spBtn.setVisible(true);
        } else if (uno.getCurrentCard().getValue().equals("+2")) {
            spBtn.setVisible(true);
        }
        cardInHand = uno.getCardInHand(uno.getCurrentPlayer());
        numberOfCardsText.setText(Integer.toString(cardInHand.size()));
        logText.setText(buf);
        whoseTurn.setText(playerName[uno.getCurrentPlayer()] + "'s Turn");
        try {
            currentScore = score.readInt();
        } catch (IOException ex) {
            Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        scoreText.setText(Integer.toString(currentScore));
        try {
            stockImgView.setImage(new Image(new File("Image/Card/" + uno.getCurrentCard().toString() + ".png").toURI().toURL().toString()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (uno.getCurrentPlayer() == 0) {
            try {
                handImgView1.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex).toString() + ".png").toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                handImgView2.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 1).toString() + ".png").toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                handImgView3.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 2).toString() + ".png").toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                handImgView4.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 3).toString() + ".png").toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                handImgView5.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 4).toString() + ".png").toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                handImgView6.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 5).toString() + ".png").toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                handImgView7.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 6).toString() + ".png").toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                handImgView1.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                handImgView2.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                handImgView3.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                handImgView4.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                handImgView5.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                handImgView6.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                handImgView7.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(INGameController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void deckPressed(MouseEvent event) throws MalformedURLException {
        if (uno.getCurrentPlayer() == 0) {
            if (isDraw == true && uno.isPlus() == false && uno.getIsSkip() == false) {
                isDraw = false;
                logText.setText(uno.draw());
                numberOfCardsText.setText(Integer.toString(cardInHand.size()));
                spBtn.setVisible(true);
                if (cardInHand.size() > 7) {
                    rightBtn.setVisible(true);
                }
                handImgView1.setImage(new Image(new File("").toURI().toURL().toString()));
                handImgView2.setImage(new Image(new File("").toURI().toURL().toString()));
                handImgView3.setImage(new Image(new File("").toURI().toURL().toString()));
                handImgView4.setImage(new Image(new File("").toURI().toURL().toString()));
                handImgView5.setImage(new Image(new File("").toURI().toURL().toString()));
                handImgView6.setImage(new Image(new File("").toURI().toURL().toString()));
                handImgView7.setImage(new Image(new File("").toURI().toURL().toString()));
                if (uno.getCurrentPlayer() == 0) {
                    handImgView1.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex).toString() + ".png").toURI().toURL().toString()));

                    if (cardInHand.size() > 1) {
                        handImgView2.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 1).toString() + ".png").toURI().toURL().toString()));

                    }
                    if (cardInHand.size() > 2) {
                        handImgView3.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 2).toString() + ".png").toURI().toURL().toString()));

                    }
                    if (cardInHand.size() > 3) {
                        handImgView4.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 3).toString() + ".png").toURI().toURL().toString()));

                    }
                    if (cardInHand.size() > 4) {
                        handImgView5.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 4).toString() + ".png").toURI().toURL().toString()));

                    }
                    if (cardInHand.size() > 5) {
                        handImgView6.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 5).toString() + ".png").toURI().toURL().toString()));

                    }
                    if (cardInHand.size() > 6) {
                        handImgView7.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 6).toString() + ".png").toURI().toURL().toString()));

                    }
                    if (cardInHand.size() > 7) {
                        rightBtn.setVisible(true);
                    }
                } else {
                    handImgView1.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                    if (cardInHand.size() > 1) {
                        handImgView2.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                    }
                    if (cardInHand.size() > 2) {
                        handImgView3.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                    }
                    if (cardInHand.size() > 3) {
                        handImgView4.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                    }
                    if (cardInHand.size() > 4) {
                        handImgView5.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                    }
                    if (cardInHand.size() > 5) {
                        handImgView6.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                    }
                    if (cardInHand.size() > 6) {
                        handImgView7.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                    }
                }
            } else {
                logText.setText("Cannot draw now");
            }
        }
    }

    @FXML
    private void leftBtnPressed(ActionEvent event) throws MalformedURLException {
        startIndex--;
        rightBtn.setVisible(true);
        if (startIndex == 0) {
            leftBtn.setVisible(false);
        }
        handImgView1.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex).toString() + ".png").toURI().toURL().toString()));
        handImgView2.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 1).toString() + ".png").toURI().toURL().toString()));
        handImgView3.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 2).toString() + ".png").toURI().toURL().toString()));
        handImgView4.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 3).toString() + ".png").toURI().toURL().toString()));
        handImgView5.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 4).toString() + ".png").toURI().toURL().toString()));
        handImgView6.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 5).toString() + ".png").toURI().toURL().toString()));
        handImgView7.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 6).toString() + ".png").toURI().toURL().toString()));
    }

    @FXML
    private void rightBtnPressed(ActionEvent event) throws MalformedURLException {
        startIndex++;
        leftBtn.setVisible(true);
        if (startIndex == cardInHand.size() - 7) {
            rightBtn.setVisible(false);
        }
        handImgView1.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex).toString() + ".png").toURI().toURL().toString()));
        handImgView2.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 1).toString() + ".png").toURI().toURL().toString()));
        handImgView3.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 2).toString() + ".png").toURI().toURL().toString()));
        handImgView4.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 3).toString() + ".png").toURI().toURL().toString()));
        handImgView5.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 4).toString() + ".png").toURI().toURL().toString()));
        handImgView6.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 5).toString() + ".png").toURI().toURL().toString()));
        handImgView7.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 6).toString() + ".png").toURI().toURL().toString()));
    }

    @FXML
    private void img1Pressed(MouseEvent event) throws MalformedURLException, IOException, ClassNotFoundException {
        if (uno.getCurrentPlayer() == 0) {
            operate(cardInHand.get(startIndex));
        }
    }

    @FXML
    private void img2Pressed(MouseEvent event) throws MalformedURLException, IOException, ClassNotFoundException {
        if (cardInHand.size() > 1 && uno.getCurrentPlayer() == 0) {
            operate(cardInHand.get(startIndex + 1));
        }
    }

    @FXML
    private void img3Pressed(MouseEvent event) throws MalformedURLException, IOException, ClassNotFoundException {
        if (cardInHand.size() > 2 && uno.getCurrentPlayer() == 0) {
            operate(cardInHand.get(startIndex + 2));
        }
    }

    @FXML
    private void img4Pressed(MouseEvent event) throws MalformedURLException, IOException, ClassNotFoundException {
        if (cardInHand.size() > 3 && uno.getCurrentPlayer() == 0) {
            operate(cardInHand.get(startIndex + 3));
        }
    }

    @FXML
    private void img5Pressed(MouseEvent event) throws MalformedURLException, IOException, ClassNotFoundException {
        if (cardInHand.size() > 4 && uno.getCurrentPlayer() == 0) {
            operate(cardInHand.get(startIndex + 4));
        }
    }

    @FXML
    private void img6Pressed(MouseEvent event) throws MalformedURLException, IOException, ClassNotFoundException {
        if (cardInHand.size() > 5 && uno.getCurrentPlayer() == 0) {
            operate(cardInHand.get(startIndex + 5));
        }
    }

    @FXML
    private void img7Pressed(MouseEvent event) throws MalformedURLException, IOException, ClassNotFoundException {
        if (cardInHand.size() > 6 && uno.getCurrentPlayer() == 0) {
            operate(cardInHand.get(startIndex + 6));
        }
    }

    private String operate() {
        String buf = "";
        String name = playerName[uno.getCurrentPlayer()];
        if (uno.isPlus()) {
            if (uno.canPlus()) {
                for (int i = 0; i < cardInHand.size(); i++) {
                    if (cardInHand.get(i).getValue().contains("+")) {
                        buf = uno.playPlus(cardInHand.get(i));
                        if (uno.getCurrentCard().getValue().equals("+4")) {
                            int r = (int) (Math.random() * 4);
                            switch (r) {
                                case 0:
                                    buf = uno.SelectColor("Red");
                                    break;
                                case 1:
                                    buf = uno.SelectColor("Blue");
                                    break;
                                case 2:
                                    buf = uno.SelectColor("Yellow");
                                    break;
                                case 3:
                                    buf = uno.SelectColor("Green");
                                    break;
                            }
                        }
                        break;
                    }
                }
            } else {
                buf = uno.draw(uno.getPlusStack());
                if (uno.getIsSkip()) {
                    buf += " & " + uno.skip();
                }
            }
        } else if (uno.getIsSkip()) {
            buf = uno.skip();
        } else {
            for (int i = 0; i < cardInHand.size(); i++) {
                buf = uno.play(cardInHand.get(i));
                if (!buf.equals("Cannot play this card")) {
                    if (buf.equals("Select color")) {
                        int r = (int) (Math.random() * 4);
                        switch (r) {
                            case 0:
                                buf = uno.SelectColor("Red");
                                break;
                            case 1:
                                buf = uno.SelectColor("Blue");
                                break;
                            case 2:
                                buf = uno.SelectColor("Yellow");
                                break;
                            case 3:
                                buf = uno.SelectColor("Green");
                                break;
                        }
                    }
                    break;
                } else if (i == cardInHand.size() - 1) {
                    if (isDraw == true) {
                        buf = uno.draw();
                        isDraw = false;
                    } else {
                        uno.changePlayer();
                    }
                    break;
                }
            }
        }
        if (buf.equals("Cannot play this card")) {
            return name + " passes";
        }
        return buf;
    }

    private void operate(Card play) throws MalformedURLException, IOException, ClassNotFoundException {
        String buf = "";
        int bu = uno.getCurrentPlayer();
        if (uno.isPlus()) {
            buf = uno.playPlus(play);
        } else if (uno.getIsSkip() && !uno.isPlus()) {
            buf = "Cannot play now";
        } else {
            buf = uno.play(play);
        }
        logText.setText(buf);
        cardInHand = uno.getCardInHand(uno.getCurrentPlayer());
        numberOfCardsText.setText(Integer.toString(cardInHand.size()));
        whoseTurn.setText(playerName[uno.getCurrentPlayer()] + "'s Turn");
        leftBtn.setVisible(false);
        rightBtn.setVisible(false);
        stockImgView.setImage(new Image(new File("Image/Card/" + uno.getCurrentCard().toString() + ".png").toURI().toURL().toString()));

        handImgView1.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView2.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView3.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView4.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView5.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView6.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView7.setImage(new Image(new File("").toURI().toURL().toString()));
        if (uno.getCurrentPlayer() == 0) {
            handImgView1.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex).toString() + ".png").toURI().toURL().toString()));

            if (cardInHand.size() > 1) {
                handImgView2.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 1).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 2) {
                handImgView3.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 2).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 3) {
                handImgView4.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 3).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 4) {
                handImgView5.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 4).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 5) {
                handImgView6.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 5).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > startIndex + 6) {
                handImgView7.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 6).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 7) {
                if (startIndex != 0) {
                    leftBtn.setVisible(true);
                }
                if (startIndex + 7 < cardInHand.size() - 1) {
                    rightBtn.setVisible(true);
                }
            }
        } else {
            handImgView1.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            if (cardInHand.size() > 1) {
                handImgView2.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 2) {
                handImgView3.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 3) {
                handImgView4.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 4) {
                handImgView5.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 5) {
                handImgView6.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 6) {
                handImgView7.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
        }
        if (uno.isSetColor()) {
            selectColorPane.setVisible(true);
        }
        if (uno.getCurrentPlayer() != 0) {
            spBtn.setVisible(true);
            startIndex = 0;
        }
        if (uno.getIsSkip()) {
            spBtn.setVisible(true);
        }
        if (bu != uno.getCurrentPlayer()) {
            isDraw = true;
        }
        if (uno.isGamover()) {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("Data/score.dat"));
            if (uno.getCardInHand(0).isEmpty()) {
                for (int i = 1; i < playerName.length; i++) {
                    ArrayList<Card> bufCardInHand = uno.getCardInHand(i);
                    while (!bufCardInHand.isEmpty()) {
                        Card bufCard = bufCardInHand.get(0);
                        if (bufCard.getValue().equals("+2")) {
                            currentScore += 10;
                        } else if (bufCard.getValue().equals("Skip")) {
                            currentScore += 10;
                        } else if (bufCard.getValue().equals("Reverse")) {
                            currentScore += 10;
                        } else if (bufCard.getValue().equals("+4")) {
                            currentScore += 50;
                        } else if (bufCard.getValue().equals("SelectColor")) {
                            currentScore += 50;
                        } else {
                            currentScore += Integer.parseInt(bufCard.getValue());
                        }
                        bufCardInHand.remove(0);
                    }
                }

                dos.writeInt(currentScore);
                dos.close();
            } else {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Data/leaderboard.dat"));
                ArrayList<String> leaderName = (ArrayList<String>) ois.readObject();
                ArrayList<Integer> leaderScore = (ArrayList<Integer>) ois.readObject();
                ois.close();

                for (int i = 0; i < 5; i++) {
                    if (currentScore > leaderScore.get(i)) {
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Data/leaderboard.dat"));
                        leaderName.add(i, playerName[0]);
                        leaderScore.add(i, currentScore);

                        leaderName.remove(i + 1);
                        leaderScore.remove(i + 1);

                        oos.writeObject(leaderName);
                        oos.writeObject(leaderScore);
                        oos.close();
                        break;
                    }
                }
                dos.writeInt(0);
                dos.close();
            }
            DataOutputStream winner = new DataOutputStream(new FileOutputStream("Data/winner.dat"));
            for (int i = 0; i < playerName.length; i++) {
                if (uno.getCardInHand(i).isEmpty()) {
                    winner.writeUTF(playerName[i]);
                    winner.close();
                    break;
                }
            }
            stackPane.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Gameover.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Game Over!");
            stage.show();
        }
    }

    @FXML
    private void blueBtnPressed(ActionEvent event) throws MalformedURLException {
        String buf = uno.SelectColor("Blue");

        logText.setText(buf);
        cardInHand = uno.getCardInHand(uno.getCurrentPlayer());
        numberOfCardsText.setText(Integer.toString(cardInHand.size()));
        whoseTurn.setText(playerName[uno.getCurrentPlayer()] + "'s Turn");
        startIndex = 0;
        leftBtn.setVisible(false);
        rightBtn.setVisible(false);

        handImgView1.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView2.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView3.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView4.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView5.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView6.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView7.setImage(new Image(new File("").toURI().toURL().toString()));
        if (uno.getCurrentPlayer() == 0) {
            handImgView1.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex).toString() + ".png").toURI().toURL().toString()));

            if (cardInHand.size() > 1) {
                handImgView2.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 1).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 2) {
                handImgView3.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 2).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 3) {
                handImgView4.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 3).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 4) {
                handImgView5.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 4).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 5) {
                handImgView6.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 5).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 6) {
                handImgView7.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 6).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 7) {
                rightBtn.setVisible(true);
            }
        } else {
            handImgView1.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            if (cardInHand.size() > 1) {
                handImgView2.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 2) {
                handImgView3.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 3) {
                handImgView4.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 4) {
                handImgView5.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 5) {
                handImgView6.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 6) {
                handImgView7.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
        }
        selectColorPane.setVisible(false);
        if (uno.getCurrentPlayer() != 0) {
            spBtn.setVisible(true);
        }
    }

    @FXML
    private void redBtnPressed(ActionEvent event) throws MalformedURLException {
        String buf = uno.SelectColor("Red");

        logText.setText(buf);
        cardInHand = uno.getCardInHand(uno.getCurrentPlayer());
        numberOfCardsText.setText(Integer.toString(cardInHand.size()));
        whoseTurn.setText(playerName[uno.getCurrentPlayer()] + "'s Turn");
        startIndex = 0;
        leftBtn.setVisible(false);
        rightBtn.setVisible(false);

        handImgView1.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView2.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView3.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView4.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView5.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView6.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView7.setImage(new Image(new File("").toURI().toURL().toString()));
        if (uno.getCurrentPlayer() == 0) {
            handImgView1.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex).toString() + ".png").toURI().toURL().toString()));

            if (cardInHand.size() > 1) {
                handImgView2.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 1).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 2) {
                handImgView3.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 2).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 3) {
                handImgView4.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 3).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 4) {
                handImgView5.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 4).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 5) {
                handImgView6.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 5).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 6) {
                handImgView7.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 6).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 7) {
                rightBtn.setVisible(true);
            }
        } else {
            handImgView1.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            if (cardInHand.size() > 1) {
                handImgView2.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 2) {
                handImgView3.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 3) {
                handImgView4.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 4) {
                handImgView5.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 5) {
                handImgView6.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 6) {
                handImgView7.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
        }
        selectColorPane.setVisible(false);
        if (uno.getCurrentPlayer() != 0) {
            spBtn.setVisible(true);
        }
    }

    @FXML
    private void yellowBtnPressed(ActionEvent event) throws MalformedURLException {
        String buf = uno.SelectColor("Yellow");

        logText.setText(buf);
        cardInHand = uno.getCardInHand(uno.getCurrentPlayer());
        numberOfCardsText.setText(Integer.toString(cardInHand.size()));
        whoseTurn.setText(playerName[uno.getCurrentPlayer()] + "'s Turn");
        startIndex = 0;
        leftBtn.setVisible(false);
        rightBtn.setVisible(false);

        handImgView1.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView2.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView3.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView4.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView5.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView6.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView7.setImage(new Image(new File("").toURI().toURL().toString()));
        if (uno.getCurrentPlayer() == 0) {
            handImgView1.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex).toString() + ".png").toURI().toURL().toString()));

            if (cardInHand.size() > 1) {
                handImgView2.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 1).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 2) {
                handImgView3.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 2).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 3) {
                handImgView4.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 3).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 4) {
                handImgView5.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 4).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 5) {
                handImgView6.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 5).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 6) {
                handImgView7.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 6).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 7) {
                rightBtn.setVisible(true);
            }
        } else {
            handImgView1.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            if (cardInHand.size() > 1) {
                handImgView2.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 2) {
                handImgView3.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 3) {
                handImgView4.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 4) {
                handImgView5.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 5) {
                handImgView6.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 6) {
                handImgView7.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
        }
        selectColorPane.setVisible(false);
        if (uno.getCurrentPlayer() != 0) {
            spBtn.setVisible(true);
        }
    }

    @FXML
    private void greenBtnPressed(ActionEvent event) throws MalformedURLException {
        String buf = uno.SelectColor("Green");

        logText.setText(buf);
        cardInHand = uno.getCardInHand(uno.getCurrentPlayer());
        numberOfCardsText.setText(Integer.toString(cardInHand.size()));
        whoseTurn.setText(playerName[uno.getCurrentPlayer()] + "'s Turn");
        startIndex = 0;
        leftBtn.setVisible(false);
        rightBtn.setVisible(false);

        handImgView1.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView2.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView3.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView4.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView5.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView6.setImage(new Image(new File("").toURI().toURL().toString()));
        handImgView7.setImage(new Image(new File("").toURI().toURL().toString()));
        if (uno.getCurrentPlayer() == 0) {
            handImgView1.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex).toString() + ".png").toURI().toURL().toString()));

            if (cardInHand.size() > 1) {
                handImgView2.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 1).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 2) {
                handImgView3.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 2).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 3) {
                handImgView4.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 3).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 4) {
                handImgView5.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 4).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 5) {
                handImgView6.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 5).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 6) {
                handImgView7.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 6).toString() + ".png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 7) {
                rightBtn.setVisible(true);
            }
        } else {
            handImgView1.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            if (cardInHand.size() > 1) {
                handImgView2.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 2) {
                handImgView3.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 3) {
                handImgView4.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 4) {
                handImgView5.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 5) {
                handImgView6.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
            if (cardInHand.size() > 6) {
                handImgView7.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

            }
        }
        selectColorPane.setVisible(false);
        if (uno.getCurrentPlayer() != 0) {
            spBtn.setVisible(true);
        }
    }

    @FXML
    private void spBtnPressed(ActionEvent event) throws MalformedURLException, IOException, ClassNotFoundException {
        String buf = null;
        if (uno.getCurrentPlayer() == 0) {
            if (uno.isPlus()) {
                buf = uno.draw(uno.getPlusStack());
                spBtn.setVisible(false);
                if (uno.getIsSkip()) {
                    buf += " & " + uno.skip();
                }
            } else if (uno.getIsSkip() && !uno.isPlus()) {
                buf = uno.skip();
            } else {
                buf = playerName[uno.getCurrentPlayer()] + " passes";
                uno.changePlayer();
            }
            isDraw = true;
            logText.setText(buf);
            cardInHand = uno.getCardInHand(uno.getCurrentPlayer());
            numberOfCardsText.setText(Integer.toString(cardInHand.size()));
            whoseTurn.setText(playerName[uno.getCurrentPlayer()] + "'s Turn");

            leftBtn.setVisible(false);
            rightBtn.setVisible(false);
            stockImgView.setImage(new Image(new File("Image/Card/" + uno.getCurrentCard().toString() + ".png").toURI().toURL().toString()));

            handImgView1.setImage(new Image(new File("").toURI().toURL().toString()));
            handImgView2.setImage(new Image(new File("").toURI().toURL().toString()));
            handImgView3.setImage(new Image(new File("").toURI().toURL().toString()));
            handImgView4.setImage(new Image(new File("").toURI().toURL().toString()));
            handImgView5.setImage(new Image(new File("").toURI().toURL().toString()));
            handImgView6.setImage(new Image(new File("").toURI().toURL().toString()));
            handImgView7.setImage(new Image(new File("").toURI().toURL().toString()));
            if (uno.getCurrentPlayer() == 0) {
                handImgView1.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex).toString() + ".png").toURI().toURL().toString()));
                if (cardInHand.size() > 1) {
                    handImgView2.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 1).toString() + ".png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 2) {
                    handImgView3.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 2).toString() + ".png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 3) {
                    handImgView4.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 3).toString() + ".png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 4) {
                    handImgView5.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 4).toString() + ".png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 5) {
                    handImgView6.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 5).toString() + ".png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > startIndex + 6) {
                    handImgView7.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 6).toString() + ".png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 7) {
                    if (startIndex != 0) {
                        leftBtn.setVisible(true);
                    }
                    rightBtn.setVisible(true);
                }
            }
            if (uno.getCurrentPlayer() != 0) {
                handImgView1.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                if (cardInHand.size() > 1) {
                    handImgView2.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 2) {
                    handImgView3.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 3) {
                    handImgView4.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 4) {
                    handImgView5.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 5) {
                    handImgView6.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 6) {
                    handImgView7.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                }
                spBtn.setVisible(true);
                startIndex = 0;
            }
        } else {
            int bu = uno.getCurrentPlayer();
            logText.setText(operate());
            if (bu != uno.getCurrentPlayer()) {
                isDraw = true;
            }
            cardInHand = uno.getCardInHand(uno.getCurrentPlayer());
            numberOfCardsText.setText(Integer.toString(cardInHand.size()));
            whoseTurn.setText(playerName[uno.getCurrentPlayer()] + "'s Turn");

            leftBtn.setVisible(false);
            rightBtn.setVisible(false);
            stockImgView.setImage(new Image(new File("Image/Card/" + uno.getCurrentCard().toString() + ".png").toURI().toURL().toString()));

            handImgView1.setImage(new Image(new File("").toURI().toURL().toString()));
            handImgView2.setImage(new Image(new File("").toURI().toURL().toString()));
            handImgView3.setImage(new Image(new File("").toURI().toURL().toString()));
            handImgView4.setImage(new Image(new File("").toURI().toURL().toString()));
            handImgView5.setImage(new Image(new File("").toURI().toURL().toString()));
            handImgView6.setImage(new Image(new File("").toURI().toURL().toString()));
            handImgView7.setImage(new Image(new File("").toURI().toURL().toString()));
            if (uno.getCurrentPlayer() == 0) {
                handImgView1.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex).toString() + ".png").toURI().toURL().toString()));
                if (cardInHand.size() > 1) {
                    handImgView2.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 1).toString() + ".png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 2) {
                    handImgView3.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 2).toString() + ".png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 3) {
                    handImgView4.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 3).toString() + ".png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 4) {
                    handImgView5.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 4).toString() + ".png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 5) {
                    handImgView6.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 5).toString() + ".png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 6) {
                    handImgView7.setImage(new Image(new File("Image/Card/" + cardInHand.get(startIndex + 6).toString() + ".png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 7) {
                    if (startIndex != 0) {
                        leftBtn.setVisible(true);
                    }
                    rightBtn.setVisible(true);
                }
                if (uno.isPlus() || uno.getIsSkip()) {
                    spBtn.setVisible(true);
                } else {
                    spBtn.setVisible(false);
                }
            }
            if (uno.getCurrentPlayer() != 0) {
                handImgView1.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                if (cardInHand.size() > 1) {
                    handImgView2.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 2) {
                    handImgView3.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 3) {
                    handImgView4.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 4) {
                    handImgView5.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 5) {
                    handImgView6.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                }
                if (cardInHand.size() > 6) {
                    handImgView7.setImage(new Image(new File("Image/Card/Back.png").toURI().toURL().toString()));

                }
                spBtn.setVisible(true);
                startIndex = 0;
            }
        }
        if (uno.isGamover()) {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("Data/score.dat"));
            if (uno.getCardInHand(0).isEmpty()) {
                for (int i = 1; i < playerName.length; i++) {
                    ArrayList<Card> bufCardInHand = uno.getCardInHand(i);
                    while (!bufCardInHand.isEmpty()) {
                        Card bufCard = bufCardInHand.get(0);
                        if (bufCard.getValue().equals("+2")) {
                            currentScore += 10;
                        } else if (bufCard.getValue().equals("Skip")) {
                            currentScore += 10;
                        } else if (bufCard.getValue().equals("Reverse")) {
                            currentScore += 10;
                        } else if (bufCard.getValue().equals("+4")) {
                            currentScore += 50;
                        } else if (bufCard.getValue().equals("SelectColor")) {
                            currentScore += 50;
                        } else {
                            currentScore += Integer.parseInt(bufCard.getValue());
                        }
                        bufCardInHand.remove(0);
                    }
                }

                dos.writeInt(currentScore);
                dos.close();
            } else {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Data/leaderboard.dat"));
                ArrayList<String> leaderName = (ArrayList<String>) ois.readObject();
                ArrayList<Integer> leaderScore = (ArrayList<Integer>) ois.readObject();
                ois.close();

                for (int i = 0; i < 5; i++) {
                    if (currentScore > leaderScore.get(i)) {
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Data/leaderboard.dat"));
                        leaderName.add(i, playerName[0]);
                        leaderScore.add(i, currentScore);

                        leaderName.remove(i + 1);
                        leaderScore.remove(i + 1);

                        oos.writeObject(leaderName);
                        oos.writeObject(leaderScore);
                        oos.close();
                        break;
                    }
                }
                dos.writeInt(0);
                dos.close();
            }
            DataOutputStream winner = new DataOutputStream(new FileOutputStream("Data/winner.dat"));
            for (int i = 0; i < playerName.length; i++) {
                if (uno.getCardInHand(i).isEmpty()) {
                    winner.writeUTF(playerName[i]);
                    winner.close();
                    break;
                }
            }
            stackPane.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Gameover.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Game Over!");
            stage.show();
        }
    }
}
