package inventory.viewfiles;

import gamestates.Playing;
import inventory.controllerfiles.InventoryPropertyListener;
import inventory.modelfiles.*;
import main.GameModel;
import playerclasses.PlayerModel;
import utilities.Load;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static utilities.Constants.PlayerConstants.*;

/**
 * Central class for inventory handling. Generates and displays the inventory UI. Upon creation, it creates the
 * appropriate model Inventory object by I/O.
 * @author Cata Mihit
 */
public class InventoryManager {

    // Playing state to which the manager is connected
    private final Playing playing;

    // Saver/Loader for Inventory object
    private InventoryIO inventoryIO;
    private Inventory inventory;

    // UI-Related elements
    private final int INVENTORY_ROWS = 3;
    private final int INVENTORY_COLS = 12;
    private final ArrayList<InventoryButton> inventoryButtons; //InventoryButton[] inventoryButtons;
    private int invWidth, invHeight, invX, invY;
    private BufferedImage inventoryImage;

    /**
     * Determines what weapon the player should own (given by default in inventory)
     * @param player is the player who has this inventory
     * @return the weapon item corresponding to their class, or an empty item if something goes wrong
     */
    private Item setupWeapon(PlayerModel player){
        int playerClass = player.getPlayerClassAsInt();
        if (playerClass == WARRIOR) return new WeaponItem("1", 2.0f);
        if (playerClass == ARCHER) return new WeaponItem("2", 3.0f);
        if (playerClass == BARD) return new WeaponItem("4", 1.0f);
        return new GenericItem("0");
    }

    /**
     * Creates an inventory (new or from saves) and prepares its UI elements
     * @param playing the game state that controls the inventory manager
     */
    public InventoryManager(Playing playing) {
        this.playing = playing;

        Item playerWeapon = setupWeapon(this.playing.getPlayer());

        // Test code -> save/load works just isn't connected to the UI
        this.inventoryIO = new InventoryIO(new Inventory(playerWeapon));
        inventoryIO.saveInventory();
        this.inventory = inventoryIO.loadInventory();
        //this.inventory = new Inventory(playerWeapon);
        inventory.addItem(new GenericItem("3"));
        inventory.addItem(new GenericItem("5"));

        this.inventory.addListeners(new InventoryPropertyListener(this));

        loadInventoryImage();
        this.inventoryButtons = new ArrayList<>(INVENTORY_ROWS * INVENTORY_COLS);
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

                InventoryButton button = new InventoryButton(
                    currentItem.getSpriteLoc(),
                    posx, posy,
                    2*(int)(spriteSize*GameModel.scale),
                    2*(int)(spriteSize*GameModel.scale)
                );

                try {
                    this.inventoryButtons.set(index, button);
                } catch (IndexOutOfBoundsException e) {
                    this.inventoryButtons.add(index, button);
                }

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
        this.inventory.reset();
    }

    public void update(){
        // ?
    }

    /*
        Event handling -> current button implementation does not allow for
        an individual button's listener to handle events, so the inventoryManager
        also takes the role of a controller class for all buttons :(
     */

    /**
     * Several (mostly debug) key events
     * @param e is the key press event
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_Z -> { // Add a Key item to the inventory
                try{
                    inventory.addItem(new GenericItem("5"));
                } catch (ArrayIndexOutOfBoundsException exc) {
                    System.out.println("INVENTORY: Not enough space left!");
                }
            }
            case KeyEvent.VK_X -> { // Save current inventory
                inventoryIO.saveInventory();
            }
            case KeyEvent.VK_C -> { // Load inventory from save file
                inventory = inventoryIO.loadInventory();
                updateButtons();
            }
        }
    }

    /**
     * Removes element from the inventory upon clicking on it
     * @param e is the mouse release event
     */
    public void mouseReleased(MouseEvent e) {
        for (int index = 0; index < INVENTORY_ROWS * INVENTORY_COLS; index++){
            InventoryButton button = this.inventoryButtons.get(index);

            if (button.isInBorder(e)){
                try {
                    inventory.removeItem(index);
                } catch (NoSuchElementException exc) {
                    System.out.println("INVENTORY: This element is not in the inventory!");
                }
            }
        }
    }
}
