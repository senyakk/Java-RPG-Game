package equipment;

import main.GamePanel;

import java.awt.image.BufferedImage;

public abstract class Object {

    GamePanel gp;
    String name;
    BufferedImage image;

    public int attack;
    public int defense;

    public Object(GamePanel gp) {
        this.gp = gp;
    }
}
