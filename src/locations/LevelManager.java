package locations;

import playerclasses.Player;
import main.Game;
import utilities.Load;

import java.awt.*;

/**
 * Class that handles Levels
 */
public class LevelManager {

    // Array of tiles in the game
    private Tile[] tile;
    // Array of levels in the game
    private Level[] levels;
    // Current level index
    private int levelInd = 0;

    /**
     * Loads tiles and levels to the game
     */
    public LevelManager() {
        tile = Load.getTiles();
        levels = Load.getAllLevels();
    }

    /**
     * Draws current level and updates the camera when player moves
     * @param g Graphics object
     * @param player Player object
     */
    public void draw(Graphics g, Player player) {
        Level level = getCurrentLevel();

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < level.getWidth() && worldRow < level.getHeight()) {

            int tileNum = getCurrentLevel().getTileIndex(worldCol, worldRow);

            int worldX = worldCol * Game.tileSize;
            int worldY = worldRow * Game.tileSize;

            // Code for camera following the player
            int screenX = (int) (worldX - player.getWorldX() + player.getScreenX());
            int screenY = (int) (worldY - player.getWorldY() + player.getScreenY());

            // Condition for drawing only the world in the boundaries of the game screen
            if (worldX + Game.tileSize > player.getWorldX() - player.getScreenX() &&
                    worldX - Game.tileSize < player.getWorldX() + player.getScreenX() &&
                    worldY + Game.tileSize > player.getWorldY() - player.getScreenY() &&
                    worldY - Game.tileSize < player.getWorldY() + player.getScreenY()) {

                g.drawImage(tile[tileNum].image, screenX, screenY, Game.tileSize, Game.tileSize, null);
            }

            worldCol++;

            if (worldCol == level.getWidth()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    /**
     * Returns a tile with a given index
     * @param x index of a tile
     * @return Tile
     */
    public Tile getTile(int x){
        return tile[x];
    }

    public void update() {

    }

    /**
     * Returns current Level object
     * @return Level
     */
    public Level getCurrentLevel() {
        return levels[levelInd];
    }

    /**
     * Returns id of current level
     * @return index
     */
    public int getCurrentLevelId() {
        return levelInd;
    }

    /**
     * Sets current level id
     * @param id index
     */
    public void setCurrentLevel(int id) {
        levelInd = id;
    }

}