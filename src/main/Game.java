package main;

public class Game {

    // SCREEN SETTINGS
    public final static int defaultTileSize = 64;
    public final static float scale = 1.5f;
    public static final int tileSize = (int)(defaultTileSize * scale);
    // MAXIMUM NUMBER OF TILES IN A COLUMN
    public static final int maxTileCol = 10;
    // MAXIMUM NUMBER OF ROWS IN A COLUMN
    public static final int maxTileRow = 6;
    public final static int screenWidth = tileSize * maxTileCol;
    public final static int screenHeight = tileSize * maxTileRow;

    // VIEW : RENDERING THE GAME SCREEN
    private GameScreen gameWindow;
    private GamePanel gamePanel;

    // CONTROLLER
    private GameController gameController;

    // MODEL
    private GameModel gameModel;

    // GAME LOOP
    public Thread gameLoop;

    // GAME LOOP SETTINGS
    public static int FPS = 120;
    public static int UPS = 120;

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
