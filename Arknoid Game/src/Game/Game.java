// 318936507 Adir Tamam
package Game;

import Collidable.Block;
import Collidable.Paddle;
import Sprites.Ball;
import Sprites.ScoreIndicator;
import Sprites.Sprite;
import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.GUI;
import Geometry.Rectangle;
import Geometry.Point;
import Collidable.Collidable;

import java.awt.Color;

import Base.BlockRemover;

import java.util.Random;

import Base.Counter;

import Base.ScoreTrackingListener;
import Base.BallRemover;

/**
 * The Game class represents the main logic of the game, managing sprites, collidables, and game initialization.
 */
public class Game {
    private Counter numBlocks;

    private Counter numBalls;

    private Counter score;
    private SpriteCollection sprites;
    private GameEnvironment environment;

    /**
     * Constructs a new Game with an empty SpriteCollection and GameEnvironment.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.numBalls = new Counter();
        this.numBlocks = new Counter();
        this.score = new Counter();
    }

    /**
     * Retrieves the SpriteCollection associated with this object.
     *
     * @return The SpriteCollection object containing sprites.
     */
    public SpriteCollection getSpriteCollection() {
        return this.sprites;
    }

    /**
     * Retrieves the GameEnvironment associated with this object.
     *
     * @return The GameEnvironment object containing the game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }



    /**
     * Adds a Collidable to the game environment.
     *
     * @param c The Collidable to add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a Sprite to the game sprite collection.
     *
     * @param s The Sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initializes a new game by creating Blocks, Balls, and Paddle and adding them to the game.
     */
    public void initialize() {
        BlockRemover blockremover = new BlockRemover(this, this.numBlocks);
        BallRemover ballRemover = new BallRemover(this, this.numBalls);
        ScoreTrackingListener trackingListener = new ScoreTrackingListener(this.score);
        Color[] ballsColors = {Color.YELLOW, Color.GREEN, Color.RED};
        for (int i = 0; i < 3; i++) {
            Ball ball1 = new Ball(398 - 5 * i, 370 - 5 * i, 5, ballsColors[i], environment);
            ball1.setVelocity(0, -10 - i);
            ball1.addToGame(this);
            this.numBalls.increase(1);
        }

        // ... (code for creating and adding the top, left, and right blocks)
        Block boundary1 = new Block(new Rectangle(new Point(0, 20), 800, 20), Color.gray);  // Top block
        Block boundary2 = new Block(new Rectangle(new Point(0, 0), 20, 600), Color.gray);  // Left block
        Block boundary3 = new Block(new Rectangle(new Point(780, 0), 20, 600), Color.gray);  // Right block
        boundary1.addToGame(this);
        boundary2.addToGame(this);
        boundary3.addToGame(this);

        // death block creation
        Block deathRegion = new Block(new Rectangle(new Point(0, 601), 800, 1), Color.blue);  // death block
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);

        // score indicator creation
        ScoreIndicator indicator = new ScoreIndicator(this.score);
        indicator.addToGame(this);


        // blocks creation
        Color[] colors = {Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.CYAN, Color.pink, Color.RED};
        Random random = new Random();
        int color;
        double width = 50;
        double height = 20;

        // Loop for creating and adding the blocks in a pyramid shape
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 12 - 2 * j; i++) {
                color = random.nextInt(6);
                Point p = new Point(100 + width * j + width * i, height * j + height + 20);
                Block block = new Block(new Rectangle(p, width, height), colors[color]);
                block.addToGame(this);
                block.addHitListener(blockremover);
                block.addHitListener(trackingListener);
                this.numBlocks.increase(1);
            }
        }
    }


    // Run the game -- start the animation loop.

    /**
     * Runs the game, starting the animation loop.
     */
    public void run() {
        // Initialize the GUI and other necessary components
        GUI gui = new GUI("Game Example", 800, 600);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();

        // Create and add a paddle to the game
        Paddle paddle = new Paddle(new Rectangle(new Point(360, 560), 100, 20), Color.YELLOW, 800, 10, keyboard);
        paddle.addToGame(this);

        // Set up animation parameters
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        // Main game loop
        while (true) {

            long startTime = System.currentTimeMillis(); // timing

            // Draw the background and game elements
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, 800, 600);
            this.sprites.drawAllOn(d);
            d.setColor(Color.BLUE);

            // Enhance the animation appearance
            d.fillRectangle(0, 560, 20, 20);
            d.fillRectangle(780, 560, 20, 20);
            d.drawLine(20, 560, 20, 580);


            // Draw the paddle
            paddle.drawOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // Control the frame rate
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            if (this.numBlocks.getValue() == 0) {
                this.score.increase(100);
                gui.close();
                return;
            }
            if (this.numBalls.getValue() == 0) {
                gui.close();
                return;
            }
        }
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c The Collidable object to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidableList().remove(c);
    }

    /**
     * Removes a sprite from the sprite collection.
     *
     * @param s The Sprite object to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }


}
