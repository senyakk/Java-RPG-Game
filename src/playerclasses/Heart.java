package playerclasses;

import objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Heart extends GameObject {

    public BufferedImage heart_half, heart_blank;
    /**
     * Constructs an object
     *
     * @param worldX x position in the world
     * @param worldY y position in the world
     * @param width  object's width
     * @param height object's height
     * @param name   object's name
     */
    public Heart(float worldX, float worldY, float width, float height, String name) {
        super(worldX, worldY, width, height, "heart_full");

        try {
            heart_half = ImageIO.read(getClass().getResourceAsStream("/heart_half/" + name +".png"));
            heart_blank = ImageIO.read(getClass().getResourceAsStream("/heart_blank/" + name +".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
