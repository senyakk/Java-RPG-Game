package gamestates;

import buttonUi.Buttons.ClassButton;
import buttonUi.Buttons.MenuButton;
import buttonUi.GameButton;
import main.Game;
import utilities.Load;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilities.Constants.PlayerConstants.*;
import static utilities.Constants.UI.MenuButtons.B_HEIGHT;
import static utilities.Constants.UI.MenuButtons.B_WIDTH;


public class ClassSelection extends State implements Statemethods {

    private BufferedImage backgroundImage;
    private ClassButton[] buttons = new ClassButton[3];
    private MenuButton returnButton;

    /**
     * State for class selection
     * @param game
     */
    public ClassSelection(Game game) {
        super(game);
        backgroundImage = Load.GetSpriteImg("UI/StartscreenSelectClass.png");
        loadButtons();
    }

    private void loadButtons() {

        buttons[0] = new ClassButton((int) (165 * Game.scale), (int)(170 * Game.scale),
                B_WIDTH, B_HEIGHT, WARRIOR);
        buttons[1] = new ClassButton((int)(320 * Game.scale), (int)(170 * Game.scale),
                B_WIDTH, B_HEIGHT, ARCHER);
        buttons[2] = new ClassButton((int) (475 * Game.scale), (int)(170 * Game.scale),
                B_WIDTH, B_HEIGHT, BARD);

        returnButton = new MenuButton(Game.screenWidth/2, (int) (Game.screenHeight/1.3),
                B_WIDTH, B_WIDTH, 3, Gamestate.MENU);

    }

    @Override
    public void update() {
        for(ClassButton button : buttons) {
            button.update();
        }
        returnButton.update();
    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(backgroundImage,0,0, Game.screenWidth, Game.screenHeight, null);
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
                    game.getPlaying().getPlayer().setClass(button.getGameClassClass());
                    game.getPlaying().getPlayer().resetAll();
                    setGameState(Gamestate.PLAYING);
                }
                break;
            }
        }
        if (isInOBorder(e, returnButton)) {
            if (returnButton.isMousePressed()) {
                Gamestate.state = Gamestate.MENU;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for(ClassButton button : buttons)  {
            button.resetBool();
        }
        returnButton.resetBool();
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