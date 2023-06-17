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

    private int posX, posY, row, index;
    private int offsetX = B_WIDTH / 2;
    private Gamestate state;
    private BufferedImage start, actStart, options, actOptions, quit, actQuit, returnB, actReturnB;
    private BufferedImage[] images;

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
                start = Load.GetSpriteImg("UI/English/MenuButtons/startButton.png");
                images[0] = start;
                actStart = Load.GetSpriteImg("UI/English/MenuButtons/ActivatedstartButton.png");
                images[1] = actStart;
             }
            case 1 -> { // options
                switch(gameModel.getLanguage()) {
                    case ENGLISH -> {
                        options = Load.GetSpriteImg("UI/English/MenuButtons/OptionsButton.png");
                        images[0] = options;
                        actOptions = Load.GetSpriteImg("UI/English/MenuButtons/ActivatedOptionsButton.png");
                        images[1] = actOptions;
                    }
                    case DUTCH -> {
                        options = Load.GetSpriteImg("UI/Dutch/Options/ClassButtonsDutch/MenuButtons/optiesButton.png");
                        images[0] = options;
                        actOptions = Load.GetSpriteImg("UI/Dutch/Options/ClassButtonsDutch/MenuButtons/optiesActivatedButton.png");
                        images[1] = actOptions;
                    }
                }
            }
            case 2 -> {
                quit = Load.GetSpriteImg("UI/English/MenuButtons/QuitButton.png");
                images[0] = quit;
                actQuit = Load.GetSpriteImg("UI/English/MenuButtons/activatedQuitButton.png");
                images[1] = actQuit;
            }
            case 3 -> {
                returnB = Load.GetSpriteImg("UI/English/MenuButtons/returnButton.png");
                images[0] = returnB;
                actReturnB = Load.GetSpriteImg("UI/English/MenuButtons/ReturnActivatedButton.png");
                images[1] = actReturnB;
            }
         }
    }

    public void draw(Graphics g) {
        g.drawImage(images[index], posX - offsetX, posY, B_WIDTH, B_HEIGHT, null);
    }

    public void update() {
        index = 0;
        if(isMouseOver) {
            index = 1;
        }
    }

    public Gamestate getState() {
        return state;
    }
}