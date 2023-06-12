package locations;


import npcs.NPC;
import objects.GameObject;

import java.util.ArrayList;

public class Level {

    private int[][] lvlData;
    private int width;
    private int height;
    private ArrayList<GameObject> gameObjects = new ArrayList<>(10);

    private ArrayList<NPC> npcs = new ArrayList<NPC>(10);

    public Level(int[][] lvlData) {
        this.lvlData = lvlData;
        this.width = lvlData.length;
        this.height = lvlData[0].length;
    }

    public int getTileIndex(int x, int y) {
        return lvlData[y][x];
    }

    public int[][] getLvlData() {
        return lvlData;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * @return List of GameObjects on a level
     */
    public ArrayList<GameObject> getObjects() {
        return gameObjects;
    }

    public ArrayList<NPC> getNPCs() {return npcs;}
}