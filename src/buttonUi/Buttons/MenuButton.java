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
    private BufferedImage[][] EngImages = new BufferedImage[4][2],
            DutchImages = new BufferedImage[4][2], currentImages = new BufferedImage[4][2];

    public MenuButton (int x, int y, int width, int height,  int row, Gamestate state, int language) {

        super(x- (B_WIDTH / 2), y, width, height);
        this.posX = x;
        this.posY = y;
        this.row = row;
        this.state = state;
        this.language = language;
        loadImages();

        switch (language) {
            case ENGLISH -> currentImages = EngImages;
            case DUTCH -> currentImages = DutchImages;
        }
    }



    private void loadImages() {

        // Images array for button, where first column is the non-activated state
        // and second column is activated state.
        // First row is MENU, second row is OPTIONS, third row is QUIT, fourth row is RETURN

        // Loading English Images
        EngImages[0][0] = Load.GetSpriteImg("UI/English/MenuButtons/startButton.png");;
        EngImages[0][1] = Load.GetSpriteImg("UI/English/MenuButtons/ActivatedstartButton.png");
        EngImages[1][0] = Load.GetSpriteImg("UI/English/MenuButtons/OptionsButton.png");;
        EngImages[1][1] = Load.GetSpriteImg("UI/English/MenuButtons/ActivatedOptionsButton.png");
        EngImages[2][0] = Load.GetSpriteImg("UI/English/MenuButtons/QuitButton.png");
        EngImages[2][1] = Load.GetSpriteImg("UI/English/MenuButtons/activatedQuitButton.png");
        EngImages[3][0] = Load.GetSpriteImg("UI/English/MenuButtons/returnButton.png");
        EngImages[3][1] = Load.GetSpriteImg("UI/English/MenuButtons/ReturnActivatedButton.png");

        // Loading Dutch Images
        DutchImages[0][0] = EngImages[0][0];
        DutchImages[0][1] = EngImages[0][1];
        DutchImages[1][0] = Load.GetSpriteImg("UI/Dutch/MenuButtons/optiesButton.png");
        DutchImages[1][1] = Load.GetSpriteImg("UI/Dutch/MenuButtons/optiesActivatedButton.png");
        DutchImages[2][0] = Load.GetSpriteImg("UI/Dutch/MenuButtons/stopButton.png");
        DutchImages[2][1] = Load.GetSpriteImg("UI/Dutch/MenuButtons/stopActivatedButton.png");
        DutchImages[3][0] = Load.GetSpriteImg("UI/Dutch/MenuButtons/stopButton.png");
        DutchImages[3][1] = Load.GetSpriteImg("UI/Dutch/MenuButtons/stopActivatedButton.png");
    }

    public void draw(Graphics g) {
        g.drawImage(currentImages[row][index], posX - offsetX, posY, B_WIDTH, B_HEIGHT, null);
    }

    public void update(int language) {

        index = 0;
        if(isMouseOver) {
            index = 1;
        }

        if (language != this.language) {
            switch (language) {
                case DUTCH -> currentImages = DutchImages;
                case ENGLISH -> currentImages = EngImages;
            }
            this.language = language;
        }
    }

    public Gamestate getState() {
        return state;
    }

}