package buttonUi.Buttons;

import buttonUi.GameButton;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilities.Constants.UI.PauseButtons.*;

public class SoundButton extends GameButton {
    private BufferedImage[][] images;
    private int rowIndex, colIndex;
    private boolean isMuted;
    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadImages();
    }

    private void loadImages() {
        BufferedImage temp = Load.GetSpriteImg("UI/EnglishButtons/Options/sound_button.png");
        images = new BufferedImage[2][3];
        for (int j = 0; j < images.length; j++) {
            for (int i = 0; i <images[j].length; i++) {
                images[j][i] = temp.getSubimage(i* SOUND_SIZE_DEFAULT,j* SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
            }
        }
    }

    public void update() {
        if (isMuted) {
            rowIndex = 1;
        }
        else {
            rowIndex = 0;
        }

        colIndex = 0;
        if (isMouseOver) {
            colIndex = 1;
        }
        if (isMousePressed) {
            colIndex = 2;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(images[rowIndex][colIndex], x, y, width, height, null);
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }
}