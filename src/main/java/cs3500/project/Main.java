package cs3500.project;

import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        Readable input = new InputStreamReader(System.in);
        BowlingController controller = new BowlingController(input);
        controller.startGame();
    }
}