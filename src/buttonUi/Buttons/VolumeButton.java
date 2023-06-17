package buttonUi.Buttons;

import buttonUi.GameButton;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.UI.VolumeButton.*;


public class VolumeButton extends GameButton {

    private BufferedImage[]images;
    private BufferedImage slider;
    private int index = 0;
    private int buttonX, minX, maxX;
    private float floatValue = 0f;
    public VolumeButton(int x, int y, int width, int height) {
        super(x+width/2, y, VOLUME_WIDTH, height);
        bounds.x -=VOLUME_WIDTH/2;
        buttonX = x+width/2;
        this.x = x;
        this.width = width;
        minX = x + VOLUME_WIDTH / 2;
        maxX = x + width - VOLUME_WIDTH /2;
        loadImages();
    }

    private void loadImages() {
        BufferedImage temp = Load.GetSpriteImg("UI/English/Options/volume_buttons.png");
        images = new BufferedImage[3];
        for (int i = 0; i <images.length; i++) {
            images[i] = temp.getSubimage(i* VOLUME_DEFAULT_WIDTH,0,
                    VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
        }
        slider = temp.getSubimage(3* VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
    }

    public void update() {
        index = 0;
        if (isMouseOver()) {
            index = 1;
        }
        if (isMousePressed()) {
            index = 2;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(slider, x, y ,width, height, null);
        g.drawImage(images[index], buttonX - (VOLUME_WIDTH / 2), y, VOLUME_WIDTH, height, null);
    }

    public void changeSlider(int x) {
        if (x < minX) {
            buttonX = minX;
        }
        else if (x > maxX) {
            buttonX = maxX;
        }
        else {
            buttonX = x;
        }
        updateFloatValue();
        bounds.x = buttonX  -VOLUME_WIDTH / 2;
    }

    private void updateFloatValue() {
        float range = maxX - minX;
        float value = buttonX - minX;
        floatValue = value/range;

    }

    public float getFloatValue () {
        return floatValue;
    }


}