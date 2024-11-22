package cs3500.project;

import java.util.List;
import java.util.Scanner;

/**
 * This class acts as the controller for the Bowling Game, handling user input, managing the game flow,
 * and displaying scores. It interfaces with an {@link IBowlingGame} instance to perform game logic and keeps
 * the game interactive by prompting the user for rolls and handling the game restart process.
 */
public class BowlingController implements IBowlingController{
    private IBowlingGame bowlingGame;
    private Scanner scanner;

    /**
     * Constructor that initializes the {@link IBowlingGame} instance and the scanner for user input.
     * The game is initialized to a new {@link BowlingGame} object.
     */
    public BowlingController(Readable readable) {
        this.bowlingGame = new BowlingGame();
        this.scanner = new Scanner(readable);
    }

    /**
     * Starts the bowling game, prompts the user for roll inputs, and calculates the score frame by frame.
     * The game continues in a loop, allowing the user to restart after each game by typing 'y' or quit by typing 'q'.
     */
    public void startGame() {
        System.out.println("Welcome to the Bowling Score Calculator!");
        System.out.println("Enter your rolls frame by frame.");

        while (true) {
            playGame();
            System.out.println("\nGame Over!");
            System.out.println("Your total score is: " + bowlingGame.score());
            System.out.println("Do you want to start over? Type 'y' to play again or 'q' to quit.");
            String response = scanner.next();
            if (response.equalsIgnoreCase("q")) {
                break;
            }
            resetGame();
        }
    }

    /**
     * Plays the game for 10 frames, prompting the user for input for each roll and calculating the score.
     * It handles the logic for strikes, spares, and the 10th frame where bonus rolls are allowed.
     */
    public void playGame() {
        for (int frame = 1; frame <= 10; frame++) {
            System.out.println("\nFrame " + frame + ":");
            int roll1 = getRollInput("Enter roll 1: ", 0, 10);
            bowlingGame.roll(roll1);

            if (roll1 == 10 && frame != 10) {
                System.out.println("Strike! The next 2 rolls will be used as bonus points.");
            }

            if (roll1 < 10) {
                int roll2 = getRollInput("Enter roll 2: ", 0, 10 - roll1);
                bowlingGame.roll(roll2);

                if (roll1 + roll2 == 10) {
                    System.out.println("Spare! The next roll will be used as bonus points.");
                }
            }

            if (frame == 10) {
                List<Integer> rolls = bowlingGame.getRolls();

                if (rolls.size() >= 2 && (rolls.get(rolls.size() - 2) == 10 || rolls.get(rolls.size() - 2) + rolls.get(rolls.size() - 1) == 10)) {
                    int roll3 = getRollInput("Enter roll 3: ", 0, 10);
                    bowlingGame.roll(roll3);
                    System.out.println("Third roll in 10th frame added as bonus points.");
                }
            }

            int score = bowlingGame.currentScore(frame);
            System.out.println("Current score after Frame " + frame + ": " + score);
        }
    }

    /**
     * Prompts the user for a valid roll input within the specified range.
     * If the user enters an invalid input or presses 'q', the game will quit.
     *
     * @param prompt the message to display when asking for the input
     * @param minPins the minimum valid number of pins to enter
     * @param maxPins the maximum valid number of pins to enter
     * @return the validated roll input
     */
    private int getRollInput(String prompt, int minPins, int maxPins) {
        int roll = -1;
        while (true) {
            System.out.print(prompt);
            String input = scanner.next();
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Quitting the game...");
                System.exit(0);
            }
            try {
                roll = Integer.parseInt(input);
                if (roll < minPins || roll > maxPins) {
                    throw new IllegalArgumentException("Number of pins is out of bounds, please try again.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
        return roll;
    }

    /**
     * Resets the game by creating a new instance of {@link BowlingGame}, effectively starting a new game.
     */
    private void resetGame() {
        bowlingGame = new BowlingGame();
    }
}