package main;

public class Game {

    private GameScreen gameWindow;
    private GamePanel gamePanel;

    /**
     * Game constructor that creates GamePanel and GameScreen
     */
    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameScreen(gamePanel);
        gamePanel.requestFocus();
    }
}