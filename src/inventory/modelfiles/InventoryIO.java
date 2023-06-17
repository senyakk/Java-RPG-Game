package inventory.modelfiles;

import main.GameModel;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Class that handles input and output behaviour for the inventory, i.e. saving to and loading from object files
 * @author Cata Mihit
 */
public class InventoryIO implements Serializable {
    @Serial
    private static final long serialVersionUID = 7L;

    private final String savePath = GameModel.SAVE_DIR + "Inventory.obj";
    private Inventory inventory;

    public InventoryIO(Inventory inventory){
        this.inventory = inventory;
        saveInventory();
    }

    /**
     * Saves the inventory object in the resources folder
     */
    public void saveInventory(){
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("resources/saves/Inventory.obj"))) {
            o.writeObject(inventory.getItemList());
            System.out.println("INVENTORY: Saved inventory!");
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
        ArrayList<Item> itemList = null;

        try (ObjectInputStream i = new ObjectInputStream(new FileInputStream("resources/saves/Inventory.obj"))) {
            itemList = (ArrayList<Item>) i.readObject();
            inventory = new Inventory(itemList);
            System.out.println("INVENTORY: Loaded inventory from file!");
        } catch (IOException e) {
            e.printStackTrace();
            new File(savePath);
            inventory = new Inventory();
        } catch (ClassNotFoundException e) {
            System.out.println("INVENTORY: No inventory saved yet!");
            inventory = new Inventory();
        }

        this.inventory = inventory;
        return inventory;
    }
}
