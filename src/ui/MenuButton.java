package ui;


import gamestates.Gamestate;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilities.Constants.UI.MenuButtons.*;

public class MenuButton {

    private int posX, posY, row, index;
    private int offsetX = B_WIDTH / 2;
    private Gamestate state;
    private BufferedImage start, actStart, options, actOptions, quit, actQuit;
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
        images = new BufferedImage[2];
         switch (row) {
             case 0 -> {
             start = Load.GetSpriteImg("UI/MenuButtons/startButton.png");
             images[0] = start;
             actStart = Load.GetSpriteImg("UI/MenuButtons/ActivatedstartButton.png");
             images[1] = actStart;
             }
            case 1 -> {
                options = Load.GetSpriteImg("UI/MenuButtons/OptionsButton.png");
                images[0] = options;
                actOptions = Load.GetSpriteImg("UI/MenuButtons/ActivatedOptionsButton.png");
                images[1] = actOptions;
            }
            case 2 -> {
                quit = Load.GetSpriteImg("UI/MenuButtons/QuitButton.png");
                images[0] = quit;
                actQuit = Load.GetSpriteImg("UI/MenuButtons/activatedQuitButton.png");
                images[1] = actQuit;
            }
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
            //index = 2;
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

    public Gamestate getState() {
        return state;
    }
}