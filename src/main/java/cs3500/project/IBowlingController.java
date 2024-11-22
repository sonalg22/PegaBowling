package cs3500.project;

/**
 * This interface defines the operations for a Bowling Controller.
 * It is responsible for managing the flow of a bowling game, including starting the game
 * and facilitating gameplay for all frames, while interacting with the user or other components.
 */
public interface IBowlingController {

    /**
     * Starts the bowling game.
     * This method initializes the game, provides instructions to the user, and handles the game loop.
     * After each game, it offers the option to restart or quit.
     */
    public void startGame();

    /**
     * Facilitates the gameplay for a single session of the bowling game.
     * This method processes all 10 frames of a game, handles scoring rules (e.g., strikes, spares,
     * and bonus rolls in the 10th frame), and ensures the scores are updated and displayed frame by frame.
     */
    public void playGame();
}