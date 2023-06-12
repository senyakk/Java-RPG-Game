package npcs;

import main.Game;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC extends Creature {

    private BufferedImage image;
    protected boolean isActive = true;

    public NPC(float worldX, float worldY, float width, float height) {
        super(worldX, worldY, width, height);

        image = Load.GetSpriteImg("characters/player_sprites.png");
        //look at how player is handled for animations
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
