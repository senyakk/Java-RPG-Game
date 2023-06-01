package main;

public class Game implements Runnable {
    private GameScreen gameWindow;
    private GamePanel gamePanel;
    public Thread gameLoop;
    private InventoryPanel inventoryPanel;
    private final int FPS = 120;
    private final int UPS = 120;

    /**
     * Game constructor that creates GamePanel and GameScreen
     */
    public Game() {
        inventoryPanel = new InventoryPanel();
        gamePanel = new GamePanel();
        gameWindow = new GameScreen(gamePanel, inventoryPanel);

        // gamePanel.requestFocus();

        startGameLoop();
    }

    private void startGameLoop() {
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    public void update() {
        gamePanel.updateGame();
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
}