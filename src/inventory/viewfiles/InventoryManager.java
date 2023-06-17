package inventory.viewfiles;

import buttonUi.GameButton;
import gamestates.Playing;
import inventory.controllerfiles.InventoryPropertyListener;
import inventory.modelfiles.*;
import main.GameModel;
import main.GamePanel;
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
    private Item playerWeapon;

    // Saver/Loader for Inventory object
    private InventoryIO inventoryIO;
    private Inventory inventory;

    // UI-Related elements
    private final int INVENTORY_ROWS = 3;
    private final int INVENTORY_COLS = 12;
    private final ArrayList<InventoryButton> inventoryButtons; //InventoryButton[] inventoryButtons;
    private int invWidth, invHeight, invX, invY;
    private BufferedImage inventoryImage;
    private final GameButton currentItemName;

    /**
     * Determines what weapon the player should own (given by default in inventory)
     * @param player is the player who has this inventory
     * @return the weapon item corresponding to their class, or an empty item if something goes wrong
     */
    private void setupWeapon(PlayerModel player){
        int playerClass = player.getPlayerClassAsInt();
        switch (playerClass) {
            case WARRIOR -> playerWeapon = new WeaponItem("1", 2.0f);
            case ARCHER -> playerWeapon = new WeaponItem("2", 3.0f);
            case BARD -> playerWeapon = new WeaponItem("4", 1.0f);
            default -> playerWeapon = new GenericItem("0");
        }
    }

    /**
     * Creates an inventory (new or from saves) and prepares its UI elements
     * @param playing the game state that controls the inventory manager
     */
    public InventoryManager(Playing playing) {
        this.playing = playing;

        setupWeapon(playing.getPlayer());

        // Test code -> save/load almost works
        this.inventory = new Inventory();
        this.inventoryIO = new InventoryIO(inventory);

        this.inventory.addListeners(new InventoryPropertyListener(this));

        loadInventoryImage();
        this.currentItemName = new GameButton((GameModel.screenWidth / 2 + invWidth / 2),
                (invY + invHeight /2),150, 75);
        this.currentItemName.setText(" ");
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
        currentItemName.draw(g);
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
                    inventory.addItem(new GenericItem("6"));
                    inventory.addItem(new GenericItem("7"));
                    inventory.addItem(new GenericItem("8"));
                    inventory.addItem(new GenericItem("9"));
                    inventory.addItem(new GenericItem("10"));
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

    /**
     * Updates display text based on whether an inventory item is hovered over
     * @param e is the mouse release event
     */
    public void mouseMoved(MouseEvent e) {
        int language = playing.getGameModel().getLanguage();

        boolean insideBound = false;
        for (int index = 0; index < INVENTORY_ROWS * INVENTORY_COLS; index++){
            InventoryButton button = this.inventoryButtons.get(index);

            if (button.isInBorder(e)){
                ArrayList<String> names = inventory.getItemList().get(index).getDisplayNames();
                currentItemName.setText(names.get(language));
                insideBound = true;
            }
        }
        if (!insideBound) {
            currentItemName.setText(" ");
        }
    }

    /**
     * Resets inventory upon exiting the playing state
     */
    public void resetAll(){
        this.inventory.reset();
    }

    /**
     * Updates the inventory at each frame and adds the player weapon in the inventory if it doesn't exist yet
     */
    public void update(){
        setupWeapon(playing.getPlayer());
        if (!inventory.contains(playerWeapon)){
            inventory.addItem(playerWeapon);
        }
    }

    /**
     * Used when a game object is picked up to add it to the inventory
     * @param itemID is the ID corresponding to the picked up object
     * @param itemClass is the type of object being picked up
     */
    public void notifyPickup(String itemID, String itemClass){
        switch (itemClass){
            case "WeaponItem" -> inventory.addItem(new WeaponItem(itemID, 2.0f));
            // other item classes
            default -> inventory.addItem(new GenericItem(itemID));
        }
    }
    public boolean isInInventory(String id) {
        return inventory.contains(new GenericItem(id));
    }
}
