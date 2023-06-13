package inventory;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Represents a player's inventory
 */
public class Inventory {
    private final int MAX_INVENTORY_SIZE = 36;

    private int usedSlots;
    private boolean fullInventory;

    private final ArrayList<Item> itemList;

    public Inventory(){
        itemList = new ArrayList<Item>(MAX_INVENTORY_SIZE);
        for (int itemNum = 0; itemNum < MAX_INVENTORY_SIZE; itemNum++){
            itemList.add(itemNum, new Item("0"));
        }

        addItem(new Item("1"));
        addItem(new Item("2"));

        updateFullInventory();
    }

    private void updateFullInventory(){
        this.fullInventory = (usedSlots == MAX_INVENTORY_SIZE);
    }

    private void addItem(Item newItem) throws ArrayIndexOutOfBoundsException {
        if (fullInventory){
            throw new ArrayIndexOutOfBoundsException();
        }

        int position;
        for (position = 0; position < MAX_INVENTORY_SIZE; position++){
            Item currentItem = itemList.get(position);

            if (currentItem.isEmptyItem()) {
                itemList.set(position, newItem);
                usedSlots++;
                updateFullInventory();
                break;
            }
        }
    }

    private Item removeItem(int position){
        Item removedItem = itemList.get(position);

        if (removedItem.isEmptyItem()){
            throw new NoSuchElementException("This inventory slot is empty!");
        }

        itemList.remove(position);
        usedSlots--;
        fullInventory = false;
        return removedItem;
    }

    public ArrayList<Item> getItemList(){
        return this.itemList;
    }
}
