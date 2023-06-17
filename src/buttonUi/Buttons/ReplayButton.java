package buttonUi.Buttons;

import buttonUi.GameButton;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.UI.PauseButtons.*;

public class ReplayButton extends GameButton {

    private BufferedImage[] images;
    private int index, rowIndex;
    public ReplayButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadImages();
    }

    private void loadImages() {
        BufferedImage temp = Load.GetSpriteImg("UI/EnglishButtons/Options/urm_buttons.png");
        images = new BufferedImage[3];
        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(i* URM_SIZE_DEFAULT,rowIndex* URM_SIZE_DEFAULT,
                    URM_SIZE_DEFAULT, URM_SIZE_DEFAULT);
        }
    }

    public void update() {
        index = 0;
        if (isMouseOver) {
            index = 1;
        }
        if (isMousePressed) {
            index = 2;
        }
    }

    public void draw (Graphics g) {
        g.drawImage(images[index], x, y, URM_SIZE, URM_SIZE, null);
    }

}