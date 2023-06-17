package objects.objectsclasses;

import objects.GameObject;

import static utilities.Constants.Objects.OBJECT_SIZE;

public class Flower extends Weapon {
    /**
     * Constructs an object
     *
     * @param worldX x position in the world
     * @param worldY y position in the world
     */
    public Flower(float worldX, float worldY) {
        super(worldX, worldY, OBJECT_SIZE*1.5f, OBJECT_SIZE*1.5f, "flower", "flower",0.5f);
        initHitArea(0, 0, (int) (OBJECT_SIZE*0.65), (int) (OBJECT_SIZE*0.65));
    }
}