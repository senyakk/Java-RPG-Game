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

    private ArrayList<InventoryPropertyListener> listeners;

    /**
     * Inventory constructor, creates a list of MAX_INVENTORY_SIZE empty items (+- other setup)
     */
    public Inventory(){
        itemList = new ArrayList<Item>(MAX_INVENTORY_SIZE);
        for (int itemNum = 0; itemNum < MAX_INVENTORY_SIZE; itemNum++){
            itemList.add(itemNum, new GenericItem("0"));
        }

        addItem(new GenericItem("1"));
        addItem(new GenericItem("2"));

        updateFullInventory();
    }

    /**
     * Updates the value of the fullInventory property based on the number of used slots in the inventory
     */
    private void updateFullInventory(){
        this.fullInventory = (usedSlots == MAX_INVENTORY_SIZE);
    }

    /**
     * Adds an item to the first position that is empty (is an empty item)
     * @param newItem is the item to be added in the inventory
     * @throws ArrayIndexOutOfBoundsException occurs when there are no remaining empty slots
     */
    private void addItem(GenericItem newItem) throws ArrayIndexOutOfBoundsException {
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

    /**
     * Removes the item at a given position in the inventory
     * @param position is the position from where to remove
     * @return the Item object that was removed
     * @throws NoSuchElementException occurs when there is no (non-empty) item to be removed
     */
    private Item removeItem(int position) throws NoSuchElementException {
        Item removedItem = itemList.get(position);

        if (removedItem.isEmptyItem()){
            throw new NoSuchElementException("This inventory slot is empty!");
        }

        itemList.remove(position);
        usedSlots--;
        fullInventory = false;
        return removedItem;
    }

    /**
     * Getter for the item list
     * @return the list of items in the inventory
     */
    public ArrayList<Item> getItemList(){
        return this.itemList;
    }

    /**
     * Adds a property listener to the list of listeners
     * @param listener is the new listener to be added
     */
    public void addListeners(InventoryPropertyListener listener){
        this.listeners.add(listener);
    }
}
