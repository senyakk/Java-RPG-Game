package inventory;

import java.util.ArrayList;

/**
 * Represents a player's inventory
 */
public class Inventory {
    private int MAX_INVENTORY_SIZE = 12;
    private int usedSlots;
    private boolean fullInventory;

    private ArrayList<Item> itemList;

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

    public Inventory(){
        for (int i = 0; i < MAX_INVENTORY_SIZE; i++){
            addItem(new Item("0"));
        }

        usedSlots = 0;
        fullInventory = false;
    }
}
