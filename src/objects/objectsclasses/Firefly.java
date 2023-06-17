package objects.objectsclasses;

import objects.GameObject;

import static utilities.Constants.Objects.OBJECT_SIZE;

public class Firefly extends GameObject {

    public Firefly(float worldX, float worldY) {
        super(worldX, worldY, OBJECT_SIZE*1.5f, OBJECT_SIZE*1.5f,
                "/Fireflyitem", "firefly");
        initHitArea(0, 0, (int) (16 *1.5), (int) (16*1.5));
    }
}
