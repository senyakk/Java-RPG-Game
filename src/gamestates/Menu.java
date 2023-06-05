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
        loadBackground();
        backgroundImage = Load.GetSpriteImg(Load.BACKGROUND_IMG);
    }

    private void loadBackground() {
        background = Load.GetSpriteImg(Load.MENU_BACKGROUND);
        menuWidth = (int) (background.getWidth() * Game.scale);
        menuHeight = (int) (background.getHeight() * Game.scale);
        menuX = Game.screenWidth / 2 - menuWidth / 2;
        menuY = (int)(60 * Game.scale);
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.screenWidth / 2, (int) (165 * Game.scale), 0 ,Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.screenWidth / 2, (int) (235 * Game.scale), 1 ,Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Game.screenWidth / 2, (int) (305 * Game.scale), 2 ,Gamestate.QUIT);
    }


    @Override
    public void update() {
        for(MenuButton button : buttons) {
            button.update();
        }
    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(backgroundImage,0,0,Game.screenWidth, Game.screenHeight, null);
        g.drawImage(background, menuX, menuY, menuWidth, menuHeight, null);
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