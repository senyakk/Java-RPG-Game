package buttonUi.Buttons;

import buttonUi.GameButton;
import gamestates.Gamestate;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.PlayerConstants.*;
import static utilities.Constants.UI.MenuButtons.B_HEIGHT;
import static utilities.Constants.UI.MenuButtons.B_WIDTH;



public class ClassButton extends GameButton {

    private int posX, posY, row, index;
    private int offsetX = B_WIDTH / 2;
    private Gamestate state;
    private BufferedImage warrior, warAct, archer, archAct, bard, bardAct, returnB, actReturnB;
    private BufferedImage[] images;
    private boolean mousePressed, mouseOver;
    private int gameClass;

    public ClassButton(int x, int y, int width, int height, int gameClass) {
        super(x- (B_WIDTH / 2), y, width, height);
        this.posX = x;
        this.posY = y;
        this.gameClass = gameClass;
        loadImages();
    }

    private void loadImages() {
        images = new BufferedImage[2];
        switch (gameClass) {
            case WARRIOR -> {
                warrior = Load.GetSpriteImg("UI/MenuButtons/WarriorButton.png");
                images[0] = warrior;
                warAct = Load.GetSpriteImg("UI/MenuButtons/WarriorActivatedButton.png");
                images[1] = warAct;
            }
            case ARCHER -> {
                archer = Load.GetSpriteImg("UI/MenuButtons/ArcherButton.png");
                images[0] = archer;
                archAct = Load.GetSpriteImg("UI/MenuButtons/ArcherActivatedButton.png");
                images[1] = archAct;
            }
            case BARD -> {
                bard = Load.GetSpriteImg("UI/MenuButtons/BardButton.png");
                images[0] = bard;
                bardAct = Load.GetSpriteImg("UI/MenuButtons/BardActivatedButton.png");
                images[1] = bardAct;
            }
        }
    }

    public void draw(Graphics g) {
        g.drawImage(images[index], posX - offsetX, posY, B_WIDTH, B_HEIGHT, null);
    }


    public int getGameClassClass() {
        return gameClass;
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
