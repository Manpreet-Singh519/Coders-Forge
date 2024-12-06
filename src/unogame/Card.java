/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unogame;

/**
 * The Card class represents a single card in the UNO game.
 * Each card has a color (e.g., red, blue, green, yellow) and a value (e.g., numbers, action cards like "Skip").
 * This class provides methods to retrieve the color and value of the card, 
 * and a string representation for displaying the card.
 * 
 * @author Dell
 */
class Card {
    // The color of the card (e.g., "Red", "Blue", "Green", "Yellow").
    private String color;

    // The value of the card (e.g., "1", "Reverse", "Skip").
    private String value;

    /**
     * Constructor for the Card class.
     * Initializes the card with the specified color and value.
     * 
     * @param color the color of the card
     * @param value the value of the card
     */
    public Card(String color, String value) {
        this.color = color;
        this.value = value;
    }

    /**
     * Gets the color of the card.
     * 
     * @return the color of the card
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the value of the card.
     * 
     * @return the value of the card
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns a string representation of the card.
     * Combines the color and value into a single string for display.
     * 
     * @return a string in the format "color value"
     */
    @Override
    public String toString() {
        return color + " " + value;
    }
}
