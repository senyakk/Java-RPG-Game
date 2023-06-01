package utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class SaveLoad {

    public static final String PLAYER_IMAGE = "player_sprites.jpeg";

    public static BufferedImage GetSpriteImg(String name) {
        BufferedImage image = null;
        InputStream playerSprites = SaveLoad.class.getResourceAsStream("/" + name);
        try {
            image = ImageIO.read(playerSprites);
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                playerSprites.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }
}