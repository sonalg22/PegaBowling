package cs3500.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

class BowlingGameTest {

    private IBowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = new BowlingGame();
    }

    @Test
    void testScore_noRolls() {
        assertEquals(0, bowlingGame.score());
    }

    @Test
    void testScore_allGutters() {
        // Simulate a game with only gutters (0 pins every roll)
        for (int i = 0; i < 20; i++) {
            bowlingGame.roll(0);
        }
        assertEquals(0, bowlingGame.score());
    }

    @Test
    void testScore_perfectGame() {
        // Simulate a perfect game (strike every frame)
        for (int i = 0; i < 12; i++) {
            bowlingGame.roll(10);
        }
        assertEquals(300, bowlingGame.score());
    }

    @Test
    void testScore_spares() {
        // Simulate a game with all spares
        for (int i = 0; i < 10; i++) {
            bowlingGame.roll(5);
            bowlingGame.roll(5); // Spare in each frame
        }
        bowlingGame.roll(5); // Extra roll for last spare
        assertEquals(150, bowlingGame.score());
    }

    @Test
    void testGetRolls() {
        bowlingGame.roll(3);
        bowlingGame.roll(4);
        List<Integer> rolls = bowlingGame.getRolls();
        assertEquals(2, rolls.size());
        assertEquals(3, rolls.get(0));
        assertEquals(4, rolls.get(1));
    }

    @Test
    void testScore_singleStrike() {
        // Simulate a game with a single strike followed by gutter balls
        bowlingGame.roll(10); // Strike
        for (int i = 0; i < 18; i++) {
            bowlingGame.roll(0); // Gutter balls
        }
        assertEquals(10, bowlingGame.score());
    }

    @Test
    void testScore_singleSpare() {
        // Simulate a game with a single spare followed by gutter balls
        bowlingGame.roll(5);  // First roll of the spare
        bowlingGame.roll(5);  // Second roll of the spare (completing the spare)
        for (int i = 0; i < 18; i++) {
            bowlingGame.roll(0); // Gutter balls
        }
        assertEquals(10, bowlingGame.score());
    }


    @Test
    void testScore_edgeCase_after10Frames() {
        // Simulate 10 frames, then check the score before any extra rolls
        for (int i = 0; i < 9; i++) {  // 9 frames for spares with bonuses
            bowlingGame.roll(5);
            bowlingGame.roll(5); // Spare in each frame
        }

        // 10th frame spare (with bonus roll)
        bowlingGame.roll(5);  // 1st roll of 10th frame
        bowlingGame.roll(5);  // 2nd roll of 10th frame
        bowlingGame.roll(5);  // 3rd roll (bonus for the spare in the 10th frame)

        // After 10 frames + 1 bonus roll (which gives 5 extra points)
        assertEquals(150, bowlingGame.score());
    }

    @Test
    void testScore_mixedStrikesSpareNormal() {
        // Simulate a game with a mix of strikes, spares, and normal frames.
        // Frame 1: Strike (10), Frame 2: Spare (5, 5), Frame 3: Normal (3, 4), Frame 4: Strike (10), Frame 5: Normal (7, 2)
        // Frame 6: Spare (6, 4), Frame 7: Normal (2, 3), Frame 8: Strike (10), Frame 9: Spare (5, 5), Frame 10: Normal (8, 1)

        bowlingGame.roll(10); // Strike
        bowlingGame.roll(5);  // Spare
        bowlingGame.roll(5);
        bowlingGame.roll(3);  // Normal
        bowlingGame.roll(4);
        bowlingGame.roll(10); // Strike
        bowlingGame.roll(7);  // Normal
        bowlingGame.roll(2);
        bowlingGame.roll(6);  // Spare
        bowlingGame.roll(4);
        bowlingGame.roll(2);  // Normal
        bowlingGame.roll(3);
        bowlingGame.roll(10); // Strike
        bowlingGame.roll(5);  // Spare
        bowlingGame.roll(5);
        bowlingGame.roll(8);  // Normal
        bowlingGame.roll(1);

        assertEquals(132, bowlingGame.score());  // Expected score after all frames
    }

}
