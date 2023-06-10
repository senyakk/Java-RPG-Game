package locations;


import objects.GameObject;

import java.util.ArrayList;

public class Level {

    private int[][] lvlData;
    private int width;
    private int heigth;
   // private ArrayList<NPC> npcs = new ArrayList<>(10);
    private ArrayList<GameObject> gameObjects = new ArrayList<>(10);


    public Level(int[][] lvlData) {
        this.lvlData = lvlData;
        this.width = lvlData.length;
        this.heigth = lvlData[0].length;
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

    public int getHeigth() {
        return heigth;
    }

    public void addObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    //public ArrayList<NPC> getNpcs() {
    //    return npcs;
    //}

    public ArrayList<GameObject> getObjects() {
        return gameObjects;
    }
}