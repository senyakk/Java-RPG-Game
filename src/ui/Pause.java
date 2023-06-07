package ui;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilities.Load;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilities.Constants.UI.PauseButtons.URM_SIZE;

public class Pause {

    BufferedImage background;
    private int posX, posY, width, heigth;
    private ReplayButton menuButton, replayButton, unpauseButton;
    private Playing playing;
    private Audio audio;

    public Pause(Playing playing) {
        this.playing = playing;
        loadBackground();
        audio = playing.getGame().getAudio();
        addReplayButtons();
    }


    private void addReplayButtons() {
        int menuX = (int)(252 * Game.scale);
        int replayX = (int)(300 * Game.scale);
        int unpauseX = (int) (351 * Game.scale);
        int y  = (int) (230 * Game.scale);
        menuButton = new ReplayButton(menuX,y, URM_SIZE, URM_SIZE, 2);
        replayButton = new ReplayButton(replayX,y, URM_SIZE, URM_SIZE, 1);
        unpauseButton = new ReplayButton(unpauseX, y, URM_SIZE, URM_SIZE, 0);
    }


    private void loadBackground() {
        background = Load.GetSpriteImg(Load.PAUSE);
        width = (int)(background.getWidth() * Game.scale/1.5);
        heigth =(int) (background.getHeight() * Game.scale/1.5);
        posX = Game.screenWidth / 2 - width / 2;
        posY = (int)(25 * Game.scale);
    }

    public void update() {

        menuButton.update();
        unpauseButton.update();
        replayButton.update();
        audio.update();
    }

    public void draw (Graphics g) {
        // Background
        g.drawImage(background, posX, posY, width, heigth, null);

        // Buttons
        menuButton.draw(g);
        unpauseButton.draw(g);
        replayButton.draw(g);
        audio.draw(g);

    }

    public void mouseDragged(MouseEvent e) {
        audio.mouseDragged(e);
    }

    public void mousePressed(MouseEvent e) {
        if (isInOBorder(e, menuButton)) {
            menuButton.setMousePressed(true);
        }
        else if (isInOBorder(e, replayButton)) {
            replayButton.setMousePressed(true);
        }
        else if (isInOBorder(e, unpauseButton)) {
            unpauseButton.setMousePressed(true);
        }
        else
            audio.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (isInOBorder(e, menuButton)) {
            if (menuButton.isMousePressed()) {
                Gamestate.state = Gamestate.MENU;
                playing.unpause();
            }
        }
        else if (isInOBorder(e, replayButton)) {
            if (replayButton.isMousePressed()) {
                playing.resetAll();
                playing.unpause();
            }
        }
        else if (isInOBorder(e, unpauseButton)) {
            if (unpauseButton.isMousePressed()) {
                playing.unpause();
            }
        } else
            audio.mouseReleased(e);
        menuButton.reset();
        replayButton.reset();
        unpauseButton.reset();
    }


    public void mouseMoved(MouseEvent e){
        menuButton.setMouseOver(false);
        replayButton.setMouseOver(false);
        unpauseButton.setMouseOver(false);

        if (isInOBorder(e, menuButton)) {
            menuButton.setMouseOver(true);
        }
        else if (isInOBorder(e, replayButton)) {
            replayButton.setMouseOver(true);
        }
        else if (isInOBorder(e, unpauseButton)) {
            unpauseButton.setMouseOver(true);
        }
        else
            audio.mouseMoved(e);
    }

    private boolean isInOBorder(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

}