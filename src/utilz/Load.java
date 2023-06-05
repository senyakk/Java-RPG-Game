package utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Load {

    public static final String PLAYER_IMAGE = "characters/player_sprites.png";
    public static final String MENU_BACKGROUND = "UI/menu_background.png";
    public static final String MENU_BUTTONS = "UI/button_atlas.png";
    public static final String PAUSE = "UI/pause_menu.png";
    public static final String URM = "UI/urm_buttons.png";
    public static final String SOUND = "UI/sound_button.png";
    public static final String VOLUME = "UI/volume_buttons.png";
    public static final String BACKGROUND_IMG = "UI/Path 1.png";

    public static BufferedImage GetSpriteImg(String name) {
        BufferedImage image = null;
        InputStream playerSprites = Load.class.getResourceAsStream("/" + name);
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
