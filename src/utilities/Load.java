package utilities;

import tile.Level;
import tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class Load {

    public static final String PLAYER_IMAGE = "characters/player_sprites.png";
    public static final String MENU_BACKGROUND = "UI/menu_background.png";
    public static final String MENU_BUTTONS = "UI/button_atlas.png";
    public static final String PAUSE = "UI/pause_menu.png";
    public static final String URM = "UI/urm_buttons.png";
    public static final String SOUND = "UI/sound_button.png";
    public static final String VOLUME = "UI/volume_buttons.png";
    public static final String BACKGROUND_IMG = "UI/Path 1.png";
    public static final String OPTIONS_IMG = "UI/options_background.png";
    public static final String INVENTORY = "UI/InventoryScreenRealReso.png";
    public static final String INVENTORY_BUTTON = "UI/inventoryButton.png";

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

    public static Tile[] getTiles() {
        Tile[] tile = new Tile[10];

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Load.class.getResourceAsStream("/tiles/Set1/002.png"));

            tile[1]= new Tile();
            tile[1].image = ImageIO.read(Load.class.getResourceAsStream("/tiles/Set1/032.png"));
            tile[1].collision = true;

            tile[2]= new Tile();
            tile[2].image = ImageIO.read(Load.class.getResourceAsStream("/tiles/Set1/019.png"));
            tile[2].collision = true;

            tile[3]= new Tile();
            tile[3].image = ImageIO.read(Load.class.getResourceAsStream("/tiles/Set1/003.png"));

            tile[4]= new Tile();
            tile[4].image = ImageIO.read(Load.class.getResourceAsStream("/tiles/Set1/016.png"));
            tile[4].collision = true;

            tile[5]= new Tile();
            tile[5].image = ImageIO.read(Load.class.getResourceAsStream("/tiles/Set1/017.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return tile;
    }
}
