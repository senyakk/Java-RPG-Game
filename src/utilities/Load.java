package utilities;

import locations.Level;
import locations.Tile;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
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
    public static final String BACKGROUND_IMG = "UI/Startscreen.png";
    public static final String OPTIONS_IMG = "UI/options_background.png";
    public static final String INVENTORY = "UI/InventoryScreenRealReso.png";
    public static final String INVENTORY_BUTTON = "UI/inventoryButton.png";
    public static final String START_BUTTON = "UI/MenuButtons/startButton.png";
    public static final String ACTIVATED_START_BUTTON = "UI/MenuButtons/ActivatedstartButton.png";
    public static final String OPTIONS_BUTTON = "UI/MenuButtons/OptionsButton.png";
    public static final String ACTIVATED_OPTIONS_BUTTON = "UI/MenuButtons/ActivatedOptionsButton.png";
    public static final String QUIT_BUTTON = "UI/MenuButtons/QuitButton.png";
    public static final String ACTIVATED_QUIT_BUTTON = "UI/MenuButtons/activatedQuitButton.png";

    public static final String ALCHEMIST_HOUSE = "locations/Alchemisthouse.png";
    public static final String WITCH_HOUSE = "locations/WitchHouse.png";

    private static Tile[] tile = new Tile[10];

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

        setup(0, "002", false);
        setup(1, "032", true);
        setup(2, "019", false);
        setup(3, "017", false);
        setup(4, "016", true);
        setup(5, "003", false);
        setup(6, "033", true);

        return tile;
    }

    private static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics g = scaledImage.createGraphics();
        g.drawImage(original, 0, 0, width, height, null);
        g.dispose();

        return scaledImage;
    }

    public static void setup(int index, String imagePath, boolean collision) {

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Load.class.getResourceAsStream("/tiles/Set1/" + imagePath + ".png"));
            tile[index].image = scaleImage(tile[index].image, Game.tileSize, Game.tileSize);
            tile[index].collision = collision;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
