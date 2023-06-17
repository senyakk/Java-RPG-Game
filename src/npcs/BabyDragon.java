package npcs;

import utilities.Constants;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.NPCs.NPCSize;
import static utilities.Constants.NPCs.NPCSizeDef;
import static utilities.Constants.anim_speed;
import static utilities.Load.GetSpriteImg;

public class BabyDragon extends NPC {

    /**
     * Constructs the NPC Baby Dragon
     *
     * @param worldX x position in the world
     * @param worldY y position in the world
     */
    public BabyDragon(float worldX, float worldY) {
        super(worldX, worldY, (float) (NPCSize*2),(float) (NPCSize*2) ,"BabyGreenDragonIdleSide", "Baby Dragon");
        initHitArea(0, 5, 26, 26);
        setDialogue();
        loadNPCImages();
    }

    public void setDialogue() {
        dialogues[0] = "Milo! I am sick and I need you to make a potion to heal me.";
        dialogues[1] = "Speak to the professor for more information";
        dialogues[2] = "You can find him in his house";
    }

    public void update() {
        updateAnimation();
    }

    protected void updateAnimation() {
        animTick++;
        if(animTick >= anim_speed) {
            animTick = 0;
            animIndex++;
            if(animIndex >= 4) {
                animIndex = 0;
            }
        }
    }

    private void loadNPCImages() {
        images = new BufferedImage[1][4];
        BufferedImage temp = GetSpriteImg("characters/BabyGreenDragonIdleSide.png");
        for(int j = 0; j < images.length; j++) {
            for (int i = 0; i < images[j].length; i++) {
                images[j][i] = temp.getSubimage(i * NPCSizeDef, j* NPCSizeDef,
                        NPCSizeDef, NPCSizeDef);
            }
        }
    }

}
