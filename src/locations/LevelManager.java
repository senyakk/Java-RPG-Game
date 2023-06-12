package locations;

import playerclasses.Player;
import main.Game;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class that handles Levels
 */
public class LevelManager {

    // Array of tiles in the game
    private Tile[] tile;
    // Array of levels in the game
    private Level[] levels;

    // Current level index
    private int levelInd;

    /**
     * Loads tiles and levels to the game
     */
    public LevelManager() {
        tile = Load.getTiles();
        levels = Load.getAllLevels();

        levels[1].setBackground(Load.GetSpriteImg("locations/Alchemisthouse.png"));
        levels[2].setBackground(Load.GetSpriteImg("locations/WitchHouse.png"));
    }



    /**
     * Draws current level and updates the camera when player moves
     * @param g Graphics object
     * @param player Player object
     */
    public void draw(Graphics g, Player player) {
        Level level = getCurrentLevel();

        if (level.hasBackground()) {
            player.lockScreen();
            g.drawImage(level.getBackground(), 0, 0, Game.screenWidth, Game.screenHeight, null);

            int y = 0;

            for (int worldRow = 0; worldRow < Game.maxTileRow; worldRow++) {
                int x = 0;
                for (int worldCol = 0; worldCol < Game.maxTileCol; worldCol++) {
                    g.drawRect(x, y, Game.tileSize, Game.tileSize);
                    x+= Game.tileSize;
                }
                y+= Game.tileSize;
            }

        } else {

            player.unlockScreen();

            for (int worldRow = 0; worldRow < level.getHeight(); worldRow++) {
                for (int worldCol = 0; worldCol < level.getWidth(); worldCol++) {
                    int tileNum = getCurrentLevel().getTileIndex(worldCol, worldRow);
                    int worldX = worldCol * Game.tileSize;
                    int worldY = worldRow * Game.tileSize;

                    int screenX = (int) (worldX - player.getWorldX() + player.getScreenX());
                    int screenY = (int) (worldY - player.getWorldY() + player.getScreenY());

                    if (isTileInBounds(screenX, screenY, player)) {
                        g.drawImage(tile[tileNum].image, screenX, screenY, Game.tileSize, Game.tileSize, null);
                    }
                }
            }
        }

    }

    // Check if a tile is within the bounds of the game screen
    private boolean isTileInBounds(int screenX, int screenY, Player player) {
        return screenX + Game.tileSize > player.getScreenX() - Game.screenWidth / 2 &&
                screenX - Game.tileSize < player.getScreenX() + Game.screenWidth / 2 &&
                screenY + Game.tileSize > player.getScreenY() - Game.screenHeight / 2 &&
                screenY - Game.tileSize < player.getScreenY() + Game.screenHeight / 2;
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