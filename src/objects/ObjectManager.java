package objects;

import gamestates.Playing;
import objects.objectsclasses.Key;
import playerclasses.Player;
import utilities.HelpMethods;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ObjectManager {

    private Playing playing;
    private ArrayList<GameObject> gameObjects;
    private BufferedImage key;

    public ObjectManager(Playing playing) {
        this.playing = playing;
        gameObjects = playing.getLevelManager().getCurrentLevel().getObjects();
        placeObject();
    }

    public void placeObject() {
        switch (playing.getLevelManager().getCurrentLevelId()) {
            case 0 -> {
               gameObjects.add(new Key(23, 23));
            }
        }
    }

    public void update() {
        for (GameObject k : gameObjects) {
            if (k.checkActive()) {
                k.update();
            }
        }
    }

    public void drawObjects(Graphics g) {
        for (GameObject k : gameObjects) {
            if (k.checkActive()) {
                Player player = playing.getPlayer();
                int screenX = (int) (k.getWorldX() - player.getWorldX() + player.getScreenX());
                int screenY = (int) (k.getWorldY() - player.getWorldY() + player.getScreenY());

                if (HelpMethods.checkWorldCamera((int) k.getWorldX(), (int) k.getWorldY(), (int) player.getWorldX(),
                        (int) player.getWorldY(), player.getScreenX(), player.getScreenY())) {
                    g.drawImage(k.getImage(), screenX, screenY, k.getWidth(), k.getHeight(), null);
                    k.drawObjectHitArea(g, screenX, screenY);
                }
            }
        }
    }


    public void resetAll() {
        gameObjects.clear();
        placeObject();
    }
}