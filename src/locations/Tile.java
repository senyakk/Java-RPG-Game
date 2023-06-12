package locations;

import java.awt.image.BufferedImage;

public class Tile {

    private String name;

    // Image of the tile
    public BufferedImage image;
    // Collision status of the tile
    public boolean collision =  false;
    public Tile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
