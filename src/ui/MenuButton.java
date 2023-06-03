package ui;


import gamestates.Gamestate;
import utilz.SaveLoad;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.Buttons.*;

public class MenuButton {

    private int posX, posY, row, index;
    private int offsetX = B_WIDTH / 2;
    private Gamestate state;
    private BufferedImage[] images;
    private boolean mousePressed, mouseOver;
    private Rectangle bounds;

    public MenuButton (int posX, int posY, int row, Gamestate state) {

        this.posX = posX;
        this.posY = posY;
        this.row = row;
        this.state = state;
        loadImages();
        initBounds();

    }

    private void initBounds() {
        bounds = new Rectangle(posX - offsetX, posY, B_WIDTH, B_HEIGHT);
    }

    private void loadImages() {
        images = new BufferedImage[3];
        BufferedImage temp = SaveLoad.GetSpriteImg(SaveLoad.MENU_BUTTONS);
        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, row * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT );
        }

    }

    public void draw(Graphics g) {
        g.drawImage(images[index], posX - offsetX, posY, B_WIDTH, B_HEIGHT, null);
    }

    public void update() {
        index = 0;
        if(mouseOver) {
            index = 1;
        }
        if(mousePressed) {
            index = 2;
        }
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setGameState() {
        Gamestate.state = state;
    }

    public void resetBool() {
        mouseOver = false;
        mousePressed = false;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}