package objects.objectsclasses;

import objects.GameObject;

import static utilities.Constants.Objects.OBJECT_SIZE;


public class Key extends GameObject {

    public Key(float worldX, float worldY) {
        super(worldX, worldY, OBJECT_SIZE*1.5f, OBJECT_SIZE*1.5f, "key");
        initHitArea(0, 0, (int) (OBJECT_SIZE*0.65), (int) (OBJECT_SIZE*0.65));
    }

}