package main;

import javax.swing.*;
import java.awt.*;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private int xDelta = 100, yDelta = 100;

    /**
     * GamePanel constructor adds mouse and keyboard listeners to the game
     */
    public GamePanel() {

        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    /**
     * Method for changing the horizontal position of the player
     * @param value the value to move
     */
    public void changeXDelta(int value) {
        this.xDelta += value;
        repaint();
    }

    /**
     * Method for changing the vertical position of the player
     * @param value the value to move
     */
    public void changeYDelta(int value) {
        this.yDelta += value;
        repaint();
    }

    /**
     * Method for manual setting the position of the player
     * @param x the horizontal position
     * @param y the vertical position
     */
    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(xDelta, yDelta, 200, 50);
    }
}
