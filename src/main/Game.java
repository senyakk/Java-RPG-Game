package main;

public class Game {

    // SCREEN SETTINGS
    public final static int defaultTileSize = 64;
    public final static float scale = 2.5f;
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

    // GAME LOOP
    public Thread gameLoop;

    // GAME LOOP SETTINGS
    public final static int FPS = 120;
    public final static int UPS = 120;

    /**
     * Game constructor that creates GamePanel, GameController, and GameWindow and starts the game loop
     */
    public Game() {
        gamePanel = new GamePanel();
        gameController = new GameController(gamePanel);
        gamePanel.setGameController(gameController);
        gameWindow = new GameScreen(gamePanel);

        gamePanel.requestFocus();
        startGameLoop();
    }


    private void startGameLoop() {
        gameLoop = new Thread(gameController);
        gameLoop.start();
    }
}