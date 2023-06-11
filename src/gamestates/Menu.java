package gamestates;

import main.Game;
import buttonUi.GameButton;
import buttonUi.Buttons.MenuButton;
import utilities.Load;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilities.Constants.UI.MenuButtons.*;

public class Menu extends State implements Statemethods{
    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backgroundImage;

    public Menu(Game game) {
        super(game);
        loadButtons();
        backgroundImage = Load.GetSpriteImg("UI/Startscreen.png");
    }

    private void loadButtons() {
        buttons[0] = new MenuButton((int) (165 * Game.scale), (int)(170 * Game.scale),
                B_WIDTH, B_HEIGHT,0,Gamestate.CLASS_SELECTION);
        buttons[1] = new MenuButton((int)(320 * Game.scale), (int)(170 * Game.scale),
                B_WIDTH, B_HEIGHT, 1 ,Gamestate.OPTIONS);
        buttons[2] = new MenuButton((int) (475 * Game.scale), (int)(170 * Game.scale),
                B_WIDTH, B_HEIGHT, 2 ,Gamestate.QUIT);
    }


    @Override
    public void update() {
        for(MenuButton button : buttons) {
            button.update();
        }
    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(backgroundImage,0,0, Game.screenWidth, Game.screenHeight, null);
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
                    button.setState();
                }
                if (button.getState() == Gamestate.PLAYING)
                    game.getAudioPlayer().playAmbient();
                break;
            }
        }
        resetButtons();

    }

    private void resetButtons() {
        for(MenuButton button : buttons)  {
            button.resetBool();
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