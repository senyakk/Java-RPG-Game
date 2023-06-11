package objects.objectsclasses;

import objects.GameObject;

public class Weapon extends GameObject {

    private float attack;

    /**
     * Constructs an object
     *
     * @param worldX x position in the world
     * @param worldY y position in the world
     * @param width  object's width
     * @param height object's height
     * @param name   object's name
     */
    public Weapon(float worldX, float worldY, float width, float height, String name, float attack) {
        super(worldX, worldY, width, height, name);
        this.attack = attack;
    }

    public float getAttack() {
        return attack;
    }
}
