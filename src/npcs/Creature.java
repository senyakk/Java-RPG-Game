package npcs;

import locations.CollisionChecker;
import main.GameModel;

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
    protected Rectangle solidArea = new Rectangle(0,0, GameModel.tileSize, GameModel.tileSize);
    protected int walkDir = DOWN;
    public float xDefaultHitbox, yDefaultHitbox;
    protected int animTick, animIndex;
    protected int animSpeed = 25;
    protected int state;
    protected int maxHealth;
    protected int currentHealth;


    public Creature(float X, float Y, float width, float height) {
        this.worldX = (X * GameModel.tileSize) - width /2 + (float) GameModel.tileSize / 2;
        this.worldY = (Y * GameModel.tileSize) - height /2 + (float) GameModel.tileSize / 2;
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
        this.xDefaultHitbox = xHitbox * GameModel.scale;
        this.yDefaultHitbox = yHitbox * GameModel.scale;
        solidArea = new Rectangle((int) (xHitbox * GameModel.scale), (int) (yHitbox * GameModel.scale),
                (int) (hitWidth * GameModel.scale), (int) (hitHeight * GameModel.scale));
    }

    protected void drawNPCHitArea(Graphics g, int otherScreenX, int otherScreenY) {
        g.setColor(Color.ORANGE);
        g.drawRect((int) (otherScreenX + solidArea.x), (int) (otherScreenY + solidArea.y),
                solidArea.width, solidArea.height);
    }
}
