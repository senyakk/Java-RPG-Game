package objects.objectsclasses;

import main.Game;
import objects.GameObject;

import static utilities.Constants.Objects.OBJECT_SIZE;
import static utilities.Constants.Objects.OBJECT_SIZE_DEFAULT;


public class Key extends GameObject {

    /**
     * Creates a Key object
     * @param worldX x coordinate position in a world
     * @param worldY y coordinate position in a world
     */
    public Key(float worldX, float worldY) {
        super(worldX, worldY, (float) (OBJECT_SIZE*1.5), (float) (OBJECT_SIZE*1.5), "key");
        initHitArea(0, 0, (int) (16*1.5), (int) (16*1.5));
    }

}