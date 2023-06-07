package locations;

import playerclasses.Player;
import main.Game;
import utilities.Load;

import java.awt.*;

public class TileManager {

    Tile[] tile;
    private Level[] levels;
    private int levelInd = 0;


    public TileManager() {
        tile = Load.getTiles();
        levels = Load.getAllLevels();
    }

    public void draw(Graphics g, Player player) {
        Level level = getCurrentLevel();

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < level.getWidth() && worldRow < level.getHeigth()) {

            int tileNum = getCurrentLevel().getTileIndex(worldCol, worldRow);

            int worldX = worldCol * Game.tileSize;
            int worldY = worldRow * Game.tileSize;

            // Code for camera following the player
            int screenX = worldX - player.getWorldX() + player.getScreenX();
            int screenY = worldY - player.getWorldY() + player.getScreenY();

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

    public Tile getTile(int x){
        return tile[x];
    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return levels[levelInd];
    }

}