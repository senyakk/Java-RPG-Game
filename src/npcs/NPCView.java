package npcs;

import gamestates.Playing;
import playerclasses.PlayerModel;
import utilities.HelpMethods;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class NPCView {

    private ArrayList<NPC> NPCs;
    private PlayerModel player;

    public NPCView(Playing playing) {
        NPCs = playing.getLevelManager().getCurrentLevel().getNPCs();
        player = playing.getPlayer();
    }

    public void draw(Graphics g) {
        drawNPC(g);
    }

    public void drawNPC(Graphics g) {
        for (NPC n : NPCs) {
            if (n.checkActive()) {
                //PlayerModel player = playing.getPlayer();
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
}
