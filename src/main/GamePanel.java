package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {

    // SCREEN SETTINGS
    final int originalTileSize = 64;
    final int scale = 2;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 8;
    final int maxScreenRow = 6;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    private boolean isVisible;

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage image, subImg;

    /**
     * GamePanel constructor adds mouse and keyboard listeners to the game
     */
    public GamePanel() {

        importImage();
        setPanelSize();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        this.isVisible = true;
        this.setVisible(true);
    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.jpeg");

        try {
            image = ImageIO.read(is);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(screenWidth, screenHeight);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        setBackground(new Color(100, 200, 90));
        setDoubleBuffered(true);
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

        subImg = image.getSubimage(0,0, 64, 72);
        g.drawImage(subImg, (int)xDelta, (int)yDelta, 128, 144, null);

    }

    public void switchVisibility(){
        this.isVisible = !isVisible;
        this.setVisible(isVisible);
        System.out.println("Switched game panel to ".concat(Boolean.toString(isVisible)));
    }
}
