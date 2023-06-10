package inventory;

import gamestates.Playing;
import main.Game;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Represents a player's inventory
 */
public class Inventory {
    private int MAX_INVENTORY_SIZE = 12;
    private int usedSlots;
    private boolean fullInventory;

    private Playing playing;
    private BufferedImage inventoryImage;
    private int invWidth, invHeight, invX, invY;

    private ArrayList<Item> itemList;

    public Inventory(Playing playing){

        this.playing = playing;
        loadInventoryImage();

        for (int i = 0; i < MAX_INVENTORY_SIZE; i++){
            addItem(new Item("0"));
        }

        usedSlots = 0;
        fullInventory = false;
    }

    private void loadInventoryImage() {

        inventoryImage = Load.GetSpriteImg(Load.INVENTORY);
        invWidth = (int) (inventoryImage.getWidth() * Game.scale);
        invHeight = (int) (inventoryImage.getHeight() * Game.scale);
        invX = Game.screenWidth / 2 - invWidth / 2;
        invY = (int)(200 * Game.scale);
    }

    public void draw(Graphics g) {
        g.drawImage(inventoryImage, invX, invY, invWidth, invHeight, null);
    }

    private void updateFullInventory(){
        if (usedSlots == MAX_INVENTORY_SIZE){
            this.fullInventory = true;
        } else {
            this.fullInventory = false;
        }
    }

    private int addItem(Item newItem){
        if (!fullInventory){
            return -1;
        }

        int position;
        for (position = 0; position < MAX_INVENTORY_SIZE; position++){
            Item currentItem = itemList.get(position);

            if (currentItem.isEmptyItem()) {
                itemList.add(position, newItem);
                usedSlots++;
                updateFullInventory();
                break;
            }
        }
        return position;
    }

    private Item removeItem(int position){
        // TODO: Should only be called on NON-EMPTY items
        Item removedItem = itemList.get(position);
        itemList.remove(position);
        usedSlots--;
        fullInventory = false;
        return removedItem;
    }

    public void resetAll() {
    }

    public void update() {
    }
}
