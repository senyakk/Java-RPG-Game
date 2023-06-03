package ui;

import utilz.SaveLoad;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.VolumeButton.*;


public class VolumeButton extends PauseButton {

    private BufferedImage[]images;
    private BufferedImage slider;
    private int index = 0;
    private boolean isMouseOver, isMousePressed;
    private int buttonX, minX, maxX;
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
        BufferedImage temp = SaveLoad.GetSpriteImg(SaveLoad.VOLUME);
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

    public void reset() {
        isMouseOver = false;
        isMousePressed = false;
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
        bounds.x = buttonX  -VOLUME_WIDTH / 2;
    }

    public boolean isMouseOver() {
        return isMouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        isMouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return isMousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        isMousePressed = mousePressed;
    }


}