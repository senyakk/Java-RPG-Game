package npcs;

import java.awt.image.BufferedImage;

import static utilities.Constants.Direction.*;

public abstract class NPC extends Creature {
    protected int typeNPC;
    protected String[] dialogues = new String[20];
    protected int dialogueInd = 0;
    public NPC(float worldX, float worldY, float width, float height, int typeNPC) {
        super(worldX, worldY, width, height);
        this.typeNPC = typeNPC;
    }

    public String[] getDialogues() {
        return dialogues;
    }

    public int getDialogueInd() {
        return dialogueInd;
    }
}