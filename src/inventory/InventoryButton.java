package inventory;

import buttonUi.GameButton;
import main.Game;
import main.GameModel;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class InventoryButton extends GameButton {
    private final BufferedImage sprite;
    private ArrayList<InventoryButtonListener> listeners;

    //private final BufferedImage BUTTON_BG = Load.GetSpriteImg("UI/inventoryButton.png");

    /**
     * Constructor for inventory button
     * @param spriteLoc file path to sprite that corresponds to the item on the button
     * @param x         button x position on the screen
     * @param y         button y position on the screen
     * @param width     buttons' width
     * @param height    buttons' height
     */
    public InventoryButton(String spriteLoc, int x, int y, int width, int height) {
        //super(new ImageIcon(spriteLoc));
        //this.x = x; this.y = y;
        //this.width = width; this.height = height;
        super(x, y, width, height);
        this.sprite = Load.GetSpriteImg(spriteLoc);

        this.listeners = new ArrayList<>();
    }

    /**
     * Draws the button on the screen by drawing the sprite (where the inventory slot is positioned in the UI)
     * @param g Graphics object
     */
    public void draw(Graphics g) {
        // Improper calculations again, but it works for our purposes
        int szx = (int) (1.5f * sprite.getWidth() * GameModel.scale);
        int szy = (int) (1.5f * sprite.getHeight() * GameModel.scale);

        //int szbgx = (int) (3.3f * sprite.getWidth() * Game.scale);
        //int szbgy = (int) (3.3f * sprite.getHeight() * Game.scale);

        //g.drawImage(BUTTON_BG, x - szbgx/2, y - szbgy/2, szbgx, szbgy, null);
        g.drawImage(sprite, x, y, szx, szy, null);
    }

    /**
     * Adds a button listener to the list of listeners
     * @param listener is the new listener to be added
     */
    public void addListeners(InventoryButtonListener listener){
        this.listeners.add(listener);
    }
}
