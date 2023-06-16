package inventory.controllerfiles;

import inventory.modelfiles.Inventory;
import inventory.viewfiles.InventoryButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InventoryButtonListener implements MouseListener {
    private final Inventory inventory;
    private final int position;
    private final InventoryButton button;

    /**
     * Constructor for the inventory property listener
     * @param inventory is the model component that the listener can modify
     * @param position indicates which button on the manager was acted on
     */
    public InventoryButtonListener(Inventory inventory, int position, InventoryButton button){
        this.inventory = inventory;
        this.position = position;
        this.button = button;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (button.isInBorder(e)){
            System.out.println("in bounds!");
            inventory.removeItem(position);
        } else {
            System.out.println("out of bounds!");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
