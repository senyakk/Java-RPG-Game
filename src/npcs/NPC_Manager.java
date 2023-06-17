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

    private Playing playing;
    private ArrayList<NPC> NPCs;

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
        }
        for (NPC n : NPCs) {
            n.addCollisionChecker(collisionChecker);
        }
    }

    public void drawNPC(Graphics g) {
        for (NPC n : NPCs) {
            if (n.checkActive()) {
                PlayerModel player = playing.getPlayer();
                int screenX = (int) (n.getWorldX() - player.getWorldX() + player.getScreenX());
                int screenY = (int) (n.getWorldY() - player.getWorldY() + player.getScreenY());

                // Draws NPCs if they are located in the player's window
                if (HelpMethods.checkWorldCamera((int) n.getWorldX(), (int) n.getWorldY(), (int) player.getWorldX(),
                        (int) player.getWorldY(), player.getScreenX(), player.getScreenY())) {
                    // Draw npc
                    g.drawImage(n.getImage(), screenX, screenY, n.getWidth(), n.getHeight(), null);
                    // Draw npc hitbox
                    n.drawNPCHitArea(g, screenX, screenY);
                }
            }
        }
    }

    public void draw(Graphics g) {
        drawNPC(g);
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

