package npcs;

import utilities.Constants;
import utilities.Load;

import static utilities.Constants.NPCs.NPCSize;

public class BabyDragon extends NPC {

    /**
     * Constructs the NPC Baby Dragon
     *
     * @param worldX x position in the world
     * @param worldY y position in the world
     */
    public BabyDragon(float worldX, float worldY) {
        super(worldX, worldY, (float) (NPCSize*1.5),(float) (NPCSize*1.5) ,"BabyGreenDragonIdleSide", "BabyDragon");
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "Milo! I am sick and I need you to make a potion to heal me.";
        dialogues[1] = "Speak to the professor for more information";
        dialogues[2] = "You can find him in his house";
    }


}
