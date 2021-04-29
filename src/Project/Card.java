/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

/**
 *
 * @author Advice
 */
public class Card {

    private String color;
    private String value;

    public Card() {
    }

    public Card(String color, String value) {
        this.color = color;
        this.value = value;
    }

    public Card(int c, int v) {

        switch (c) { // green,yellow,red,blue,wild
            case 0 ->
                color = "Green";
            case 1 ->
                color = "Yellow";
            case 2 ->
                color = "Red";
            case 3 ->
                color = "Blue";
            case 4 ->
                color = "Wild";
        }
        if (c == 4) {  // +4,select color
            switch (v) {
                case 0 ->
                    value = "+4";
                case 1 ->
                    value = "SelectColor";
            }
        } else {
            switch (v) { // 0-9,+2,reverse,skip
                case 0 ->
                    value = "0";
                case 1 ->
                    value = "1";
                case 2 ->
                    value = "2";
                case 3 ->
                    value = "3";
                case 4 ->
                    value = "4";
                case 5 ->
                    value = "5";
                case 6 ->
                    value = "6";
                case 7 ->
                    value = "7";
                case 8 ->
                    value = "8";
                case 9 ->
                    value = "9";
                case 10 ->
                    value = "+2";
                case 11 ->
                    value = "Reverse";
                case 12 ->
                    value = "Skip";
            }
        }
    }

    public String getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return color + "_" + value;
    }

}
