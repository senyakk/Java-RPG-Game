package objects;

import gamestates.Playing;
import objects.objectsclasses.Boots;
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
               gameObjects.add(new Key(23, 23));
               gameObjects.add(new Boots(12, 32));
            }
        }
    }

    /**
     * Update the status of objects
     */
    public void update() {
        for (GameObject k : gameObjects) {
            if (k.checkActive()) {
                k.update();
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