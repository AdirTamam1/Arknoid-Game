// 318936507 Adir Tamam

import Game.Game;

/**
 * The main class for the Ass5Game program.
 * This class contains the main method to start and run the game.
 */
public class Ass5Game {

    /**
     * The main method that initializes and runs the game.
     *
     * @param args The command line arguments (not used in this program).
     */
    public static void main(String[] args) {
        // Create a new Game instance
        Game game = new Game();

        // Initialize the game
        game.initialize();

        // Run the game
        game.run();
    }
}
