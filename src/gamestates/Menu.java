package gamestates;

import main.Game;
import ui.MenuButton;
import utilities.Load;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements Statemethods{
    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage background, backgroundImage;
    private int menuX, menuY, menuWidth, menuHeight;
    public Menu(Game game) {
        super(game);
        loadButtons();
        backgroundImage = Load.GetSpriteImg(Load.BACKGROUND_IMG);
    }

    private void loadButtons() {
        buttons[0] = new MenuButton((int) (165 * Game.scale), (int)(170 * Game.scale),  0 ,Gamestate.PLAYING);
        buttons[1] = new MenuButton((int)(320 * Game.scale), (int)(170 * Game.scale),  1 ,Gamestate.OPTIONS);
        buttons[2] = new MenuButton((int) (475 * Game.scale), (int)(170 * Game.scale), 2 ,Gamestate.QUIT);
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
            if (inBounds(e, button)) {
                button.setMousePressed(true);
                break;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton button : buttons) {
            if (inBounds(e, button)) {
                if (button.isMousePressed()) {
                    button.setGameState();
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
            if (inBounds(e, button)) {
                button.setMouseOver(true);
                break;
            }
        }
    }

}