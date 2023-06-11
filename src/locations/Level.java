package locations;


import objects.GameObject;

import java.util.ArrayList;

public class Level {

    private int[][] lvlData;
    private int width;
    private int heigth;
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

    /**
     * @return List of GameObjects on a level
     */
    public ArrayList<GameObject> getObjects() {
        return gameObjects;
    }
}