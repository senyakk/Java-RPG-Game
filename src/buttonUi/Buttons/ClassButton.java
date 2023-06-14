package buttonUi.Buttons;

import buttonUi.GameButton;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.PlayerConstants.*;
import static utilities.Constants.UI.MenuButtons.B_HEIGHT;
import static utilities.Constants.UI.MenuButtons.B_WIDTH;



public class ClassButton extends GameButton {

    private int posX, posY, index;
    private int offsetX = B_WIDTH / 2;
    private BufferedImage warrior, warAct, archer, archAct, bard, bardAct;
    private BufferedImage[] images;
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
                warrior = Load.GetSpriteImg("UI/English/ClassButtons/WarriorButton.png");
                images[0] = warrior;
                warAct = Load.GetSpriteImg("UI/English/ClassButtons/WarriorActivatedButton.png");
                images[1] = warAct;
            }
            case ARCHER -> {
                archer = Load.GetSpriteImg("UI/English/ClassButtons/ArcherButton.png");
                images[0] = archer;
                archAct = Load.GetSpriteImg("UI/English/ClassButtons/ArcherActivatedButton.png");
                images[1] = archAct;
            }
            case BARD -> {
                bard = Load.GetSpriteImg("UI/English/ClassButtons/BardButton.png");
                images[0] = bard;
                bardAct = Load.GetSpriteImg("UI/English/ClassButtons/BardActivatedButton.png");
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
        if(isMouseOver) {
            index = 1;
        }
    }

}
