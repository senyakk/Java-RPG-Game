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

    private static Tile[] tile = new Tile[60];

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
                String[] columns = line.split("\\s+");
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
        setup(9, "transparent", true, "transparentExitProfsHouse");
        setup(10, "078", true, "witchhousebottom");
        setup(11, "080", true, "gate");

        //Dragon cemetery tiles
        setup(12, "039", true, "stone");
        setup(13, "040", false, "darkGrass");
        setup(14, "041", true, "tree");
        setup(15, "042", true, "tomb");
        setup(16, "043", true, "statue");
        setup(17, "044", false, "stonePath");
        setup(18, "045", true, "fenceSide");
        setup(19, "046", true, "fenceUp");
        setup(20, "047", true, "fontain");
        setup(21, "048", true, "DragonDownRight");
        setup(22, "049", true, "DragonDownLeft");
        setup(23, "050", true, "DragonUpRight");
        setup(24, "051", true, "DragonUpLeft");
        setup(25, "052", true, "houseDown");
        setup(26, "053", true, "houseUp");

        // swamp tiles
        setup(27, "054", true, "water");
        setup(28, "055", true, "CUL"); //corner upper left
        setup(29, "056", true, "CDL"); // corner down left
        setup(30, "057", true, "CDR");
        setup(31, "058", true, "CUR");
        setup(32, "059", true, "downR");
        setup(33, "060", true, "downL");
        setup(34, "061", true, "upperR");
        setup(35, "062", true, "upperL");
        setup(36, "063", true, "leftSide");
        setup(37, "064", true, "rightSide");
        setup(38, "065", true, "down");
        setup(39, "066", true, "up");
        setup(40, "067", false, "swampGrass");
        setup(41, "068", true, "swampTree");
        setup(42, "069", true, "bush");
        setup(43, "070", false, "orangeFlower");
        setup(44, "071", false, "bridgeLeft");
        setup(45, "073", false, "bridgeUp");
        setup(46, "074", false, "bridgeDown");
        setup(47, "075", false, "pinkFlower");
        setup(48, "072", false, "bridgeRight");
        setup(49, "076", false, "bridgeHorizontal");
        setup(50, "077", false, "bridgeVertical");
        setup(51, "078", true, "witchHouseDoor");
        setup(52, "079", true, "witchHouseRoof");
        setup(53, "transparent", true, "transparentExitWitchHouse");
        setup(54, "081", true, "TreeDoor");
        setup(55, "082", true, "treeLeaves");

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
