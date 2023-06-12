package buttonUi.Buttons;

import buttonUi.GameButton;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.UI.MenuButtons.B_HEIGHT;
import static utilities.Constants.UI.MenuButtons.B_WIDTH;

public class InventoryButton extends GameButton {
    private final BufferedImage BUTTON_BG = Load.GetSpriteImg("UI/inventoryButton.png");
    private BufferedImage sprite;

    /**
     * Parent class for the buttons in game
     *
     * @param x      button x position on the screen
     * @param y      button y position on the screen
     * @param width  buttons' width
     * @param height buttons' height
     */

    public InventoryButton(String spriteLoc, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.sprite = Load.GetSpriteImg(spriteLoc);
    }

    public void draw(Graphics g) {
        g.drawImage(BUTTON_BG, x, y, 100, 100, null);
        g.drawImage(sprite, x, y, 100, 100, null);
    }

}
