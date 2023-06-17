package gamestates;

import buttonUi.GameButton;
import buttonUi.Buttons.MenuButton;
import main.GameModel;
import utilities.Load;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilities.Constants.GameLanguage.*;
import static utilities.Constants.UI.MenuButtons.*;



/**
 * @author Arsenijs
 * Class that handles menu state
 */
public class Menu extends State {
    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backgroundImage;
    private BufferedImage[] buttonImages;
    private BufferedImage start, actStart, options, actOptions, quit, actQuit, returnB, actReturnB;

    public Menu(GameModel gameModel) {
        super(gameModel);
        loadSprites();
    }

    private void loadSprites() {

        switch(gameModel.getLanguage()) {
            case ENGLISH -> {
                backgroundImage = Load.GetSpriteImg("UI/English/Startscreen.png");
            }
            case DUTCH -> {
                // Dutch start screen here
                backgroundImage = Load.GetSpriteImg("UI/English/Startscreen.png");
            }
        }

        private void loadImages() {
            buttonImages = new BufferedImage[2];
            switch (row) {
                case 0 -> {
                    start = Load.GetSpriteImg("UI/English/MenuButtons/startButton.png");
                    buttonImages[0] = start;
                    actStart = Load.GetSpriteImg("UI/English/MenuButtons/ActivatedstartButton.png");
                    buttonImages[1] = actStart;
                }
                case 1 -> { // options
                    switch(gameModel.getLanguage()) {
                        case ENGLISH -> {
                            options = Load.GetSpriteImg("UI/English/MenuButtons/OptionsButton.png");
                            buttonImages[0] = options;
                            actOptions = Load.GetSpriteImg("UI/English/MenuButtons/ActivatedOptionsButton.png");
                            buttonImages[1] = actOptions;
                        }
                        case DUTCH -> {
                            options = Load.GetSpriteImg("UI/Dutch/Options/ClassButtonsDutch/MenuButtons/optiesButton.png");
                            buttonImages[0] = options;
                            actOptions = Load.GetSpriteImg("UI/Dutch/Options/ClassButtonsDutch/MenuButtons/optiesActivatedButton.png");
                            buttonImages[1] = actOptions;
                        }
                    }
                }
                case 2 -> {
                    quit = Load.GetSpriteImg("UI/English/MenuButtons/QuitButton.png");
                    buttonImages[0] = quit;
                    actQuit = Load.GetSpriteImg("UI/English/MenuButtons/activatedQuitButton.png");
                    buttonImages[1] = actQuit;
                }
                case 3 -> {
                    returnB = Load.GetSpriteImg("UI/English/MenuButtons/returnButton.png");
                    buttonImages[0] = returnB;
                    actReturnB = Load.GetSpriteImg("UI/English/MenuButtons/ReturnActivatedButton.png");
                    buttonImages[1] = actReturnB;
                }
            }
        }
        buttons[0] = new MenuButton((int) (165 * GameModel.scale), (int) (170 * GameModel.scale),
                B_WIDTH, B_HEIGHT, 0, Gamestate.CLASS_SELECTION);
        buttons[1] = new MenuButton((int) (320 * GameModel.scale), (int) (170 * GameModel.scale),
                B_WIDTH, B_HEIGHT, 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton((int) (475 * GameModel.scale), (int) (170 * GameModel.scale),
                B_WIDTH, B_HEIGHT, 2, Gamestate.QUIT);
    }


    @Override
    public void update() {
        for(MenuButton button : buttons) {
            button.update();
        }
    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(backgroundImage,0,0, GameModel.screenWidth, GameModel.screenHeight, null);
        for(MenuButton button : buttons) {
           button.draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButton button : buttons) {
            if (isInOBorder(e, button)) {
                button.setMousePressed(true);
                break;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton button : buttons) {
            if (isInOBorder(e, button)) {
                if (button.isMousePressed()) {
                    gameModel.setGameState(button.getState());
                }
                break;
            }
        }
        resetButtons();

    }

    private void resetButtons() {
        for(MenuButton button : buttons)  {
            button.reset();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButton button : buttons)  {
            button.setMouseOver(false);
        }

        for (MenuButton button : buttons) {
            if (isInOBorder(e, button)) {
                button.setMouseOver(true);
                break;
            }
        }
    }
    private boolean isInOBorder(MouseEvent e, GameButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

}