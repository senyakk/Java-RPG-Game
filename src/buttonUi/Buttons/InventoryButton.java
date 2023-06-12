package buttonUi.Buttons;

import buttonUi.GameButton;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.UI.MenuButtons.B_HEIGHT;
import static utilities.Constants.UI.MenuButtons.B_WIDTH;

public class InventoryButton extends GameButton {

    private BufferedImage image = Load.GetSpriteImg("UI/inventoryButton.png");
    /**
     * Parent class for the buttons in game
     *
     * @param x      button x position on the screen
     * @param y      button y position on the screen
     * @param width  buttons' width
     * @param height buttons' height
     */
    public InventoryButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, 50, 50, null);
    }

}
