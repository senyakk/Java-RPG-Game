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

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(gamePanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}