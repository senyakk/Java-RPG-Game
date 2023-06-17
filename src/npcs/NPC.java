package npcs;

import main.Game;
import playerclasses.PlayerModel;
import utilities.HelpMethods;
import utilities.Load;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static utilities.Constants.anim;

public class NPC extends Creature {


    BufferedImage image;
    protected boolean isActive = true;

    protected int typeNPC;
    private int flipX = 0, flipW = 1;
    protected BufferedImage[][] images;
    protected String[] dialogues = new String[50];
    protected int dialogueInd = 0;

    /**
     * Constructs an NPC based on the superclass Creature
     * @param worldX x position in the world
     * @param worldY y position in the world
     * @param width width of the NPC
     * @param height height of the NPC
     */

    public NPC(float worldX, float worldY, float width, float height, String path, String name) {
        super(worldX, worldY, width, height);

        if (worldX == -1 && worldY == 1)
            isActive = false;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/characters/" + path +".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

    }
    public void draw(Graphics g) {}


    protected void checkCollisions() {
        collisionOn = false;
        collisionChecker.checkTile(this);
        //collisionChecker.checkObject(this);
    }

    public int getAnimIndex() {
        return animIndex;
    }

    public BufferedImage[][] getImages() {
        return images;
    }
    public int getName() {
        return typeNPC;
    }

    public BufferedImage getImage() {
        return image;
    }
    public boolean checkActive() {
        return isActive;
    }

    protected void drawNPCHitArea(Graphics g, int otherScreenX, int otherScreenY) {
        g.setColor(Color.ORANGE);
        g.drawRect((int) (otherScreenX + solidArea.x), (int) (otherScreenY + solidArea.y),
                solidArea.width, solidArea.height);
    }

    public String[] getDialogues() {
        return dialogues;
    }

    public int getDialogueInd() {
        return dialogueInd;
    }

    protected void updateAnimation() {
        animTick++;
        if(animTick >= anim) {
            animTick = 0;
            animIndex++;
            if(animIndex >= 4) {
                animIndex = 0;
            }
        }
    }
}
