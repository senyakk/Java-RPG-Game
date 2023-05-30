package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GameScreen {
    private JFrame frame;

    /**
     * Game screen constructor responsible for game window
     * @param gamePanel panel that hosts the game
     */
    public GameScreen(GamePanel gamePanel, InventoryPanel inventoryPanel) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setCards(gamePanel, inventoryPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void setCards(GamePanel gamePanel, InventoryPanel inventoryPanel){
        JPanel overlay = new JPanel();
        overlay.setLayout(new CardLayout());

        overlay.add(gamePanel, "GAMEPANEL");
        addListeners(gamePanel);

        overlay.add(inventoryPanel, "INVENTORYPANEL");
        addListeners(inventoryPanel);

        frame.getContentPane().add(overlay, BorderLayout.CENTER);
    }

    private void addListeners(Object listenerTarget){
        this.frame.addKeyListener(new KeyboardInputs(listenerTarget));

        MouseInputs mouseInputs = new MouseInputs(listenerTarget);
        this.frame.addMouseListener(mouseInputs);
        this.frame.addMouseMotionListener(mouseInputs);
    }
}