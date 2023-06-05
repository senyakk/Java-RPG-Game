package ui;

import gamestates.Gamestate;
import main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

import static utilities.Constants.UI.PauseButtons.SOUND_SIZE;
import static utilities.Constants.UI.VolumeButton.SLIDER_WIDTH;
import static utilities.Constants.UI.VolumeButton.VOLUME_HEIGHT;

public class Audio {

    private SoundButton musicButton, sfxButton;
    private VolumeButton volumeButton;


    public Audio() {
        addSoundButtons();
        addVolumeButton();

    }

    private void addVolumeButton() {
        int x = (int)(214 * Game.scale);
        int y = (int)(278 * Game.scale);
        volumeButton = new VolumeButton(x, y, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    private void addSoundButtons() {
        int x = (int)(355 * Game.scale);
        int musicY = (int)(140 * Game.scale);
        int sfxY = (int) (186 * Game.scale);
        musicButton = new SoundButton(x,musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(x,sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    public void update() {
        musicButton.update();
        sfxButton.update();
        volumeButton.update();
    }

    public void draw(Graphics g) {
        musicButton.draw(g);
        sfxButton.draw(g);
        volumeButton.draw(g);
    }

    public void mouseDragged(MouseEvent e) {
        if (volumeButton.isMousePressed()) {
            volumeButton.changeSlider(e.getX());
        }
    }

    public void mousePressed(MouseEvent e) {
        if (isInOBorder(e, musicButton)) {
            musicButton.setMousePressed(true);
        }
        else if (isInOBorder(e, sfxButton)) {
            sfxButton.setMousePressed(true);
        }
        else if (isInOBorder(e, volumeButton)) {
            volumeButton.setMousePressed(true);
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
        musicButton.reset();
        sfxButton.reset();

        volumeButton.reset();
    }


    public void mouseMoved(MouseEvent e){
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        volumeButton.setMouseOver(false);

        if (isInOBorder(e, musicButton)) {
            musicButton.setMouseOver(true);
        }
        else if (isInOBorder(e, sfxButton)) {
            sfxButton.setMouseOver(true);
        }
        else if (isInOBorder(e, volumeButton)) {
            volumeButton.setMouseOver(true);
        }
    }

    private boolean isInOBorder(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

}