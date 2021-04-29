/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.util.ArrayList;

/**
 *
 * @author Advice
 */
public class UNOGame {

    private int currentPlayer;
    private String[] playerID; // Name of each player

    private Deck deck; // deck
    private ArrayList<ArrayList<Card>> cardInHand;  // Card in each player hand
    private Card currentCard;  // Card on the table

    private boolean direction;
    private boolean setColor;
    private boolean isSkip;

    private String currentColor;
    private String currentValue;

    private int plusStack;

    public UNOGame(String[] playerID) {
        this.playerID = playerID;
    }

    public String start() {
        deck = new Deck();
        currentPlayer = 0;
        direction = false;
        cardInHand = new ArrayList<>();
        plusStack = 0;
        setColor = false;
        isSkip = false;

        for (int i = 0; i < this.playerID.length; i++) {
            ArrayList<Card> buf = new ArrayList<>(deck.draw(7));
            cardInHand.add(buf);
        }
        currentCard = deck.draw();
        while (currentCard.getColor().equals("Wild")) {
            deck.add(currentCard);
            currentCard = deck.draw();
        }

        currentColor = currentCard.getColor();
        currentValue = currentCard.getValue();
        if (currentValue.equals("Skip")) {
            return skip();
        } else if (currentValue.equals("Reverse")) {
            direction = !direction;
            return "direction was reversed";
        } else if (currentValue.equals("+2")) {
            plusStack += 2;
            return "plus stack are +" + Integer.toString(plusStack);
        }
        return "";
    }

    public String draw() {
        cardInHand.get(currentPlayer).add(deck.draw());
        return playerID[currentPlayer] + " draw card";
    }

    public String draw(int num) {
        for (int i = 1; i <= num; i++) {
            cardInHand.get(currentPlayer).add(deck.draw());
        }
        plusStack = 0;
        return playerID[currentPlayer] + " draw " + num + " cards";
    }

    public String play(Card a) {
        String name = playerID[currentPlayer];
        if (a.getColor().equals("Wild")) {
            if (a.getValue().equals("+4")) {
                plusStack += 4;
                isSkip = true;
            }
            setColor = true;
            deck.add(currentCard);
            currentCard = a;
            currentValue = currentCard.getValue();
            cardInHand.get(currentPlayer).remove(a);
            return "Select color";
        } else if (a.getColor().equals(currentColor) || a.getValue().equals(currentValue)) {
            if (a.getValue().equals("+2")) {
                plusStack += 2;
                deck.add(currentCard);
                currentCard = a;
                currentColor = currentCard.getColor();
                currentValue = currentCard.getValue();
                cardInHand.get(currentPlayer).remove(a);
                changePlayer();
                return "plus stack are +" + Integer.toString(plusStack);
            } else if (a.getValue().equals("Reverse")) {
                direction = !direction;
                deck.add(currentCard);
                currentCard = a;
                currentColor = currentCard.getColor();
                currentValue = currentCard.getValue();
                cardInHand.get(currentPlayer).remove(a);
                changePlayer();
                return "direction was reversed";
            } else if (a.getValue().equals("Skip")) {
                isSkip = true;
            }
            deck.add(currentCard);
            currentCard = a;
            currentColor = currentCard.getColor();
            currentValue = currentCard.getValue();
            cardInHand.get(currentPlayer).remove(a);
            changePlayer();
            return name + " plays card";
        }
        return "Cannot play this card";
    }

    public String SelectColor(String a) {
        currentColor = a;
        setColor = false;
        changePlayer();
        return "Set color to " + a;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public ArrayList<Card> getCardInHand(int id) {
        return cardInHand.get(id);
    }

    public void changePlayer() {
        if (direction == true) {
            currentPlayer = currentPlayer - 1;
            if (currentPlayer < 0) {
                currentPlayer = playerID.length - 1;
            }
        } else {
            currentPlayer = (currentPlayer + 1) % playerID.length;
        }
    }

    public boolean isGamover() {
        for (int i = 0; i < playerID.length; i++) {
            if (cardInHand.get(i).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean isSetColor() {
        return setColor;
    }

    public boolean isPlus() {
        return plusStack > 0;
    }

    public int getPlusStack() {
        return plusStack;
    }

    public boolean canPlus() {
        boolean buf = false;
        for (int i = 0; i < cardInHand.get(currentPlayer).size(); i++) {
            if (cardInHand.get(currentPlayer).get(i).getValue().equals("+4")) {
                buf = true;
            } else if (cardInHand.get(currentPlayer).get(i).getValue().equals("+2")) {
                buf = true;
            }
        }
        return buf;
    }

    public boolean getIsSkip() {
        return isSkip;
    }

    public String playPlus(Card a) {
        if (a.getValue().equals("+4")) {
            plusStack += 4;
            isSkip = true;
            setColor = true;
            deck.add(currentCard);
            currentCard = a;
            currentValue = currentCard.getValue();
            cardInHand.get(currentPlayer).remove(a);
            return "plus stack are +" + Integer.toString(plusStack);
        } else if (a.getValue().equals("+2")) {
            plusStack += 2;
            deck.add(currentCard);
            currentCard = a;
            currentColor = currentCard.getColor();
            currentValue = currentCard.getValue();
            cardInHand.get(currentPlayer).remove(a);
            changePlayer();
            return "plus stack are +" + Integer.toString(plusStack);
        } else {
            return "Cannot play this card";
        }
    }

    public String skip() {
        String buf = playerID[currentPlayer];
        changePlayer();
        isSkip = false;
        return buf + " was skipped!";
    }
}
