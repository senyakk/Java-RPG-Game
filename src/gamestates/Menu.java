package gamestates;

import main.Game;
import ui.MenuButton;
import utilz.SaveLoad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements Statemethods{
    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage background;
    private int menuX, menuY, menuWidth, menuHeight;
    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        background = SaveLoad.GetSpriteImg(SaveLoad.MENU_BACKGROUND);
        menuWidth = (int) (background.getWidth() * Game.scale);
        menuHeight = (int) (background.getHeight() * Game.scale);
        menuX = Game.screenWidth / 2 - menuWidth / 2;
        menuY = (int)(45 * Game.scale);
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.screenWidth / 2, (int) (150 * Game.scale), 0 ,Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.screenWidth / 2, (int) (220 * Game.scale), 1 ,Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Game.screenWidth / 2, (int) (290 * Game.scale), 2 ,Gamestate.QUIT);
    }


    @Override
    public void update() {
        for(MenuButton button : buttons) {
            button.update();
        }
    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(background, menuX, menuY, menuWidth, menuHeight, null);
        for(MenuButton button : buttons) {
            button.draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e, JPanel targetPanel) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e, JPanel targetPanel) {

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