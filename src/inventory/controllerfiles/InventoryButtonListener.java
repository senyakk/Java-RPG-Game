package inventory.controllerfiles;

import gamestates.Playing;
import inventory.modelfiles.Inventory;
import inventory.viewfiles.InventoryButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener for an individual button on the inventory View
 * Is literally impossible to implement without having to get the panel of the controller of the state of the whatever
 * the rest of the code is doing, which is beyond what i can personally understand and believe to be okay in the
 * context of this MVC project :)
 * @author Cata Mihit
 */
public class InventoryButtonListener implements MouseListener {
    private Playing playing;

    private final Inventory inventory;
    private final int position;
    private final InventoryButton button;

    /**
     * Constructor for the inventory property listener
     * @param inventory is the model component that the listener can modify
     * @param position indicates which button on the manager was acted on
     */
    public InventoryButtonListener(Playing playing, Inventory inventory, int position, InventoryButton button){
        this.playing = playing;
        this.inventory = inventory;
        this.position = position;
        this.button = button;
        System.out.println("HII " + button.getBounds());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("HIIYE");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("HIIYE");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (button.isInBorder(e)){
            System.out.println("in bounds!");
            inventory.removeItem(position);
        } else {
            System.out.println("out of bounds!");
        }
        System.out.println("HIIYE");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("HIIYE");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("HIIYE");
    }
}
