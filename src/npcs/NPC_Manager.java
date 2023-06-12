package npcs;

import gamestates.Playing;
import objects.GameObject;
import objects.objectsclasses.Key;
import playerclasses.Player;
import utilities.HelpMethods;

import java.awt.*;
import java.util.ArrayList;

public class NPC_Manager {

    private Playing playing;
    private ArrayList<NPC> NPCs;

    public NPC_Manager(Playing playing) {
        this.playing = playing;
        NPCs = playing.getLevelManager().getCurrentLevel().getNPCs();
        placeNPC();
    }

    public void placeNPC() {
        switch (playing.getLevelManager().getCurrentLevelId()) {
            case 0 -> {
                NPCs.add(new NPC(23, 23, 200, 200));
            }
        }
    }

    public void drawNPC(Graphics g) {
        for (NPC n : NPCs) {
            if (n.checkActive()) {
                Player player = playing.getPlayer();
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
    public void resetAll() {
        NPCs.clear();
        placeNPC();
    }
}
