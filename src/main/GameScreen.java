package main;
import javax.swing.*;

public class GameScreen {
    private JFrame frame;
    /**
     * Game screen constructor responsible for game window
     * @param gamePanel panel that hosts the game
     */
    public GameScreen(GamePanel gamePanel) {
        frame = new JFrame();
        frame.setTitle("Dragonbrew: The Alchemist's Quest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}