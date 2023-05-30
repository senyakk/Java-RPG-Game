package java.main;

import javax.swing.*;

public class GameScreen {

    private JFrame frame;

    public GameScreen(GamePanel gamePanel) {

        frame = new JFrame();

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(gamePanel);
        frame.setVisible(true);

    }
}