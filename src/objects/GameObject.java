package objects;

import locations.CollisionChecker;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameObject {

    // Position variables in the world
    protected float worldX, worldY;
    protected float width, height;

    protected String name;
    protected BufferedImage image;

    protected float xDefaultHitbox, yDefaultHitbox;
    protected boolean isActive = true;

    // Position variables on the screen
    protected int screenX, screenY;
    protected CollisionChecker collisionChecker;
    protected boolean collisionOn = false;
    protected Rectangle solidArea = new Rectangle(0,0, Game.tileSize, Game.tileSize);

    public GameObject(float worldX, float worldY, float width, float height, String name) {
        this.worldX = (worldX * Game.tileSize) - width /2 + (float) Game.tileSize / 2;
        this.worldY = (worldY * Game.tileSize) - height /2 + (float) Game.tileSize / 2;
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

    protected void initHitArea(int xHitbox, int yHitbox, int hitWidth, int hitHeight)  {
        this.xDefaultHitbox = xHitbox * Game.scale;
        this.yDefaultHitbox = yHitbox * Game.scale;
        solidArea = new Rectangle((int) (xHitbox * Game.scale), (int) (yHitbox * Game.scale),
                (int) (hitWidth * Game.scale), (int) (hitHeight * Game.scale));
    }

    protected void drawObjectHitArea(Graphics g, int otherScreenX, int otherScreenY) {
        g.setColor(Color.ORANGE);
        g.drawRect((int) (otherScreenX + solidArea.x), (int) (otherScreenY + solidArea.y),
                solidArea.width, solidArea.height);
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

    public void setCollision() {
        collisionOn = true;
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

}
