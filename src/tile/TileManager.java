package tile;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    Game game;
    Tile[] tile;
    int[][] mapTileNumber;

    public TileManager(Game game) {
        this.game = game;
        tile = new Tile[10];
        mapTileNumber = new int[Game.maxWorldCol][Game.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage() {
        try {
            tile[0]= new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));

            tile[1]= new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[2]= new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));

            tile[3]= new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4]= new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));

            tile[5]= new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < Game.maxWorldCol && row < Game.maxWorldRow) {
                String line  = br.readLine();
                while (col < Game.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                }
                if (col == Game.maxWorldCol) {
                    col = 0;
                    row++;
                }

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics g) {

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < Game.maxWorldCol && worldRow < Game.maxWorldRow) {

            int tileNum = mapTileNumber[worldCol][worldRow];

            // Code for camera following the player
            int worldX = worldCol * Game.tileSize;
            int worldY = worldRow * Game.tileSize;
            int screenX = worldX - game.getPlayer().getWorldX() + game.getPlayer().getScreenX();
            int screenY = worldY - game.getPlayer().getWorldY() + game.getPlayer().getScreenY();

            // Condition for drawing only the world in the boundaries of the game screen
            if (worldX + Game.tileSize > game.getPlayer().getWorldX() - game.getPlayer().getScreenX() &&
                worldX - Game.tileSize < game.getPlayer().getWorldX() + game.getPlayer().getScreenX() &&
                worldY + Game.tileSize > game.getPlayer().getWorldY() - game.getPlayer().getScreenY() &&
                worldY - Game.tileSize < game.getPlayer().getWorldY() + game.getPlayer().getScreenY()) {

                g.drawImage(tile[tileNum].image, screenX, screenY, Game.tileSize, Game.tileSize, null);
            }
            worldCol++;

            if (worldCol == Game.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}