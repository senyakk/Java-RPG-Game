package objects;

import gamestates.Playing;
import objects.objectsclasses.Boots;
import objects.objectsclasses.Firefly;
import objects.objectsclasses.Key;
import playerclasses.PlayerModel;
import utilities.HelpMethods;


import java.awt.*;
import java.util.ArrayList;

public class ObjectManager {

    // Game Playing class
    private Playing playing;
    // Lists of GameObjects
    private ArrayList<GameObject> gameObjects;

    /**
     * Creates object manager that operates objects in a given level
     * @param playing Playing state class
     */
    public ObjectManager(Playing playing) {
        this.playing = playing;
        gameObjects = playing.getLevelManager().getCurrentLevel().getObjects();
        placeObject();
    }

    /**
     * Adds objects in a given level
     */
    public void placeObject() {
        switch (playing.getLevelManager().getCurrentLevelId()) {
            case 0 -> {
               gameObjects.add(new Key(23, 7));
               gameObjects.add(new Boots(12, 32));
            }
            case 7 -> {
                gameObjects.add(new Firefly(34, 34));
            }
        }
    }

    /**
     * Update the status of objects
     */
    public void update() {
        gameObjects = playing.getLevelManager().getCurrentLevel().getObjects();
        for (GameObject k : gameObjects) {
            if (k.checkActive()) {
                k.update();
                if (!k.isActive)
                    k = null;
            }
        }
    }

    /**
     * Resets objects in a level
     */
    public void resetAll() {
        gameObjects.clear();
        placeObject();
    }

}