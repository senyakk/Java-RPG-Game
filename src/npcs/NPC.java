package npcs;

import main.Game;
import utilities.Load;


import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC extends Creature {

    private BufferedImage image;
    protected boolean isActive = true;

    protected int typeNPC;
    private int flipX = 0, flipW = 1;
    protected BufferedImage[][] images;

    public NPC(float worldX, float worldY, float width, float height) {
        super(worldX, worldY, width, height);

        image = Load.GetSpriteImg("characters/player_sprites.png");

        if (worldX == -1 && worldY == 1)
            isActive = false;

    }

    public void update() {

    }

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
    //add interaction

    //add movement

    //draw dialogue if there is a dialogue option with the npc
    public BufferedImage getImage() {
        return image;
    }
    public boolean checkActive() {
        return isActive;
    }

}
