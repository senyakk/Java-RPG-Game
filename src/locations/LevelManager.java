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

        setStartLevel(0);
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

    public void movePlayer(int origin) {
        PlayerModel player = playing.getPlayer();
        switch (getCurrentLevelId()) {
            case 0 -> { //go to with coords in hometown
                if (origin == 1) // when from professors house
                    player.setCoordinates(22, 19);
                else if (origin == 0) // When from hometown
                    player.setCoordinates(22, 21);
                if (origin == 8) // when from swamp
                    player.setCoordinates(22, 21);
                else if (origin == 7) // When from dragon cemetery
                    player.setCoordinates(22, 21);
                else if (origin == 9 ) // When from forrest
                    player.setCoordinates(38, 10);
            }
            case 1 -> player.setCoordinates(3, 5); // Go to entrance in professors house
            case 2 -> player.setCoordinates(4, 5); // Go to entrance in Witch house
            case 7 -> player.setCoordinates(10, 23); // spawn of dragon cemetery
            case 8 -> { // go to coords in swamp
                if (origin == 7) // when from dragon Cemetery
                    player.setCoordinates(34, 23); // swamp -> 34,23 is real spawn
                if (origin == 2) // when from witch house
                    player.setCoordinates(34, 29); // spawn outside the door
            }
            case 9 -> player.setCoordinates(36, 14); // spawn of dragon cemetery
        }
        playing.getCollisionChecker().updateLevel();
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
        movePlayer(origin);
        playing.getGameModel().getAudioPlayer().setLevelSong(id);
    }

}