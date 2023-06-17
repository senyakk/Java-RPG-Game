package npcs;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static utilities.Constants.anim_speed;

public class NPC extends Creature {


    BufferedImage image;
    protected boolean isActive = true;

    protected String typeNPC;
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
        typeNPC = name;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/characters/" + path +".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

    }

    protected void checkCollisions() {
        collisionOn = false;
        collisionChecker.checkTile(this);
    }

    public int getAnimIndex() {
        return animIndex;
    }

    public BufferedImage[][] getImages() {
        return images;
    }
    public String getName() {
        return typeNPC;
    }

    public BufferedImage getImage() {
        return image;
    }
    public boolean checkActive() {
        return isActive;
    }


    public String[] getDialogues() {
        return dialogues;
    }

    public int getDialogueInd() {
        return dialogueInd;
    }

}
