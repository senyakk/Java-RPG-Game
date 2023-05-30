package main;

import javax.swing.*;

public class GameScreen {

    private JFrame frame;

    /**
     * Game screen constructor responsible for game window
     * @param gamePanel panel that hosts the game
     */
    public GameScreen(GamePanel gamePanel, InventoryPanel inventoryPanel) {

        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}