package objects;

import inventory.modelfiles.Item;
import locations.CollisionChecker;
import main.GameModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameObject {

    protected float worldX, worldY, width, height;              // Position and size in the world
    protected String name;                                      // Name of the object
    protected BufferedImage image;                              // Image of the object
    protected float xDefaultHitbox, yDefaultHitbox;             // Hitbox of the object
    protected boolean isActive = true;                          // Activity status of the object
    protected int screenX, screenY;                             // Position on the screen when the player is moving
    protected CollisionChecker collisionChecker;                // Collision checker
    protected boolean collisionOn = false;
    protected Rectangle solidArea = new Rectangle(0,0, GameModel.tileSize, GameModel.tileSize);

    protected Item item;                                        // Item that represents the GameObject

    /**
     * Constructs an object
     * @param worldX x position in the world
     * @param worldY y position in the world
     * @param width object's width
     * @param height object's height
     * @param name object's name
     */
    public GameObject(float worldX, float worldY, float width, float height, String name) {
        this.worldX = (worldX * GameModel.tileSize) - width /2 + (float) GameModel.tileSize / 2;
        this.worldY = (worldY * GameModel.tileSize) - height /2 + (float) GameModel.tileSize / 2;
        this.width = width;
        this.height = height;
        this.name = name;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/" + name +".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() { return name; }

    public boolean checkActive() {
        return isActive;
    }

    public void deactivate() {
        isActive = false;
        solidArea = null;
    }

    /**
     * Set hitbox for the object
     * @param xHitbox x coordinate
     * @param yHitbox y coordinate
     * @param hitWidth hitbox width
     * @param hitHeight hitbox height
     */
    protected void initHitArea(int xHitbox, int yHitbox, int hitWidth, int hitHeight)  {
        this.xDefaultHitbox = xHitbox * GameModel.scale;
        this.yDefaultHitbox = yHitbox * GameModel.scale;
        solidArea = new Rectangle((int) (xHitbox * GameModel.scale), (int) (yHitbox * GameModel.scale),
                (int) (hitWidth * GameModel.scale), (int) (hitHeight * GameModel.scale));
    }

    /**
     * Add collision checker to the object
     * @param collisionChecker
     */
    public void addCollisionChecker(CollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
    }

    /**
     * @return hitbox area
     */
    public Rectangle getHitArea() { return solidArea; }

    /**
     * @return object x position of the screen
     */
    public int getScreenX() {
        return screenX;
    }

    /**
     * @return object y position of the screen
     */
    public int getScreenY() {
        return screenY;
    }

    /**
     * @return object x position in the world
     */
    public float getWorldX() {
        return worldX;
    }

    /**
     * @return object y position in the world
     */
    public float getWorldY() {
        return worldY;
    }

    /**
     * Sets collision on for the object
     */
    public void setCollision() {
        collisionOn = true;
    }

    /**
     * @return width of the object
     */
    public int getWidth() { return (int) width; }

    /**
     * @return height of the object
     */
    public int getHeight() { return (int) height;}

    /**
     * @return x position of object hitbox
     */
    public float getHitAreaDefaultX() { return xDefaultHitbox; }

    /**
     * @return y position of object hitbox
     */
    public float getHitAreaDefaultY() {
        return yDefaultHitbox;
    }

    /**
     * @return status of collision
     */
    public boolean getCollision() {
        return collisionOn;
    }

}
