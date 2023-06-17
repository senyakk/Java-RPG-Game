package locations;


import main.GameModel;
import npcs.NPC;
import objects.GameObject;
import utilities.Load;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Level {

    private int[][] lvlData;
    private int width;
    private int heigth;
    private BufferedImage background = null;
    private boolean hasBackground = false;
    private ArrayList<GameObject> gameObjects = new ArrayList<>(10);
    private ArrayList<NPC> npcs = new ArrayList<>(5);

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

    public int getHeight() {
        return heigth;
    }

    /**
     * @return List of GameObjects on a level
     */
    public ArrayList<GameObject> getObjects() {
        return gameObjects;
    }

    /**
     * @return List of NPCs on a level
     */
    public ArrayList<NPC> getNPCs() {return npcs;}

    public void setBackground(BufferedImage background) {
        this.background = Load.scaleImage(background, GameModel.screenWidth, GameModel.screenHeight);
        hasBackground = true;
    }
    public BufferedImage getBackground() {
        return background;
    }

    public boolean hasBackground() {
        return hasBackground;
    }


}