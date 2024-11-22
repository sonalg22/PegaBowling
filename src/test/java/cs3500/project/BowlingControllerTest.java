package cs3500.project;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;

class BowlingControllerTest {

    private BowlingController controller;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testStartGame_quit() {
        // Simulate user input to quit the game
        String userInput = "q\n";
        Readable readable = new StringReader(userInput);
        controller = new BowlingController(readable);

        // Ensure no exceptions are thrown and the game quits gracefully
        assertDoesNotThrow(() -> controller.startGame());
    }

    @Test
    void testStartGame_restart() {
        // Simulate user input to restart and then quit the game
        String userInput = "y\nq\n";
        Readable readable = new StringReader(userInput);
        controller = new BowlingController(readable);

        // Verify that the game handles the restart command gracefully
        assertDoesNotThrow(() -> controller.startGame());
    }

    @Test
    void testPlayGame_strikeScenario() {
        // Simulate a game with a strike in frame 1
        String userInput = "10\nq\n"; // First roll: strike, then quit
        Readable readable = new StringReader(userInput);
        controller = new BowlingController(readable);

        // Run the game and ensure no exceptions occur
        assertDoesNotThrow(() -> controller.startGame());
    }

    @Test
    void testPlayGame_spareScenario() {
        // Simulate a spare scenario (roll1 + roll2 = 10)
        String userInput = "5\n5\nq\n"; // Spare, then quit
        Readable readable = new StringReader(userInput);
        controller = new BowlingController(readable);

        // Run the game and ensure no exceptions occur
        assertDoesNotThrow(() -> controller.startGame());
    }

    @Test
    void testPlayGame_normalFrame() {
        String userInput = "3\n4\nq\n"; // Normal frame, then quit
        Readable readable = new StringReader(userInput);
        controller = new BowlingController(readable);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        assertDoesNotThrow(() -> controller.startGame());
        System.setOut(originalOut);
    }

}
