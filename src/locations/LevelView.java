package locations;

import main.GameModel;
import playerclasses.PlayerModel;

import java.awt.*;

public class LevelView {

    /**
     * Draws current level and updates the camera when player moves
     * @param g Graphics object
     * @param player Player object
     */
    public void draw(Graphics g, PlayerModel player, LevelManager levelManager) {
        Level level = levelManager.getCurrentLevel();

        if (levelManager.getCurrentLevelId() == 1 || levelManager.getCurrentLevelId() == 2) {
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

        } else if (levelManager.getCurrentLevelId() == 3 || levelManager.getCurrentLevelId() == 4) {

            player.unlockScreen();

            for (int worldRow = 0; worldRow < level.getHeight(); worldRow++) {
                for (int worldCol = 0; worldCol < level.getWidth(); worldCol++) {
                    //int tileNum = getCurrentLevel().getTileIndex(worldCol, worldRow);
                    int worldX = worldCol * GameModel.tileSize;
                    int worldY = worldRow * GameModel.tileSize;

                    int screenX = (int) (worldX - player.getWorldX() + player.getScreenX());
                    int screenY = (int) (worldY - player.getWorldY() + player.getScreenY());

                    if (levelManager.isTileInBounds(screenX, screenY, player)) {
                        if (levelManager.getCurrentLevelId() == 3 || levelManager.getCurrentLevelId() == 4) {
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

