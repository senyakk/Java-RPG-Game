package buttonUi;

import buttonUi.Buttons.SoundButton;
import buttonUi.Buttons.VolumeButton;
import main.GameModel;

import java.awt.*;
import java.awt.event.MouseEvent;

import static utilities.Constants.UI.PauseButtons.SOUND_SIZE;
import static utilities.Constants.UI.VolumeButton.SLIDER_WIDTH;
import static utilities.Constants.UI.VolumeButton.VOLUME_HEIGHT;

public class AudioHandler {

    private SoundButton musicButton, sfxButton;
    private VolumeButton volumeButton;

    private GameModel gameModel;

    public AudioHandler(GameModel gameModel) {
        this.gameModel = gameModel;
        addSoundButtons();
        addVolumeButton();

    }

    private void addVolumeButton() {
        int x = (int)(248 * GameModel.scale);
        int y = (int)(198 * GameModel.scale);
        volumeButton = new VolumeButton(x, y, (int) (SLIDER_WIDTH), (int) (VOLUME_HEIGHT));
    }

    private void addSoundButtons() {
        int x = (int)(345 * GameModel.scale);
        int musicY = (int)(105 * GameModel.scale);
        int sfxY = (int) (135 * GameModel.scale);
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
            float valueB = volumeButton.getFloatValue();
            volumeButton.changeSlider(e.getX());
            float valueA = volumeButton.getFloatValue();
            if (valueB != valueA)
                gameModel.getAudioPlayer().setVolume(valueA);
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
                gameModel.getAudioPlayer().turnSongMute();
            }
        }
        else if (isInOBorder(e, sfxButton)) {
            if (sfxButton.isMousePressed()) {
                sfxButton.setMuted(!sfxButton.isMuted());
                gameModel.getAudioPlayer().turnEffectMute();
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

    private boolean isInOBorder(MouseEvent e, GameButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

}