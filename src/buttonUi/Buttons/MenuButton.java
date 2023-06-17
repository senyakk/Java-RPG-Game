package buttonUi.Buttons;

import gamestates.Gamestate;
import buttonUi.GameButton;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.GameLanguage.DUTCH;
import static utilities.Constants.GameLanguage.ENGLISH;
import static utilities.Constants.UI.MenuButtons.*;

public class MenuButton extends GameButton {

    private int posX, posY, row, index, language;
    private int offsetX = B_WIDTH / 2;
    private Gamestate state;
    private BufferedImage[][] buttonImages = new BufferedImage[4][2];

    public MenuButton (int x, int y, int width, int height,  int row, Gamestate state, int language) {

        super(x- (B_WIDTH / 2), y, width, height);
        this.posX = x;
        this.posY = y;
        this.row = row;
        this.state = state;
        this.language = language;
        loadImages();
    }



    private void loadImages() {

        // Images array for button, where first column is the non-activated state
        // and second column is activated state.
        // First row is MENU, second row is OPTIONS, third row is QUIT, fourth row is RETURN

        switch (this.language) {
            case ENGLISH -> {
                // Loading English Images
                buttonImages[0][0] = Load.GetSpriteImg("UI/English/MenuButtons/startButton.png");
                ;
                buttonImages[0][1] = Load.GetSpriteImg("UI/English/MenuButtons/ActivatedstartButton.png");
                buttonImages[1][0] = Load.GetSpriteImg("UI/English/MenuButtons/OptionsButton.png");
                ;
                buttonImages[1][1] = Load.GetSpriteImg("UI/English/MenuButtons/ActivatedOptionsButton.png");
                buttonImages[2][0] = Load.GetSpriteImg("UI/English/MenuButtons/QuitButton.png");
                buttonImages[2][1] = Load.GetSpriteImg("UI/English/MenuButtons/activatedQuitButton.png");
                buttonImages[3][0] = Load.GetSpriteImg("UI/English/MenuButtons/returnButton.png");
                buttonImages[3][1] = Load.GetSpriteImg("UI/English/MenuButtons/ReturnActivatedButton.png");
            }
            case DUTCH -> {
                // Loading Dutch Images
                buttonImages[0][0] = Load.GetSpriteImg("UI/English/MenuButtons/startButton.png");
                buttonImages[0][1] = Load.GetSpriteImg("UI/English/MenuButtons/ActivatedstartButton.png");
                buttonImages[1][0] = Load.GetSpriteImg("UI/Dutch/MenuButtons/optiesButton.png");
                buttonImages[1][1] = Load.GetSpriteImg("UI/Dutch/MenuButtons/optiesActivatedButton.png");
                buttonImages[2][0] = Load.GetSpriteImg("UI/Dutch/MenuButtons/stopButton.png");
                buttonImages[2][1] = Load.GetSpriteImg("UI/Dutch/MenuButtons/stopActivatedButton.png");
                buttonImages[3][0] = Load.GetSpriteImg("UI/Dutch/MenuButtons/stopButton.png");
                buttonImages[3][1] = Load.GetSpriteImg("UI/Dutch/MenuButtons/stopActivatedButton.png");
            }
        }
    }

    public void draw(Graphics g) {
        g.drawImage(buttonImages[row][index], posX - offsetX, posY, B_WIDTH, B_HEIGHT, null);
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

    public Gamestate getState() {
        return state;
    }

}