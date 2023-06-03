package ui;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.SaveLoad;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.PauseButtons.SOUND_SIZE;
import static utilz.Constants.UI.PauseButtons.URM_SIZE;

public class Pause {

    BufferedImage background;
    private int posX, posY, width, heigth;
    private SoundButton musicButton, sfxButton;
    private ReplayButton menuButton, replayButton, unpauseButton;
    private Playing playing;

    public Pause(Playing playing) {
        this.playing = playing;
        loadBackground();
        addSoundButtons();
        addReplayButtons();
    }

    private void addReplayButtons() {
        int menuX = (int)(218 * Game.scale);
        int replayX = (int)(292 * Game.scale);
        int unpauseX = (int) (367 * Game.scale);
        int y  = (int) (325 * Game.scale);
        menuButton = new ReplayButton(menuX,y, URM_SIZE, URM_SIZE, 2);
        replayButton = new ReplayButton(replayX,y, URM_SIZE, URM_SIZE, 1);
        unpauseButton = new ReplayButton(unpauseX, y, URM_SIZE, URM_SIZE, 0);
    }

    private void addSoundButtons() {
        int x = (int)(355 * Game.scale);
        int musicY = (int)(140 * Game.scale);
        int sfxY = (int) (186 * Game.scale);
        musicButton = new SoundButton(x,musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(x,sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    private void loadBackground() {
        background = SaveLoad.GetSpriteImg(SaveLoad.PAUSE);
        width = (int)(background.getWidth() * Game.scale);
        heigth =(int) (background.getHeight() * Game.scale);
        posX = Game.screenWidth / 2 - width / 2;
        posY = (int)(25 * Game.scale);
    }

    public void update() {
        musicButton.update();
        sfxButton.update();
        menuButton.update();
        unpauseButton.update();
        replayButton.update();
    }

    public void draw (Graphics g) {
        // Background
        g.drawImage(background, posX, posY, width, heigth, null);

        // Buttons
        musicButton.draw(g);
        sfxButton.draw(g);
        menuButton.draw(g);
        unpauseButton.draw(g);
        replayButton.draw(g);

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (isInOBorder(e, musicButton)) {
            musicButton.setMousePressed(true);
        }
        else if (isInOBorder(e, sfxButton)) {
            sfxButton.setMousePressed(true);
        }
        else if (isInOBorder(e, menuButton)) {
            menuButton.setMousePressed(true);
        }
        else if (isInOBorder(e, replayButton)) {
            replayButton.setMousePressed(true);
        }
        else if (isInOBorder(e, unpauseButton)) {
            unpauseButton.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isInOBorder(e, musicButton)) {
            if (musicButton.isMousePressed()) {
                musicButton.setMuted(!musicButton.isMuted());
            }
        }
        else if (isInOBorder(e, sfxButton)) {
            if (sfxButton.isMousePressed()) {
                sfxButton.setMuted(!sfxButton.isMuted());
            }
        }
        else if (isInOBorder(e, menuButton)) {
            if (menuButton.isMousePressed()) {
                Gamestate.state = Gamestate.MENU;
                playing.unpause();
            }
        }
        else if (isInOBorder(e, replayButton)) {
            if (replayButton.isMousePressed()) {
                System.out.println("Replay the game!");
            }
        }
        else if (isInOBorder(e, unpauseButton)) {
            if (unpauseButton.isMousePressed()) {
                playing.unpause();
            }
        }
        musicButton.reset();
        sfxButton.reset();
        menuButton.reset();
        replayButton.reset();
        unpauseButton.reset();
    }


    public void mouseMoved(MouseEvent e){
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuButton.setMouseOver(false);
        replayButton.setMouseOver(false);
        unpauseButton.setMouseOver(false);

        if (isInOBorder(e, musicButton)) {
            musicButton.setMouseOver(true);
        }
        else if (isInOBorder(e, sfxButton)) {
            sfxButton.setMouseOver(true);
        }
        else if (isInOBorder(e, menuButton)) {
            menuButton.setMouseOver(true);
        }
        else if (isInOBorder(e, replayButton)) {
            replayButton.setMouseOver(true);
        }
        else if (isInOBorder(e, unpauseButton)) {
            unpauseButton.setMouseOver(true);
        }
    }

    private boolean isInOBorder(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

}