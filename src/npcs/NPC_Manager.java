package npcs;

import gamestates.Playing;
import locations.CollisionChecker;
import objects.GameObject;
import objects.objectsclasses.Key;
import playerclasses.PlayerModel;
import utilities.HelpMethods;
import utilities.Load;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author Cansu
 */

public class NPC_Manager {

    private final Playing playing;
    private final ArrayList<NPC> NPCs;

    private CollisionChecker collisionChecker;
    protected String[] dialogues = new String[50];
    protected int dialogueInd = 0;

    public NPC_Manager(Playing playing) {
        this.playing = playing;
        this.collisionChecker = playing.getCollisionChecker();
        NPCs = playing.getLevelManager().getCurrentLevel().getNPCs();
        placeNPC();
    }

    public void placeNPC() {
        switch (playing.getLevelManager().getCurrentLevelId()) {
            case 0 -> {
                NPCs.add(new BabyDragon(24 ,19));
                NPCs.add(new Professor(22 ,22));
            }
            case 1 -> {
                NPCs.add(new Professor(3 ,3));
            }
        }
        for (NPC n : NPCs) {
            n.addCollisionChecker(collisionChecker);
        }
    }

    public void update() {
        for (NPC n : NPCs) {
            n.update();
        }
    }
    public String[] getDialogues() {
        return dialogues;
    }

    public int getDialogueInd() {
        return dialogueInd;
    }


    public void resetAll() {
        NPCs.clear();
        placeNPC();
    }
}

