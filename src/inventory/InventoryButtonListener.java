package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryButtonListener implements ActionListener {
    Inventory inventory;

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public InventoryButtonListener(Inventory inventory){
        this.inventory = inventory;
    }
}
