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
class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void drawCard(Deck deck) {
        hand.add(deck.drawCard());
    }

    public void playCard(Card card) {
        hand.remove(card);
    }

    public boolean hasPlayableCard(Card topCard) {
        return hand.stream().anyMatch(card -> 
            card.getColor().equals(topCard.getColor()) || 
            card.getValue().equals(topCard.getValue()) ||
            card.getColor().equals("Wild")
        );
    }

    public int getScore() {
        // Calculate score as the sum of remaining cards' values (simplified scoring).
        return hand.size();
    }
}