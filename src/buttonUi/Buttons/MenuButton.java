package buttonUi.Buttons;


import gamestates.Gamestate;
import buttonUi.GameButton;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilities.Constants.UI.MenuButtons.*;

public class MenuButton extends GameButton {

    private int posX, posY, row, index;
    private int offsetX = B_WIDTH / 2;
    private Gamestate state;
    private BufferedImage start, actStart, options, actOptions, quit, actQuit;
    private BufferedImage[] images;
    private boolean mousePressed, mouseOver;

    public MenuButton (int x, int y, int width, int height,  int row, Gamestate state) {

        super(x- (B_WIDTH / 2), y, width, height);
        this.posX = x;
        this.posY = y;
        this.row = row;
        this.state = state;
        loadImages();
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

    public void setState() {
        Gamestate.state = state;
    }

    public void resetBool() {
        mouseOver = false;
        mousePressed = false;
    }

    public Gamestate getState() {
        return state;
    }
}