package inventory.modelfiles;

import main.GameModel;

import java.io.*;

/**
 * Class that handles input and output behaviour for the inventory, i.e. saving to and loading from object files
 * @author Cata Mihit
 */
public class InventoryIO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String savePath = GameModel.SAVE_DIR + "Inventory.obj";

    /**
     * Saves the inventory object in the resources folder
     */
    public void saveInventory(Inventory inventory){
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("resources/saves/Inventory.obj"))) {
            o.writeObject(inventory);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Either loads an inventory from a save file or creates a new inventory
     * @return the game inventory that results from this operation
     */
    public Inventory loadInventory(){
        Inventory inventory = null;
        try (ObjectInputStream i = new ObjectInputStream(new FileInputStream("resources/saves/Inventory.obj"))) {
            inventory = (Inventory) i.readObject();
            System.out.println("INVENTORY: Loaded inventory from file!");
        } catch (IOException e) {
            e.printStackTrace();
            new File(savePath);
            inventory = new Inventory();
        } catch (ClassNotFoundException e) {
            System.out.println("INVENTORY: No inventory saved yet!");
            inventory = new Inventory();
        }
        return inventory;
    }
}
