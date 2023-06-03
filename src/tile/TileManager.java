package tile;

import creatures.Player;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    Tile[] tile;
    private Level levelOne;

    public TileManager() {
        tile = new Tile[10];
        getTileImage();
        levelOne = new Level(loadMap("/maps/world01.txt", 50, 50));
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));

            tile[1]= new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2]= new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));
            tile[2].collision = true;

            tile[3]= new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4]= new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5]= new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public int[][] loadMap(String path, int width, int heigth) {

        int[][]mapTileNumber = new int[width][heigth];
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < width && row < heigth) {
                String line  = br.readLine();
                while (col < width) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[row][col] = num;
                    col++;
                }
                if (col == width) {
                    col = 0;
                    row++;
                }

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTileNumber;
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
        return levelOne;
    }

}