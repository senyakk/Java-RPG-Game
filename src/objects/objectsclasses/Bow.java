package objects.objectsclasses;

import objects.GameObject;

import static utilities.Constants.Objects.OBJECT_SIZE;

public class Bow extends Weapon {
    /**
     * Constructs an object
     *
     * @param worldX x position in the world
     * @param worldY y position in the world
     */
    public Bow(float worldX, float worldY) {
        super(worldX, worldY, OBJECT_SIZE*1.5f, OBJECT_SIZE*1.5f, "bowItem", "bowItem",  2f);
        initHitArea(0, 0, (int) (OBJECT_SIZE*0.65), (int) (OBJECT_SIZE*0.65));
    }
}
