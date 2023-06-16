package inventory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class InventoryPropertyListener implements PropertyChangeListener {
    private final InventoryManager inventoryManager;

    /**
     * Constructor for the inventory property listener
     * @param inventoryManager is the UI component that the listener can modify
     */
    public InventoryPropertyListener(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }

    /**
     * The listener is notified when the inventory is modified, such that the
     *          manager can be updated accordingly
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        inventoryManager.updateButtons();
    }
}
