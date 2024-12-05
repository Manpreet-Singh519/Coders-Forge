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
 *
 * @author Dell
 */

class Game {
    private Deck deck;
    private List<Player> players;
    private Card topCard;
    private int currentPlayerIndex;
    private boolean isReversed;

    public Game(List<String> playerNames) {
        deck = new Deck();
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        currentPlayerIndex = 0;
        isReversed = false;

        // Deal 7 cards to each player
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck);
            }
        }

        // Set the first card
        topCard = deck.drawCard();
        while (topCard.getColor().equals("Wild")) { // Ensure the first card is not a Wild card
            deck.shuffle();
            topCard = deck.drawCard();
        }
    }

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

                    // Validate card
                    if (chosenCard.getColor().equals(topCard.getColor()) ||
                            chosenCard.getValue().equals(topCard.getValue()) ||
                            chosenCard.getColor().equals("Wild")) {

                        currentPlayer.playCard(chosenCard);
                        topCard = chosenCard;

                        // Apply special card effects
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

            // Check for win condition
            if (currentPlayer.getHand().isEmpty()) {
                winner = currentPlayer;
            }

            // Move to the next player
            currentPlayerIndex = getNextPlayerIndex();
        }

        if (winner != null) {
            System.out.println("\n" + winner.getName() + " wins the game!");
        } else if (deck.size() == 0) {
            System.out.println("\nDeck is empty! Game over.");
        }
        printFinalStandings();
        determineWinner();
    }

    private int getNextPlayerIndex() {
        return (currentPlayerIndex + (isReversed ? -1 : 1) + players.size()) % players.size();
    }

    private Player getNextPlayer() {
        return players.get(getNextPlayerIndex());
    }

    private void printFinalStandings() {
        System.out.println("\nFinal Standings:");
        players.sort(Comparator.comparingInt(Player::getScore));
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getScore() + " cards remaining");
        }
    }

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