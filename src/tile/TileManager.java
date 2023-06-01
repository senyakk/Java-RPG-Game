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
        mapTileNumber = new int[Game.maxTileCol][Game.maxTileRow];

        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage() {
        try {
            tile[0]= new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));

            tile[1]= new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));

            tile[2]= new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[3]= new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[4]= new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));

            tile[5]= new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));

            tile[6]= new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water02.png"));
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

            while (col < Game.maxTileCol && row < Game.maxTileRow) {
                String line  = br.readLine();
                while (col < Game.maxTileCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                }
                if (col == Game.maxTileCol) {
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

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < Game.maxTileCol && row < Game.maxTileRow) {

            int tileNum = mapTileNumber[col][row];
            g.drawImage(tile[tileNum].image, x, y, Game.tileSize, Game.tileSize, null);
            col++;
            x+=Game.tileSize;

            if (col == Game.maxTileCol) {
                col = 0;
                x = 0;
                row++;
                y += Game.tileSize;
            }
        }
    }
}