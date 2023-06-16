package inventory;

import gamestates.Playing;
import main.GameModel;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static utilities.Constants.PlayerConstants.*;

public class InventoryManager {
    // Playing state to which the manager is connected
    private final Playing playing;

    // Saver/Loader for Inventory object
    private InventoryIO inventoryIO;
    private final Inventory inventory;

    // UI-Related elements
    private final int INVENTORY_ROWS = 3;
    private final int INVENTORY_COLS = 12;
    private final InventoryButton[] inventoryButtons;
    private int invWidth, invHeight, invX, invY;
    private BufferedImage inventoryImage;

    /**
     * Creates an inventory (new or from saves) and prepares its UI elements
     * @param playing the game state that controls the inventory manager
     */
    public InventoryManager(Playing playing) {
        this.playing = playing;

        int playerClass = this.playing.getPlayer().getPlayerClassAsInt();
        Item playerWeapon = null;
        switch (playerClass){
            case WARRIOR -> playerWeapon = new GenericItem("1");
            case ARCHER -> playerWeapon = new GenericItem("2");
            case BARD -> playerWeapon = new GenericItem("3");
            default -> playerWeapon = new GenericItem("0");
        }

        this.inventoryIO = new InventoryIO();
        inventoryIO.saveInventory(new Inventory(playerWeapon)); // TEMPORARY
        this.inventory = inventoryIO.loadInventory();
        inventory.addItem(new GenericItem("1"));
        inventory.addItem(new GenericItem("2"));
        //inventoryIO.saveInventory(inventory);

        this.inventory.addListeners(new InventoryPropertyListener(this));

        loadInventoryImage();
        this.inventoryButtons = new InventoryButton[INVENTORY_ROWS * INVENTORY_COLS];
        updateButtons();
    }

    /**
     * Recreates all buttons on UI based on the list of items in the inventory
     */
    public void updateButtons(){
        ArrayList<Item> currentItemList = inventory.getItemList();

        // Improper position calculations, more exact method needed
        int pos0x = (int) (invX + 55 * GameModel.scale);
        int pos0y = (int) (invY + 85 * GameModel.scale);
        int spriteSize = 16;
        int skip = (int) (8 * GameModel.scale);

        int index = 0;
        int posx, posy;

        for (int row = 0; row < INVENTORY_ROWS; row++){
            for (int col = 0; col < INVENTORY_COLS; col++){
                Item currentItem = currentItemList.get(row * INVENTORY_COLS + col);

                // Also inexact but works for our purposes
                posx = pos0x + col*((int)(spriteSize*GameModel.scale) + skip);
                posy = pos0y + row*((int)(spriteSize*GameModel.scale) + skip);

                InventoryButton button = new InventoryButton(currentItem.getSpriteLoc(), posx, posy, spriteSize, spriteSize);
                button.addListeners(new InventoryButtonListener(this.inventory, index, button));
                this.inventoryButtons[index] = button;
                index++;
            }
        }
    }

    /**
     * Finds the sprite for the inventory panel
     */
    private void loadInventoryImage() {
        this.inventoryImage = Load.GetSpriteImg(Load.INVENTORY);

        this.invWidth = (int) (inventoryImage.getWidth() * GameModel.scale);
        this.invHeight = (int) (inventoryImage.getHeight() * GameModel.scale);
        this.invX = (GameModel.screenWidth / 2 - invWidth / 2);
        this.invY = (int) (GameModel.screenHeight / 1.25 - invHeight / 2);
    }

    /**
     * Draws the inventory panel image along with all the inventory buttons
     * @param g Graphics object
     */
    public void draw(Graphics g) {
        g.drawImage(inventoryImage, invX, invY, invWidth, invHeight, null);
        for(InventoryButton button : inventoryButtons) {
            button.draw(g);
        }
    }

    public void resetAll(){

    }

    public void update(){

    }
}
