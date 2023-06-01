package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Direction.*;

public class GamePanel extends JPanel {
    // SCREEN SETTINGS
    final int originalTileSize = 64;
    final int scale = 2;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 8;
    final int maxScreenRow = 6;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    // SPRITES SETTINGS
    private float xDelta = 200, yDelta = 200;
    private BufferedImage image;
    private BufferedImage[][] animations;
    private int animTick, animIndex, animSpeed = 30;
    private int playerAction;
    private int playerDirection = -1;
    private boolean moving = false;

    // INVENTORY SETTINGS
    private boolean isVisible;

    /**
     * GamePanel constructor adds mouse and keyboard listeners to the game
     */
    public GamePanel() {
        importImage();
        loadAnimations();

        setPanelSize();

        setBackground(new Color(100, 200, 90));
        setDoubleBuffered(true);

        this.isVisible = true;
        this.setVisible(true);
    }

    private void loadAnimations() {
        animations = new BufferedImage[4][4];

        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = image.getSubimage(i * 64, j * 64, 64, 64);
            }
        }
    }

    private void importImage() {
        InputStream playerSprites = getClass().getResourceAsStream("/player_sprites.jpeg");

        try {
            image = ImageIO.read(playerSprites);
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                playerSprites.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets GamePanel size
     */
    private void setPanelSize() {
        Dimension size = new Dimension(screenWidth, screenHeight);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setDirection(int direction) {
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving; 
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(animations[playerAction][animIndex], (int)xDelta, (int)yDelta, 128, 144, null);
    }

    private void updatePos() {
        if (moving) {
            switch (playerDirection) {
                case LEFT -> xDelta -= 5;
                case UP -> yDelta -= 5;
                case RIGHT -> xDelta += 5;
                case DOWN -> yDelta += 5;
            }
        }
    }

    private void setAnimation() {
        if (moving) {
            switch (playerDirection) {
                case LEFT -> playerAction = WALKING_LEFT;
                case UP -> playerAction = WALKING_UP;
                case RIGHT -> playerAction = WALKING_RIGHT;
                case DOWN -> playerAction = WALKING_DOWN;
            }
        }
    }

    private void updateAnimation() {
        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            int spriteAmount = moving ? getSpriteAmount(playerAction) : 0;
            if (animIndex >= spriteAmount) {
                animIndex = 0;
            }
        }
    }

    public void updateGame() {
        updateAnimation();
        setAnimation();
        updatePos();
    }

    public void switchVisibility(){
        this.isVisible = !isVisible;
        this.setVisible(isVisible);
        System.out.println("Switched game panel to ".concat(Boolean.toString(isVisible)));
    }
}
