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

    public Menu(GameModel gameModel) {
        super(gameModel);
        loadSprites();
        addButtons(gameModel.getLanguage());
    }

    private void loadSprites() {
        backgroundImage = Load.GetSpriteImg("UI/English/Startscreen.png");
    }

    private void addButtons(int language) {

        buttons[0] = new MenuButton((int) (165 * GameModel.scale), (int) (170 * GameModel.scale),
                B_WIDTH, B_HEIGHT, 0, Gamestate.CLASS_SELECTION, language);
        buttons[1] = new MenuButton((int) (320 * GameModel.scale), (int) (170 * GameModel.scale),
                B_WIDTH, B_HEIGHT, 1, Gamestate.OPTIONS, language);
        buttons[2] = new MenuButton((int) (475 * GameModel.scale), (int) (170 * GameModel.scale),
                B_WIDTH, B_HEIGHT, 2, Gamestate.QUIT, language);
    }

    private boolean isInOBorder(MouseEvent e, GameButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }


    @Override
    public void update() {
        for(MenuButton button : buttons) {
            button.update(gameModel.getLanguage());
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

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

}