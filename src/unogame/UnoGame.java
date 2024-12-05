/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package unogame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class UnoGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Game Introduction
        System.out.println("*");
        System.out.println("*            Welcome to UNO!           *");
        System.out.println("*");
        System.out.println("Game Rules:");
        System.out.println("1. Each player starts with 7 cards.");
        System.out.println("2. Match the top card by color or value.");
        System.out.println("3. Play special cards like 'Skip', 'Reverse', 'Draw Two', and 'Wild' to change the game!");
        System.out.println("4. The first player to get rid of all their cards wins!");
        System.out.println();
        System.out.println("Get ready to test your strategy and luck in this classic card game!");
        System.out.println("*");
        System.out.println();

        // Ask for readiness
        System.out.println("Are you ready to start the game? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        while (!response.equals("yes")) {
            System.out.println("Take your time! Let me know when you're ready. Type 'yes' to start:");
            response = scanner.nextLine().trim().toLowerCase();
        }

        // Player setup
        System.out.print("Enter the number of players (2-10): ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        while (numPlayers < 2 || numPlayers > 10) {
            System.out.println("Invalid number of players. Please enter a number between 2 and 10:");
            numPlayers = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
        }

        List<String> playerNames = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter the name of player " + i + ": ");
            String playerName = scanner.nextLine();
            playerNames.add(playerName);
        }

        System.out.println("\nGreat! Setting up the game now...");
        System.out.println("*");
        System.out.println();

        // Start the game
        Game game = new Game(playerNames);
        game.play();
    }
}
