package locations;

import gamestates.Playing;
import main.GameModel;
import playerclasses.Player;
import utilities.Load;

import java.awt.*;

/**
 * Class that handles Levels
 */
public class LevelManager {


    private Playing playing;
    // Array of tiles in the game
    private Tile[] tile;
    // Array of levels in the game
    private Level[] levels;

    // Current level index
    private int levelInd;

    /**
     * Loads tiles and levels to the game
     */
    public LevelManager(Playing playing) {
        this.playing = playing;
        tile = Load.getTiles();
        levels = Load.getAllLevels();

        levels[1].setBackground(Load.GetSpriteImg("locations/Alchemisthouse.png"));
        levels[2].setBackground(Load.GetSpriteImg("locations/WitchHouse.png"));
        levels[3].setBackground(Load.GetSpriteImg("locations/DragonCemetery.png"));
        levels[4].setBackground(Load.GetSpriteImg("locations/swampLocation.png"));
    }



    /**
     * Draws current level and updates the camera when player moves
     * @param g Graphics object
     * @param player Player object
     */
    public void draw(Graphics g, Player player) {
        Level level = getCurrentLevel();

        if (getCurrentLevelId() == 1 || getCurrentLevelId() == 2) {
            player.lockScreen();
            g.drawImage(level.getBackground(), 0, 0, GameModel.screenWidth, GameModel.screenHeight, null);

            // Following loop is for drawing tile grid
            int y = 0;
            for (int worldRow = 0; worldRow < GameModel.maxTileRow; worldRow++) {
                int x = 0;
                for (int worldCol = 0; worldCol < GameModel.maxTileCol; worldCol++) {
                    // g.drawRect(x, y, Game.tileSize, Game.tileSize);
                    x+= GameModel.tileSize;
                }
                y+= GameModel.tileSize;
            }

        } else if (getCurrentLevelId() == 3 || getCurrentLevelId() == 4) {

            player.unlockScreen();

            for (int worldRow = 0; worldRow < level.getHeight(); worldRow++) {
                for (int worldCol = 0; worldCol < level.getWidth(); worldCol++) {
                    //int tileNum = getCurrentLevel().getTileIndex(worldCol, worldRow);
                    int worldX = worldCol * GameModel.tileSize;
                    int worldY = worldRow * GameModel.tileSize;

                    int screenX = (int) (worldX - player.getWorldX() + player.getScreenX());
                    int screenY = (int) (worldY - player.getWorldY() + player.getScreenY());

                    if (isTileInBounds(screenX, screenY, player)) {
                        if (getCurrentLevelId() == 3 || getCurrentLevelId() == 4) {
                            int bgScreenX = screenX - (screenX % GameModel.tileSize);
                            int bgScreenY = screenY - (screenY % GameModel.tileSize);
                            g.drawImage(level.getBackground(), bgScreenX, bgScreenY,
                                    bgScreenX+GameModel.tileSize, bgScreenY+GameModel.tileSize,
                                    0,0, GameModel.tileSize, GameModel.tileSize, null);
                        } else {
                            g.drawImage(level.getBackground(), 0, 0, GameModel.screenWidth, GameModel.screenHeight, null);
                        }
                    }
                }
            }
        }
        /*
        else if(getCurrentLevelId() == 3 || getCurrentLevelId() == 4){
            player.unlockScreen();
            g.drawImage(level.getBackground(), 0, 0, GameModel.screenWidth, GameModel.screenHeight, null);
            // Following loop is for drawing tile grid
            int y = 0;
            for (int worldRow = 0; worldRow < GameModel.maxTileRow; worldRow++) {
                int x = 0;
                for (int worldCol = 0; worldCol < GameModel.maxTileCol; worldCol++) {
                    // g.drawRect(x, y, Game.tileSize, Game.tileSize);
                    x+= GameModel.tileSize;
                }
                y+= GameModel.tileSize;
            }
        }

         */
        else {

            player.unlockScreen();

            for (int worldRow = 0; worldRow < level.getHeight(); worldRow++) {
                for (int worldCol = 0; worldCol < level.getWidth(); worldCol++) {
                    int tileNum = getCurrentLevel().getTileIndex(worldCol, worldRow);
                    int worldX = worldCol * GameModel.tileSize;
                    int worldY = worldRow * GameModel.tileSize;

                    int screenX = (int) (worldX - player.getWorldX() + player.getScreenX());
                    int screenY = (int) (worldY - player.getWorldY() + player.getScreenY());

                    if (isTileInBounds(screenX, screenY, player)) {
                        g.drawImage(tile[tileNum].image, screenX, screenY, GameModel.tileSize, GameModel.tileSize, null);
                    }
                }
            }
        }

    }

    // Check if a tile is within the bounds of the game screen
    private boolean isTileInBounds(int screenX, int screenY, Player player) {
        return screenX + GameModel.tileSize > player.getScreenX() - GameModel.screenWidth / 2 &&
                screenX - GameModel.tileSize < player.getScreenX() + GameModel.screenWidth / 2 &&
                screenY + GameModel.tileSize > player.getScreenY() - GameModel.screenHeight / 2 &&
                screenY - GameModel.tileSize < player.getScreenY() + GameModel.screenHeight / 2;
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
    public void setStartLevel(int id) {
        levelInd = id;
    }

    public void changeLevel(int id) {
        int origin = getCurrentLevelId();
        levelInd = id;
        playing.movePlayer(origin);
    }

}