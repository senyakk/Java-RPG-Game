package equipment;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Sword extends Object{

    public Sword(GamePanel gp) throws IOException {
        super(gp);

        name = "Sword";
        image = ImageIO.read(getClass().getResource("/objects/sword"));

        attack = 1;
    }
}
