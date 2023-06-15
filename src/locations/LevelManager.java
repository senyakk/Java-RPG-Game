package locations;

import gamestates.Playing;
import main.GameModel;
import playerclasses.PlayerModel;
import utilities.Load;

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

    // Check if a tile is within the bounds of the game screen
    boolean isTileInBounds(int screenX, int screenY, PlayerModel player) {
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

    public Tile[] getTiles() {
        return tile;
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