package inventory.viewfiles;

import buttonUi.GameButton;
import main.GameModel;
import utilities.Load;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * A button for inventory slots in the View
 * @author Cata Mihit
 */
public class InventoryButton extends GameButton {
    private final BufferedImage sprite;

    /**
     * Constructor for inventory button
     * @param spriteLoc file path to sprite that corresponds to the item on the button
     * @param x         button x position on the screen
     * @param y         button y position on the screen
     * @param width     buttons' width
     * @param height    buttons' height
     */
    public InventoryButton(String spriteLoc, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.sprite = Load.GetSpriteImg(spriteLoc);
    }

    /**
     * Draws the button on the screen by drawing the sprite (where the inventory slot is positioned in the UI)
     * @param g Graphics object
     */
    public void draw(Graphics g) {
        int szx = (int) (1.5f * sprite.getWidth() * GameModel.scale);
        int szy = (int) (1.5f * sprite.getHeight() * GameModel.scale);
        g.drawImage(sprite, x, y, szx, szy, null);
    }

    /**
     * Checks whether a mouse event occurs within the on-screen bounds of the button
     * @param e is the mouse event
     * @return true if it is, false otherwises
     */
    public boolean isInBorder(MouseEvent e) {
        return this.getBounds().contains(e.getX(), e.getY());
    }
}
