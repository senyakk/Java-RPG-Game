package main;

import creatures.Player;
import tile.TileManager;

import java.awt.*;

public class Game implements Runnable {

    // SCREEN SETTINGS
    public final static int defaultTileSize = 64;
    public final static float scale = 2;
    public static final int tileSize = (int)(defaultTileSize * scale);
    public static final int maxTileCol = 8;
    public static final int maxTileRow = 6;
    public final static int screenWidth = tileSize * maxTileCol;
    public final static int screenHeight = tileSize * maxTileRow;

    // GAME STATE
    public int gameState;
    public final int playing = 1;
    public final int pause = 2;

    // WINDOW SETTINGS

    private GameScreen gameWindow;
    private GamePanel gamePanel;
    public Thread gameLoop;
    private InventoryPanel inventoryPanel;
    private final int FPS = 120;
    private final int UPS = 120;

    private Player player;
    private TileManager tileManager = new TileManager(this);

    /**
     * Game constructor that creates GamePanel and GameScreen
     */
    public Game() {
        initEntities();
        inventoryPanel = new InventoryPanel();
        gamePanel = new GamePanel(this);
        gameWindow = new GameScreen(gamePanel, inventoryPanel);
        gameState = playing;

        // gamePanel.requestFocus();

        startGameLoop();

    }

    private void initEntities() {
        player = new Player(200, 200);
    }

    private void startGameLoop() {
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    public void update() {
        if (gameState == playing) {
            player.update();
        }
        if (gameState == pause) {
            // nothing
        }
    }

    public void render(Graphics g) {
        tileManager.draw(g);
        player.render(g);
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (gameLoop != null) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirections();
    }
}