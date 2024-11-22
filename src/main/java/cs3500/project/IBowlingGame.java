package cs3500.project;

import java.util.List;

/**
 * Interface representing the basic operations of a bowling game.
 * This interface defines methods for recording rolls, calculating the total score,
 * and calculating the score up to a specified number of frames.
 */
public interface IBowlingGame {

    /**
     * Returns a list of all rolls made during the game.
     *
     * @return a List of integers representing the number of pins knocked down on each roll
     */
    public List<Integer> getRolls();

    /**
     * Records a roll in the game, specifying the number of pins knocked down.
     *
     * @param pins the number of pins knocked down during the roll
     */
    public void roll(int pins);

    /**
     * Calculates the total score for the game, including bonuses for strikes and spares.
     * The score is calculated for all 10 frames of the game.
     *
     * @return the total score for the game
     */
    public int score();

    /**
     * Calculates the score for a specified number of frames, including bonuses for strikes and spares.
     * This method allows for calculating the score partway through the game, up to the number of frames played.
     *
     * @param framesPlayed the number of frames played so far in the game
     * @return the score for the specified number of frames
     */
    public int currentScore(int framesPlayed);

}