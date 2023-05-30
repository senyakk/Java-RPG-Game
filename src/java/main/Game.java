package java.main;

public class Game {

    private GameScreen gameWindow;
    private GamePanel gamePanel;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameScreen(gamePanel);
    }
}