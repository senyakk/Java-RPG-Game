package buttonUi;

import java.awt.*;

/**
 * Parent button of all buttons
 */
public class GameButton {

    protected int x, y, width, height;
    protected Rectangle bounds;
    protected String text;
    protected boolean isMouseOver, isMousePressed;

    /**
     * Parent class for the buttons in game
     * @param x button x position on the screen
     * @param y button y position on the screen
     * @param width buttons' width
     * @param height buttons' height
     */
    public GameButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        createBounds();
    }
    private void createBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * Default parent method draw for drawing a button with text
     * @param g Graphics object
     */
    public void draw(Graphics g) {
        // Draw button background
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);


        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        FontMetrics fontMetrics = g.getFontMetrics();
        int textX = x + (width - fontMetrics.stringWidth(text)) / 2;
        int textY = y + (height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        g.drawString(text, textX, textY);
    }

    public boolean isMousePressed() {
        return isMousePressed;
    }

    public boolean isMouseOver() {
        return isMouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        isMouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        isMousePressed = mousePressed;
    }

    public void reset() {
        isMouseOver = false;
        isMousePressed = false;
    }
}