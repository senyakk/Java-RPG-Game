package buttonUi.Buttons;

import buttonUi.GameButton;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.GameLanguage.DUTCH;
import static utilities.Constants.GameLanguage.ENGLISH;
import static utilities.Constants.UI.MenuButtons.B_HEIGHT;
import static utilities.Constants.UI.MenuButtons.B_WIDTH;



public class ClassButton extends GameButton {

    private int posX, posY, index, language;
    private int offsetX = B_WIDTH / 2;
    private BufferedImage warrior, warAct, archer, archAct, bard, bardAct;
    private BufferedImage[][] EngImages = new BufferedImage[3][2],
            DutchImages = new BufferedImage[3][2], currentImages = new BufferedImage[3][2];
    private int gameClass;

    public ClassButton(int x, int y, int width, int height, int gameClass, int language) {
        super(x- (B_WIDTH / 2), y, width, height);
        this.posX = x;
        this.posY = y;
        this.gameClass = gameClass;
        this.language = language;
        loadImages();
    }

    private void loadImages() {
        switch(language) {
            case ENGLISH -> {
                warrior = Load.GetSpriteImg("UI/EnglishButtons/ClassButtons/WarriorButton.png");
                EngImages[0][0] = warrior;
                warAct = Load.GetSpriteImg("UI/EnglishButtons/ClassButtons/WarriorActivatedButton.png");
                EngImages[0][1] = warAct;
                archer = Load.GetSpriteImg("UI/EnglishButtons/ClassButtons/ArcherButton.png");
                EngImages[1][0] = archer;
                archAct = Load.GetSpriteImg("UI/EnglishButtons/ClassButtons/ArcherActivatedButton.png");
                EngImages[1][1] = archAct;
                bard = Load.GetSpriteImg("UI/EnglishButtons/ClassButtons/BardButton.png");
                EngImages[2][0] = bard;
                bardAct = Load.GetSpriteImg("UI/EnglishButtons/ClassButtons/BardActivatedButton.png");
                EngImages[2][1] = bardAct;
            }
            case DUTCH -> {
                warrior = Load.GetSpriteImg("UI/Dutch/Options/DutchButtons/classButtons/krijgerButton.png");
                EngImages[0][0] = warrior;
                warAct = Load.GetSpriteImg("UI/Dutch/Options/DutchButtons/classButtons/krijgerAtivatedButton.png");
                EngImages[0][1] = warAct;
                archer = Load.GetSpriteImg("UI/Dutch/Options/DutchButtons/classButtons/boogschutterButton.png");
                EngImages[1][0] = archer;
                archAct = Load.GetSpriteImg("UI/Dutch/Options/DutchButtons/classButtons/boogschutterActivatedButton.png");
                EngImages[1][1] = archAct;
                bard = Load.GetSpriteImg("UI/Dutch/Options/DutchButtons/classButtons/dichterButton.png");
                EngImages[2][0] = bard;
                bardAct = Load.GetSpriteImg("UI/Dutch/Options/DutchButtons/classButtons/dichterActivatedButton.png");
                EngImages[2][1] = bardAct;
            }
        }
    }

    public int getGameClassClass() {
        return gameClass;
    }

    public void draw(Graphics g) {
        gameClass = getGameClassClass();
        g.drawImage(currentImages[gameClass][index], posX - offsetX, posY, B_WIDTH, B_HEIGHT, null);
    }


    public void update(int language) {
        switch (language) {
            case DUTCH -> {
                currentImages = DutchImages;
            }
            case ENGLISH -> {
                currentImages = EngImages;
            }
        }
        index = 0;
        if(isMouseOver) {
            index = 1;
        }
    }

}
