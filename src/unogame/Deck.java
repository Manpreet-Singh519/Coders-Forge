/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unogame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Dell
 */
class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] colors = {"Red", "Yellow", "Green", "Blue"};
        String[] values = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Skip", "Reverse", "Draw Two"};

        // Add regular cards
        for (String color : colors) {
            for (String value : values) {
                cards.add(new Card(color, value));
                if (!value.equals("0")) {
                    cards.add(new Card(color, value)); // Add duplicates for 1-9 and action cards
                }
            }
        }

        // Add Wild and Wild Draw Four cards
        for (int i = 0; i < 4; i++) {
            cards.add(new Card("Wild", "Wild"));
            cards.add(new Card("Wild", "Wild Draw Four"));
        }

        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.remove(cards.size() - 1);
    }

    public int size() {
        return cards.size();
    }
}
