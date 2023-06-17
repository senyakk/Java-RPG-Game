package inventory.controllerfiles;

import inventory.viewfiles.InventoryManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Listener that propagates inventory changes from the Inventory (Model) to the InventoryManager (View)
 * @author Cata Mihit
 */
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
     * manager can be updated accordingly. Only "itemList" property changes are being used now
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        inventoryManager.updateButtons();
    }
}
