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
        NPCs = playing.getLevelManager().getCurrentLevel().getNPCs();
        placeNPC();
    }

    public void placeNPC() {
        switch (playing.getLevelManager().getCurrentLevelId()) {
            case 0 -> {
                NPCs.add(new BabyDragon(19 ,19));
            }
            case 1 -> {
                NPCs.add(new Professor(19 ,19));
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

    public static BufferedImage GetSpriteImg(String name) {
        BufferedImage image = null;
        InputStream sprites = Load.class.getResourceAsStream("/" + name);
        try {
            image = ImageIO.read(sprites);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sprites.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
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

