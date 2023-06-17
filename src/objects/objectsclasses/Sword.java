package objects.objectsclasses;

import objects.GameObject;

import static utilities.Constants.Objects.OBJECT_SIZE;

public class Sword extends Weapon {
    /**
     * Constructs an object
     *
     * @param worldX x position in the world
     * @param worldY y position in the world
     */
    public Sword(float worldX, float worldY) {
        super(worldX, worldY, OBJECT_SIZE*1.5f, OBJECT_SIZE*1.5f, "sword", "sword", 2f);
        initHitArea(0, 0, (int) (OBJECT_SIZE*0.65), (int) (OBJECT_SIZE*0.65));
    }
}
