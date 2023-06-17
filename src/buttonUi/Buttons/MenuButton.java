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
    private BufferedImage start, actStart, options, actOptions, quit, actQuit, returnB, actReturnB;
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
    }



    private void loadImages() {
        start = Load.GetSpriteImg("UI/English/MenuButtons/startButton.png");
        EngImages[0][0] = start;
         DutchImages[0][0] = start;
        actStart = Load.GetSpriteImg("UI/English/MenuButtons/ActivatedstartButton.png");
        EngImages[0][1] = actStart;
         DutchImages[0][1] = actStart;
        switch(language) {
            case ENGLISH -> {
                options = Load.GetSpriteImg("UI/English/MenuButtons/OptionsButton.png");
                EngImages[1][0] = options;
                actOptions = Load.GetSpriteImg("UI/English/MenuButtons/ActivatedOptionsButton.png");
                EngImages[1][1] = actOptions;
                quit = Load.GetSpriteImg("UI/English/MenuButtons/QuitButton.png");
                EngImages[2][0] = quit;
                actQuit = Load.GetSpriteImg("UI/English/MenuButtons/activatedQuitButton.png");
                EngImages[2][1] = actQuit;
                returnB = Load.GetSpriteImg("UI/English/MenuButtons/returnButton.png");
                EngImages[3][0] = returnB;
                actReturnB = Load.GetSpriteImg("UI/English/MenuButtons/ReturnActivatedButton.png");
                EngImages[3][1] = actReturnB;
            }
            case DUTCH -> {
                options = Load.GetSpriteImg("UI/Dutch/Options/ClassButtonsDutch/MenuButtons/optiesButton.png");
                DutchImages[1][0] = options;
                actOptions = Load.GetSpriteImg("UI/Dutch/Options/ClassButtonsDutch/MenuButtons/optiesActivatedButton.png");
                DutchImages[1][1] = actOptions;
                quit = Load.GetSpriteImg("UI/Dutch/Options/ClassButtonsDutch/MenuButtons/stopButton.png");
                DutchImages[2][0] = quit;
                actQuit = Load.GetSpriteImg("UI/Dutch/Options/ClassButtonsDutch/MenuButtons/stopActivatedButton.png");
                DutchImages[2][1] = actQuit;
                quit = Load.GetSpriteImg("UI/Dutch/Options/ClassButtonsDutch/MenuButtons/stopButton.png");
                DutchImages[3][0] = quit;
                actQuit = Load.GetSpriteImg("UI/Dutch/Options/ClassButtonsDutch/MenuButtons/stopActivatedButton.png");
                DutchImages[3][1] = actQuit;
            }
        }
    }

    public void draw(Graphics g) {
        g.drawImage(currentImages[row][index], posX - offsetX, posY, B_WIDTH, B_HEIGHT, null);
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

    public Gamestate getState() {
        return state;
    }
}