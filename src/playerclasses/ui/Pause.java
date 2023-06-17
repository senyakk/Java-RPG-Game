package playerclasses.ui;

import buttonUi.AudioHandler;
import buttonUi.GameButton;
import gamestates.Gamestate;
import gamestates.Playing;
import buttonUi.Buttons.ReplayButton;
import main.GameModel;
import utilities.Load;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilities.Constants.GameLanguage.*;
import static utilities.Constants.UI.PauseButtons.URM_SIZE;

/**
 * @author Arsenijs
 * Class that handles Pause view in UI
 */
public class Pause {

    BufferedImage background;
    private int posX, posY, width, heigth;
    private ReplayButton menuButton, replayButton, unpauseButton;
    private Playing playing;
    private AudioHandler audioHandler;

    public Pause(Playing playing) {
        this.playing = playing;
        loadBackground();
        audioHandler = playing.getGameModel().getAudio();
        addReplayButtons();
    }


    private void addReplayButtons() {
        int menuX = (int)(252 * GameModel.scale);
        int replayX = (int)(300 * GameModel.scale);
        int unpauseX = (int) (351 * GameModel.scale);
        int y  = (int) (230 * GameModel.scale);
        menuButton = new ReplayButton(menuX,y, URM_SIZE, URM_SIZE, 2);
        replayButton = new ReplayButton(replayX,y, URM_SIZE, URM_SIZE, 1);
        unpauseButton = new ReplayButton(unpauseX, y, URM_SIZE, URM_SIZE, 0);
    }


    private void loadBackground() {
        switch (playing.getGameModel().getLanguage()) {
            case ENGLISH -> {
                background = Load.GetSpriteImg("UI/EnglishButtons/Options/pause_menu.png");
            }
            case DUTCH -> {
                // Dutch pause here
                background = Load.GetSpriteImg("UI/Dutch/Options/DutchButtons/Options/pause_menu_Dutch.png");
            }
        }
        width = (int)(background.getWidth() * GameModel.scale/1.5);
        heigth =(int) (background.getHeight() * GameModel.scale/1.5);
        posX = GameModel.screenWidth / 2 - width / 2;
        posY = (int)(25 * GameModel.scale);
    }

    public void update() {

        menuButton.update();
        unpauseButton.update();
        replayButton.update();
        audioHandler.update();
    }

    public void draw (Graphics g) {
        // Background
        g.drawImage(background, posX, posY, width, heigth, null);

        // Buttons
        menuButton.draw(g);
        unpauseButton.draw(g);
        replayButton.draw(g);
        audioHandler.draw(g);

    }

    public void mouseDragged(MouseEvent e) {
        audioHandler.mouseDragged(e);
    }

    public void mousePressed(MouseEvent e) {
        if (isInBorder(e, menuButton)) {
            menuButton.setMousePressed(true);
        }
        else if (isInBorder(e, replayButton)) {
            replayButton.setMousePressed(true);
        }
        else if (isInBorder(e, unpauseButton)) {
            unpauseButton.setMousePressed(true);
        }
        else
            audioHandler.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (isInBorder(e, menuButton)) {
            if (menuButton.isMousePressed()) {
                playing.getGameModel().setGameState(Gamestate.MENU);
                playing.resetAll();
                playing.unpause();
            }
        }
        else if (isInBorder(e, replayButton)) {
            if (replayButton.isMousePressed()) {
                playing.resetAll();
                playing.unpause();
            }
        }
        else if (isInBorder(e, unpauseButton)) {
            if (unpauseButton.isMousePressed()) {
                playing.unpause();
            }
        } else
            audioHandler.mouseReleased(e);
        menuButton.reset();
        replayButton.reset();
        unpauseButton.reset();
    }


    public void mouseMoved(MouseEvent e){
        menuButton.setMouseOver(false);
        replayButton.setMouseOver(false);
        unpauseButton.setMouseOver(false);

        if (isInBorder(e, menuButton)) {
            menuButton.setMouseOver(true);
        }
        else if (isInBorder(e, replayButton)) {
            replayButton.setMouseOver(true);
        }
        else if (isInBorder(e, unpauseButton)) {
            unpauseButton.setMouseOver(true);
        }
        else
            audioHandler.mouseMoved(e);
    }

    private boolean isInBorder(MouseEvent e, GameButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

}