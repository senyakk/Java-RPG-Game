package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private float xDir = 1f, yDir = 1f;
    private Color color = new Color(144, 67, 45);
    private Random random;

    /**
     * GamePanel constructor adds mouse and keyboard listeners to the game
     */
    public GamePanel() {

        random = new Random();
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
    }

    /**
     * Method for changing the vertical position of the player
     * @param value the value to move
     */
    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    /**
     * Method for manual setting the position of the player
     * @param x the horizontal position
     * @param y the vertical position
     */
    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //updateRectangle();

        g.setColor(color);
        g.fillRect((int)xDelta, (int)yDelta, 200, 50);
    }

    private void updateRectangle() {
        xDelta += xDir;
        if (xDelta > 400 || xDelta < 0) {
            xDir *= -1;
            color = getRndColor();
        }
        yDelta += yDir;
        if (yDelta > 400 || yDelta < 0) {
            yDir *= -1;
            color = getRndColor();
        }
    }

    private Color getRndColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r, b, g);
    }

}
