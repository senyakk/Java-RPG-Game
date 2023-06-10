package npcs;

import locations.CollisionChecker;
import main.Game;

import java.awt.*;

import static utilities.Constants.Direction.*;

public abstract class Creature {

    // Position variables in the world
    protected float worldX, worldY;
    protected float width, height;

    // Position variables on the screen
    protected int screenX, screenY;
    protected float speed;
    protected CollisionChecker collisionChecker;
    protected boolean collisionOn = false;
    protected boolean moving = false;
    protected Rectangle solidArea = new Rectangle(0,0, Game.tileSize, Game.tileSize);
    protected int walkDir = DOWN;
    public float xDefaultHitbox, yDefaultHitbox;
    protected int animTick, animIndex;
    protected int animSpeed = 25;
    protected int state;
    protected int maxHealth;
    protected int currentHealth;

    public Creature(float worldX, float worldY, float width, float height) {
        this.worldX = (worldX * Game.tileSize) - width /2 + (float) Game.tileSize / 2;
        this.worldY = (worldY * Game.tileSize) - height /2 + (float) Game.tileSize / 2;
        this.width = width;
        this.height = height;
    }

    public void addCollisionChecker(CollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
    }

    public Rectangle getHitArea() { return solidArea; }

    public int getScreenX() {
        return screenX;
    }
    public int getScreenY() {
        return screenY;
    }
    public float getWorldX() {
        return worldX;
    }
    public float getWorldY() {
        return worldY;
    }
    public float getSpeed() {
        return speed;
    }
    public int getDirection() {
        return walkDir;
    }
    public void setCollision() {
        collisionOn = true;
    }
    public int getState() {
        return state;
    }
    public int getWidth() { return (int) width; }
    public int getHeight() { return (int) height;}

    public float getHitAreaDefaultX() { return xDefaultHitbox; }
    public float getHitAreaDefaultY() {
        return yDefaultHitbox;
    }

    public boolean getCollision() {
        return collisionOn;
    }

    protected void initHitArea(int xHitbox, int yHitbox, int hitWidth, int hitHeight)  {
        this.xDefaultHitbox = xHitbox * Game.scale;
        this.yDefaultHitbox = yHitbox * Game.scale;
        solidArea = new Rectangle((int) (xHitbox * Game.scale), (int) (yHitbox * Game.scale),
                (int) (hitWidth * Game.scale), (int) (hitHeight * Game.scale));
    }

    protected void drawNPCHitArea(Graphics g, int otherScreenX, int otherScreenY) {
        g.setColor(Color.ORANGE);
        g.drawRect((int) (otherScreenX + solidArea.x), (int) (otherScreenY + solidArea.y),
                solidArea.width, solidArea.height);
    }
}
