package gamestates;

import buttonUi.Buttons.ClassButton;
import buttonUi.Buttons.MenuButton;
import buttonUi.GameButton;
import main.GameModel;
import utilities.Load;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilities.Constants.GameLanguage.DUTCH;
import static utilities.Constants.GameLanguage.ENGLISH;
import static utilities.Constants.PlayerConstants.*;
import static utilities.Constants.UI.MenuButtons.B_HEIGHT;
import static utilities.Constants.UI.MenuButtons.B_WIDTH;

/**
 * @author Arsenijs
 * Class that handles class selection state
 */
public class ClassSelection extends State {

    private BufferedImage backgroundImage;
    private ClassButton[] buttons = new ClassButton[3];
    private MenuButton returnButton;

    /**
     * State for class selection
     */
    public ClassSelection(GameModel gameModel) {
        super(gameModel);
        loadSprites();
        addButtons(gameModel.getLanguage());
    }

    private void loadSprites() {
        switch (gameModel.getLanguage()) {
            case ENGLISH -> {
                backgroundImage = Load.GetSpriteImg("UI/EnglishButtons/StartscreenSelectClass.png");
            }
            case DUTCH -> {
                // Dutch background here
                backgroundImage = Load.GetSpriteImg("UI/EnglishButtons/StartscreenSelectClass.png");
            }
        }
    }
    private void addButtons(int language) {
        buttons[0] = new ClassButton((int) (165 * GameModel.scale), (int) (170 * GameModel.scale),
                B_WIDTH, B_HEIGHT, WARRIOR, language);
        buttons[1] = new ClassButton((int) (320 * GameModel.scale), (int) (170 * GameModel.scale),
                B_WIDTH, B_HEIGHT, ARCHER, language);
        buttons[2] = new ClassButton((int) (475 * GameModel.scale), (int) (170 * GameModel.scale),
                B_WIDTH, B_HEIGHT, BARD, language);

        returnButton = new MenuButton(GameModel.screenWidth / 2, (int) (GameModel.screenHeight / 1.3),
                B_WIDTH, B_WIDTH, 3, Gamestate.MENU, language);
    }

    @Override
    public void update() {
        for(ClassButton button : buttons) {
            button.update(gameModel.getLanguage());
        }
        returnButton.update(gameModel.getLanguage());
    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(backgroundImage,0,0, GameModel.screenWidth, GameModel.screenHeight, null);
        for(ClassButton button : buttons) {
            button.draw(g);
        }
        returnButton.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(ClassButton button : buttons) {
            if (isInOBorder(e, button)) {
                button.setMousePressed(true);
                break;
            }
        }
        if (isInOBorder(e, returnButton))
            returnButton.setMousePressed(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(ClassButton button : buttons) {
            if (isInOBorder(e, button)) {
                if (button.isMousePressed()) {
                    gameModel.getPlayer().setClass(button.getGameClassClass());
                    gameModel.getPlayer().resetAll();
                    gameModel.setGameState(Gamestate.PLAYING);
                }
                break;
            }
        }
        if (isInOBorder(e, returnButton)) {
            if (returnButton.isMousePressed()) {
                gameModel.setGameState(Gamestate.MENU);
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for(ClassButton button : buttons)  {
            button.reset();
        }
        returnButton.reset();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (ClassButton button : buttons) {
            button.setMouseOver(false);
            if (isInOBorder(e, button))
                button.setMouseOver(true);
        }

            returnButton.setMouseOver(false);
            if (isInOBorder(e, returnButton))
                returnButton.setMouseOver(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private boolean isInOBorder(MouseEvent e, GameButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}