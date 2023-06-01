package main;

import creatures.Player;

import java.awt.*;

public class Game implements Runnable {
    private GameScreen gameWindow;
    private GamePanel gamePanel;
    public Thread gameLoop;
    private InventoryPanel inventoryPanel;
    private final int FPS = 120;
    private final int UPS = 120;

    private Player player;

    /**
     * Game constructor that creates GamePanel and GameScreen
     */
    public Game() {
        initEntities();
        inventoryPanel = new InventoryPanel();
        gamePanel = new GamePanel(this);
        gameWindow = new GameScreen(gamePanel, inventoryPanel);

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
        player.update();
    }

    public void render(Graphics g) {
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