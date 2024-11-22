package cs3500.project;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bowling game and implements the {@link IBowlingGame} interface.
 * This class allows for rolling pins, calculating the score, and keeping track of the rolls made during the game.
 * It supports both full game scoring and partial game scoring for a specified number of frames.
 */
public class BowlingGame implements IBowlingGame {
    private final List<Integer> rolls = new ArrayList<>();


    /**
     * Records a roll by adding the number of pins knocked down to the list of rolls.
     *
     * @param pins the number of pins knocked down in a single roll
     */
    public void roll(int pins) {
        rolls.add(pins);
    }

    /**
     * Calculates and returns the total score of the game after 10 frames.
     * This method provides the score for the full game.
     *
     * @return the total score of the game after 10 frames
     */
    public int score() {
        return calculateScore(10);
    }

    /**
     * Calculates and returns the total score after a specified number of frames.
     * This method is useful for calculating the score for partial games.
     *
     * @param framesPlayed the number of frames played so far
     * @return the total score after the specified number of frames
     */
    public int currentScore(int framesPlayed) {
        return calculateScore(framesPlayed);
    }

    /**
     * Returns a copy of the list of rolls made during the game.
     *
     * @return a list of integers representing the rolls
     */
    public List<Integer> getRolls() {
        return new ArrayList<>(rolls);
    }

    /**
     * Checks if the current roll is a strike (10 pins in the first roll of a frame).
     *
     * @param rollIndex the index of the current roll
     * @return true if the current roll is a strike, false otherwise
     */
    private boolean isStrike(int rollIndex) {
        return rolls.get(rollIndex) == 10;
    }

    /**
     * Checks if the current frame is a spare (the sum of the first and second rolls equals 10).
     *
     * @param rollIndex the index of the current roll
     * @return true if the current frame is a spare, false otherwise
     */
    private boolean isSpare(int rollIndex) {
        return rolls.get(rollIndex) + rolls.get(rollIndex + 1) == 10;
    }

    /**
     * Calculates the bonus score for a strike.
     * The bonus score is the sum of the next two rolls.
     *
     * @param rollIndex the index of the current roll
     * @return the bonus score for the strike
     */
    private int strikeBonus(int rollIndex) {
        if (rollIndex + 1 < rolls.size() && rollIndex + 2 < rolls.size()) {
            return rolls.get(rollIndex + 1) + rolls.get(rollIndex + 2);
        }
        return 0;
    }

    /**
     * Calculates the bonus score for a spare.
     * The bonus score is the number of pins knocked down in the next roll.
     *
     * @param rollIndex the index of the current roll
     * @return the bonus score for the spare
     */
    private int spareBonus(int rollIndex) {
        if (rollIndex + 2 < rolls.size()) {
            return rolls.get(rollIndex + 2);
        }
        return 0;
    }

    /**
     * Calculates the score for a single frame.
     *
     * @param rollIndex the index of the first roll in the frame
     * @return the score for the frame
     */
    private int frameScore(int rollIndex) {
        return rolls.get(rollIndex) + rolls.get(rollIndex + 1);
    }

    /**
     * A refactored method to calculate the total score for a specified number of frames.
     * This method is used to calculate both full game scores and partial game scores.
     *
     * @param framesPlayed the number of frames played so far
     * @return the total score after the specified number of frames
     */
    private int calculateScore(int framesPlayed) {
        int score = 0;
        int rollIndex = 0;
        int frame = 0;

        while (frame < framesPlayed && rollIndex < rolls.size()) {
            if (isStrike(rollIndex)) {
                score += 10 + strikeBonus(rollIndex);
                rollIndex++;
            } else if (isSpare(rollIndex)) {
                score += 10 + spareBonus(rollIndex);
                rollIndex += 2;
            } else {
                score += frameScore(rollIndex);
                rollIndex += 2;
            }
            frame++;
        }

        return score;
    }
}