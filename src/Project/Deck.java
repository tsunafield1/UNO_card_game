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
public class Deck {

    private ArrayList<Card> card = new ArrayList<>();

    public Deck() {
        card.clear();
        card.add(new Card(0, 0)); // '0' 1 card each color
        card.add(new Card(1, 0));
        card.add(new Card(2, 0));
        card.add(new Card(3, 0));
        for (int i = 1; i <= 12; i++) { // '1'-'9', '+2', reverse, skip 2 card each color
            for (int j = 0; j <= 3; j++) {
                card.add(new Card(j, i));
                card.add(new Card(j, i));
            }
        }
        for (int i = 0; i <= 1; i++) { // '+4', select color 4 card each
            card.add(new Card(4, i));
            card.add(new Card(4, i));
            card.add(new Card(4, i));
            card.add(new Card(4, i));
        }
    }

    public Card draw() {
        int ran = (int) (Math.random() * card.size());
        Card buf = card.get(ran);
        card.remove(buf);
        return buf;
    }

    public ArrayList<Card> draw(int a) {
        ArrayList<Card> buf = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            buf.add(draw());
        }
        return buf;
    }

    public void add(Card a) {
        card.add(a);
    }
}
