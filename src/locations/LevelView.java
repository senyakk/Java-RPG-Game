package locations;

import gamestates.Playing;
import main.GameModel;
import playerclasses.PlayerModel;

import java.awt.*;

public class LevelView {

    PlayerModel player;
    LevelManager levelManager;

    public LevelView(Playing playing) {
        levelManager = playing.getLevelManager();
        player = playing.getPlayer();
    }

    /**
     * Draws current level and updates the camera when player moves.
     * If level has background (house), locks the camera following the player and draws the background
     * If player is not in the house, follow the player and draws the tiles of the world
     * @param g Graphics object
     */
    public void draw(Graphics g) {
        Level level = levelManager.getCurrentLevel();

        if (level.hasBackground()) {
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

        } else {

            player.unlockScreen();

            for (int worldRow = 0; worldRow < level.getHeight(); worldRow++) {
                for (int worldCol = 0; worldCol < level.getWidth(); worldCol++) {
                    int tileNum = levelManager.getCurrentLevel().getTileIndex(worldCol, worldRow);
                    int worldX = worldCol * GameModel.tileSize;
                    int worldY = worldRow * GameModel.tileSize;

                    int screenX = (int) (worldX - player.getWorldX() + player.getScreenX());
                    int screenY = (int) (worldY - player.getWorldY() + player.getScreenY());

                    if (levelManager.isTileInBounds(screenX, screenY, player)) {
                        g.drawImage(levelManager.getTiles()[tileNum].image, screenX, screenY,
                                GameModel.tileSize, GameModel.tileSize, null);
                    }
                }
            }
        }
    }
}

