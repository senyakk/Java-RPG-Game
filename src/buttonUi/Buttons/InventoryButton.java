package buttonUi.Buttons;

import buttonUi.GameButton;
import gamestates.Gamestate;
import main.Game;
import utilities.Load;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.UI.MenuButtons.B_HEIGHT;
import static utilities.Constants.UI.MenuButtons.B_WIDTH;

public class InventoryButton extends GameButton {
    //private final BufferedImage BUTTON_BG = Load.GetSpriteImg("UI/inventoryButton.png");
    private BufferedImage sprite;
    int x, y, width, height;

    /**
     * Parent class for the buttons in game
     *
     * @param x      button x position on the screen
     * @param y      button y position on the screen
     * @param width  buttons' width
     * @param height buttons' height
     */

    public InventoryButton(String spriteLoc, int x, int y, int width, int height) {
        //super(new ImageIcon(spriteLoc));
        super(x, y, width, height);
        this.sprite = Load.GetSpriteImg(spriteLoc);
        this.x = x; this.y = y;
        this.width = width; this.height = height;
    }

    public void draw(Graphics g) {
        int szx = (int) (1.5f * sprite.getWidth() * Game.scale);
        int szy = (int) (1.5f * sprite.getHeight() * Game.scale);

        //int szbgx = (int) (3.3f * sprite.getWidth() * Game.scale);
        //int szbgy = (int) (3.3f * sprite.getHeight() * Game.scale);

        //g.drawImage(BUTTON_BG, x - szbgx/2, y - szbgy/2, szbgx, szbgy, null);
        g.drawImage(sprite, x, y, szx, szy, null);
    }
}
