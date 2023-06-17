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
    private BufferedImage[][] buttonImages = new BufferedImage[3][2];
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

        // Images array for button, where first column is the non-activated state
        // and second column is activated state.
        // First row is WARRIOR, second row is ARCHER, third row is BARD

        switch (this.language) {
            case ENGLISH -> {
                // Loading English images
                buttonImages[0][0] = Load.GetSpriteImg("UI/English/ClassButtons/WarriorButton.png");
                buttonImages[0][1] = Load.GetSpriteImg("UI/English/ClassButtons/WarriorActivatedButton.png");
                buttonImages[1][0] = Load.GetSpriteImg("UI/English/ClassButtons/ArcherButton.png");
                buttonImages[1][1] = Load.GetSpriteImg("UI/English/ClassButtons/ArcherActivatedButton.png");
                buttonImages[2][0] = Load.GetSpriteImg("UI/English/ClassButtons/BardButton.png");
                buttonImages[2][1] = Load.GetSpriteImg("UI/English/ClassButtons/BardActivatedButton.png");
            }

            case DUTCH -> {
                // Loading Dutch images
                buttonImages[0][0] = Load.GetSpriteImg("UI/Dutch/ClassButtons/krijgerButton.png");
                buttonImages[0][1] = Load.GetSpriteImg("UI/Dutch/ClassButtons/krijgerAtivatedButton.png");
                buttonImages[1][0] = Load.GetSpriteImg("UI/Dutch/ClassButtons/boogschutterButton.png");
                buttonImages[1][1] = Load.GetSpriteImg("UI/Dutch/ClassButtons/boogschutterActivatedButton.png");
                buttonImages[2][0] = Load.GetSpriteImg("UI/Dutch/ClassButtons/dichterButton.png");
                buttonImages[2][1] = Load.GetSpriteImg("UI/Dutch/ClassButtons/dichterActivatedButton.png");
            }
        }
    }

    public void draw(Graphics g) {
        g.drawImage(buttonImages[gameClass][index], posX - offsetX, posY, B_WIDTH, B_HEIGHT, null);
    }


    public void update(int language) {
        index = 0;
        if(isMouseOver) {
            index = 1;
        }

        if (language != this.language) {
            this.language = language;
            loadImages();
        }
    }

    public int getGameClassClass() {
        return gameClass;
    }

}
