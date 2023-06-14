package inventory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class InventoryPropertyListener implements PropertyChangeListener {
    InventoryManager inventoryManager;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public InventoryPropertyListener(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }
}
