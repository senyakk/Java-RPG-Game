package main;

public class Game {

    // VIEW
    private final GameScreen gameWindow;
    private final GamePanel gamePanel;

    // CONTROLLER
    private final GameController gameController;

    // MODEL
    private final GameModel gameModel;

    // GAME LOOP
    public Thread gameLoop;

    /**
     * Game constructor that creates GamePanel, GameController, GameModel, and GameWindow, and starts the game loop
     */
    public Game() {
        gamePanel = new GamePanel(); // View: Creates the game panel for rendering
        gameModel = new GameModel(); // Model: Creates the game model
        gameController = new GameController(gamePanel, gameModel); // Controller: Creates the game controller with the game model
        gamePanel.setGameController(gameController); // View: Sets the game controller for the game panel
        gameWindow = new GameScreen(gamePanel); // View: Creates the game window

        gamePanel.requestFocus();

        startGameLoop();
    }

    private void startGameLoop() {
        gameLoop = new Thread(gameController); // Controller: Creates a thread for the game controller
        gameLoop.start(); // Controller: Starts the game loop in the controller
    }
}
