package objects;

import gamestates.Playing;
import locations.LevelManager;
import playerclasses.PlayerModel;
import utilities.HelpMethods;

import java.awt.*;
import java.util.ArrayList;

public class ObjectView {

    private ArrayList<GameObject> gameObjects;
    private PlayerModel player;

    public ObjectView(Playing playing) {
        gameObjects = playing.getLevelManager().getCurrentLevel().getObjects();
        player = playing.getPlayer();
    }

    /**
     * Draw all active objects on a level
     * @param g Graphics class
     */
    public void drawObjects(Graphics g) {
        for (GameObject k : gameObjects) {
            if (k.checkActive()) {
                int screenX = (int) (k.getWorldX() - player.getWorldX() + player.getScreenX());
                int screenY = (int) (k.getWorldY() - player.getWorldY() + player.getScreenY());

                // Draws objects if they are located in the player's window
                if (HelpMethods.checkWorldCamera((int) k.getWorldX(), (int) k.getWorldY(), (int) player.getWorldX(),
                        (int) player.getWorldY(), player.getScreenX(), player.getScreenY())) {
                    // Draw object
                    g.drawImage(k.getImage(), screenX, screenY, k.getWidth(), k.getHeight(), null);
                    // Draw object hitbox
                    drawObjectHitArea(g, k, screenX, screenY);
                }
            }
        }
    }

    /**
     * Draw hitbox
     * @param g graphics object
     * @param otherScreenX x position of the object on the screen
     * @param otherScreenY y position of the object on the screen
     */
    protected void drawObjectHitArea(Graphics g, GameObject k, int otherScreenX, int otherScreenY) {
        g.setColor(Color.ORANGE);
        g.drawRect((int) (otherScreenX + k.getHitArea().x), (int) (otherScreenY + k.getHitArea().y),
                k.getHitArea().width, k.getHitArea().y);
    }
}
