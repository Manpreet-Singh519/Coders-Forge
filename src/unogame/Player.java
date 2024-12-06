/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unogame;

import java.util.ArrayList;
import java.util.List;

/**
 * The Player class represents a player in the UNO game.
 * It manages the player's name, their hand of cards, and actions they can perform,
 * such as drawing or playing cards.
 * 
 * @author Dell
 */
class Player {
    private String name; // Name of the player
    private List<Card> hand; // List of cards currently in the player's hand

    /**
     * Constructor for the Player class.
     * Initializes the player's name and creates an empty hand of cards.
     * 
     * @param name The player's name
     */
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    /**
     * Retrieves the player's name.
     * 
     * @return The player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the player's hand of cards.
     * 
     * @return The player's hand
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * Draws a card from the deck and adds it to the player's hand.
     * 
     * @param deck The deck to draw the card from
     */
    public void drawCard(Deck deck) {
        hand.add(deck.drawCard());
    }

    /**
     * Removes the specified card from the player's hand, simulating playing that card.
     * 
     * @param card The card to play
     */
    public void playCard(Card card) {
        hand.remove(card);
    }

    /**
     * Checks if the player has a card in their hand that can be played on the current top card.
     * A card is playable if it matches the top card's color, value, or is a Wild card.
     * 
     * @param topCard The current top card in play
     * @return True if the player has at least one playable card, false otherwise
     */
    public boolean hasPlayableCard(Card topCard) {
        return hand.stream().anyMatch(card -> 
            card.getColor().equals(topCard.getColor()) || 
            card.getValue().equals(topCard.getValue()) ||
            card.getColor().equals("Wild")
        );
    }

    /**
     * Calculates the player's score, which is the number of cards remaining in their hand.
     * (In actual UNO, scores are based on the values of cards, but this is simplified.)
     * 
     * @return The number of cards in the player's hand
     */
    public int getScore() {
        return hand.size();
    }
}
