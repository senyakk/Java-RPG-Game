package utilities;

import locations.Level;
import locations.Tile;
import main.GameModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class Load {

    public static final String INVENTORY = "UI/InventoryScreenRealReso.png";

    private static Tile[] tile = new Tile[15];

    /**
     * Load sprite images
     * @param name name of the sprite image
     * @return Buffered Image
     */
    public static BufferedImage GetSpriteImg(String name) {
        BufferedImage image = null;
        InputStream sprite = Load.class.getResourceAsStream("/" + name);
        try {
            image = ImageIO.read(sprite);
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sprite.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    /**
     * Loads all level from maps folder
     * @return array of Levels
     */
    public static Level[] getAllLevels() {
        URL url = Load.class.getResource("/maps");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File[] files = file.listFiles();
        File[] filesSorted = new File[files.length];

        for (int i = 0; i < files.length; i++)
            for (int j = 0; j < files.length; j++) {
                if (files[j].getName().equals((i + 1) + ".txt"))
                    filesSorted[i] = files[j];
            }

        Level[] levels = new Level[filesSorted.length];
        for (int i = 0; i < levels.length; i++) {
            levels[i] = new Level(loadMap(filesSorted[i]));
        }
        return levels;
    }

    /**
     * Loads level map
     * @param file Map txt file
     * @return array of tiles
     */
    private static int[][] loadMap(File file) {

        int width = getWidth(file);
        int height = getHeight(file);

        int[][] mapTileNumber = new int[height][width];
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            int col = 0;
            int row = 0;

            while (row < height) {
                String line = br.readLine();
                String[] numbers = line.split(" ");

                for (col = 0; col < width; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[row][col] = num;
                }

                row++;
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTileNumber;
    }

    /**
     * Gets height of a level map
     * @param file Map txt file
     * @return number of rows
     */
    private static int getHeight(File file) {
        int height = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                height++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return height;
    }

    /**
     * Gets width of a level map
     * @param file Map txt file
     * @return number of columns
     */
    private static int getWidth(File file) {
        int width = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null) {
                String[] columns = line.split(" ");
                width = columns.length;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return width;
    }

    /**
     * Loads tiles
     * @return array of game tiles
     */
    public static Tile[] getTiles() {

        setup(0, "002", false, "grass");
        setup(1, "032", true, "wall");
        setup(2, "019", true, "water");
        setup(3, "017", false, "dirt");
        setup(4, "016", true, "tree");
        setup(5, "003", false, "sand");
        setup(6, "033", false, "house");
        setup(7, "transparent", false, "transparent");
        setup(8, "transparent", true, "transparentCol");
        setup(9, "transparent", true, "transparentExit");
        setup(10, "036", true, "stairs");
        setup(11, "021", false, "pond");
        setup(12, "035", false, "table");
        return tile;
    }

    /**
     * Loads tile image, scales it, and sets a collision
     * @param index tile index
     * @param imagePath path to the tile in resource folder
     * @param collision collision on/off
     * @param name name of the tile
     */
    public static void setup(int index, String imagePath, boolean collision, String name) {

        try {
            tile[index] = new Tile(name);
            tile[index].image = ImageIO.read(Load.class.getResourceAsStream("/tiles/" + imagePath + ".png"));
            tile[index].image = scaleImage(tile[index].image, GameModel.tileSize, GameModel.tileSize);
            tile[index].collision = collision;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Scales tile image
     * @param original tile image
     * @param width tile width
     * @param height tile height
     * @return scaled Image
     */
    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics g = scaledImage.createGraphics();
        g.drawImage(original, 0, 0, width, height, null);
        g.dispose();

        return scaledImage;
    }


}
