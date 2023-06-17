package npcs;

import utilities.Constants;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.NPCs.NPCSize;
import static utilities.Constants.NPCs.NPCSizeDef;
import static utilities.Load.GetSpriteImg;

public class BabyDragon extends NPC {

    /**
     * Constructs the NPC Baby Dragon
     *
     * @param worldX x position in the world
     * @param worldY y position in the world
     */
    public BabyDragon(float worldX, float worldY) {
        super(worldX, worldY, (float) (NPCSize*1.5),(float) (NPCSize*1.5) ,"BabyGreenDragonIdleSide", "Baby Dragon");
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

    public void draw(Graphics g) {
        super.draw(g);
    }

    private void loadNPCImages() {
        images = new BufferedImage[1][4];
        BufferedImage temp = GetSpriteImg("BabyGreenDragonIdleSide.png");
        for(int j = 0; j < images.length; j++) {
            for (int i = 0; i < images[j].length; i++) {
                images[j][i] = temp.getSubimage(i * NPCSizeDef, j* NPCSizeDef,
                        NPCSizeDef, NPCSizeDef);
            }
        }
    }

}
