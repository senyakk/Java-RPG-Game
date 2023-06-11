package gamestates;

import main.Game;
import buttonUi.AudioHandler;
import buttonUi.GameButton;
import buttonUi.Buttons.ReplayButton;
import utilities.Load;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilities.Constants.UI.PauseButtons.URM_SIZE;

public class Options extends State implements Statemethods {
    private AudioHandler audio;
    private BufferedImage backgroundImage, optionsBackgroundImage;
    private int bgX, bgY, bgW, bgH;
    private ReplayButton menuB;
    public Options(Game game) {
        super(game);
        loadImages();
        loadButtons();
        audio = game.getAudio();
    }

    private void loadButtons() {
        int menuX = (int) (300 * Game.scale);
        int menuY = (int) (235 * Game.scale);
        menuB = new ReplayButton(menuX, menuY, URM_SIZE, URM_SIZE, 2);
    }

    private void loadImages() {

        backgroundImage = Load.GetSpriteImg("UI/Startscreen.png");
        optionsBackgroundImage = Load.GetSpriteImg("UI/options_background.png");

        bgW = (int) (optionsBackgroundImage.getWidth() * Game.scale/1.5);
        bgH = (int) (optionsBackgroundImage.getHeight() * Game.scale/1.5);
        bgX = Game.screenWidth / 2 - bgW / 2;
        bgY = (int) (33 * Game.scale);

    }

    @Override
    public void update() {
        menuB.update();
        audio.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0,0, Game.screenWidth, Game.screenHeight, null);
        g.drawImage(optionsBackgroundImage, bgX,bgY, bgW, bgH, null);

        menuB.draw(g);
        audio.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Gamestate.state = Gamestate.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        audio.mouseDragged(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isInOBorder(e, menuB)) {
            menuB.setMousePressed(true);
        }
        else {
            audio.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isInOBorder(e, menuB)) {
            if (menuB.isMousePressed()) {
                Gamestate.state = Gamestate.MENU;
            }
        }
        else {
            audio.mouseReleased(e);
        }
        menuB.reset();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        menuB.setMouseOver(false);
        if (isInOBorder(e, menuB)) {
            menuB.setMouseOver(true);
        }
        else {
            audio.mouseMoved(e);
        }
    }

    private boolean isInOBorder(MouseEvent e, GameButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

}