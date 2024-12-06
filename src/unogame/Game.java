/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unogame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * The Game class manages the flow of an UNO game, including initialization,
 * gameplay, player turns, and determining the winner.
 * It handles special card effects, player actions, and game termination conditions.
 * 
 * @author Dell
 */
class Game {
    private Deck deck; // The deck of UNO cards used in the game
    private List<Player> players; // List of players participating in the game
    private Card topCard; // The current top card in play
    private int currentPlayerIndex; // Index of the player whose turn it is
    private boolean isReversed; // Flag to indicate if the turn order is reversed

    /**
     * Constructor for the Game class.
     * Initializes the game with the specified player names, deals cards,
     * and sets the initial top card.
     * 
     * @param playerNames List of player names
     */
    public Game(List<String> playerNames) {
        deck = new Deck();
        players = new ArrayList<>();

        // Initialize players and deal 7 cards to each
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        currentPlayerIndex = 0;
        isReversed = false;

        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck);
            }
        }

        // Draw the initial top card, ensuring it's not a Wild card
        topCard = deck.drawCard();
        while (topCard.getColor().equals("Wild")) {
            deck.shuffle();
            topCard = deck.drawCard();
        }
    }

    /**
     * Starts and manages the gameplay loop until a winner is determined
     * or the deck runs out of cards.
     */
    public void play() {
        Scanner scanner = new Scanner(System.in);
        Player winner = null;

        System.out.println("\nLet the game begin!");

        while (winner == null && deck.size() > 0) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("\nTop card: " + topCard);
            System.out.println(currentPlayer.getName() + "'s turn. Your hand: " + currentPlayer.getHand());
            System.out.println("Cards left in deck: " + deck.size());

            System.out.println("Type the card index to play, or type 'draw' to draw a card.");
            System.out.println("To quit the game, type 'quit'.");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("quit")) {
                System.out.println("\nGame ended early! Thanks for playing!");
                printFinalStandings();
                return;
            }

            if (input.equals("draw")) {
                if (deck.size() == 0) {
                    System.out.println("No cards left in the deck to draw!");
                } else {
                    System.out.println(currentPlayer.getName() + " draws a card.");
                    currentPlayer.drawCard(deck);
                }
            } else {
                try {
                    int index = Integer.parseInt(input);
                    Card chosenCard = currentPlayer.getHand().get(index);

                    // Validate if the chosen card matches the top card
                    if (chosenCard.getColor().equals(topCard.getColor()) ||
                        chosenCard.getValue().equals(topCard.getValue()) ||
                        chosenCard.getColor().equals("Wild")) {

                        currentPlayer.playCard(chosenCard);
                        topCard = chosenCard;

                        // Handle special card effects
                        if (topCard.getValue().equals("Reverse")) {
                            isReversed = !isReversed;
                        } else if (topCard.getValue().equals("Skip")) {
                            currentPlayerIndex = getNextPlayerIndex();
                        } else if (topCard.getValue().equals("Draw Two")) {
                            getNextPlayer().drawCard(deck);
                            getNextPlayer().drawCard(deck);
                        }
                    } else {
                        System.out.println("Invalid card! Try again.");
                        continue;
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("Invalid input. Try again.");
                    continue;
                }
            }

            // Check if the current player has won
            if (currentPlayer.getHand().isEmpty()) {
                winner = currentPlayer;
            }

            // Move to the next player
            currentPlayerIndex = getNextPlayerIndex();
        }

        // Determine the game outcome
        if (winner != null) {
            System.out.println("\n" + winner.getName() + " wins the game!");
        } else if (deck.size() == 0) {
            System.out.println("\nDeck is empty! Game over.");
        }
        printFinalStandings();
        determineWinner();
    }

    /**
     * Determines the index of the next player based on the current direction of play.
     * 
     * @return Index of the next player
     */
    private int getNextPlayerIndex() {
        return (currentPlayerIndex + (isReversed ? -1 : 1) + players.size()) % players.size();
    }

    /**
     * Retrieves the next player in turn order.
     * 
     * @return The next player
     */
    private Player getNextPlayer() {
        return players.get(getNextPlayerIndex());
    }

    /**
     * Prints the final standings of the game, sorted by the number of cards remaining.
     */
    private void printFinalStandings() {
        System.out.println("\nFinal Standings:");
        players.sort(Comparator.comparingInt(Player::getScore));
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getScore() + " cards remaining");
        }
    }

    /**
     * Determines the winner(s) based on the number of cards remaining
     * and handles tie scenarios.
     */
    private void determineWinner() {
        int minCards = players.stream()
                .mapToInt(Player::getScore)
                .min()
                .orElse(0);

        List<Player> winners = new ArrayList<>();
        for (Player player : players) {
            if (player.getScore() == minCards) {
                winners.add(player);
            }
        }

        if (winners.size() == 1) {
            System.out.println("\nWinner: " + winners.get(0).getName() + " with " + minCards + " cards remaining!");
        } else {
            System.out.println("\nIt's a tie! The following players have " + minCards + " cards remaining:");
            for (Player player : winners) {
                System.out.println(player.getName());
            }
        }
        System.out.println("\nThanks for playing UNO! Goodbye!");
    }
}
