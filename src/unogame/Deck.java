/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unogame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Deck class represents a deck of UNO cards.
 * It manages the creation, shuffling, and drawing of cards from the deck.
 * The deck includes standard UNO cards, action cards, and wild cards.
 * 
 * @author Dell
 */
class Deck {
    // List to hold all the cards in the deck
    private List<Card> cards;

    /**
     * Constructor for the Deck class.
     * Initializes the deck by creating all UNO cards and shuffling them.
     */
    public Deck() {
        cards = new ArrayList<>();
        
        // Define the colors and values for standard UNO cards
        String[] colors = {"Red", "Yellow", "Green", "Blue"};
        String[] values = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Skip", "Reverse", "Draw Two"};

        // Add regular and action cards for each color
        for (String color : colors) {
            for (String value : values) {
                // Add one card for "0" and two cards for other values
                cards.add(new Card(color, value));
                if (!value.equals("0")) {
                    cards.add(new Card(color, value));
                }
            }
        }

        // Add Wild and Wild Draw Four cards (4 of each)
        for (int i = 0; i < 4; i++) {
            cards.add(new Card("Wild", "Wild"));
            cards.add(new Card("Wild", "Wild Draw Four"));
        }

        // Shuffle the deck to randomize the order of cards
        shuffle();
    }

    /**
     * Shuffles the deck to randomize the order of cards.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Draws a card from the deck.
     * Removes the top card from the deck and returns it.
     * 
     * @return the card drawn from the deck
     */
    public Card drawCard() {
        return cards.remove(cards.size() - 1);
    }

    /**
     * Returns the current size of the deck.
     * 
     * @return the number of cards remaining in the deck
     */
    public int size() {
        return cards.size();
    }
}
