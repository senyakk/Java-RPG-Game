package inventory.modelfiles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class of data that defines an item
 * @author Cata Mihit
 */
public class ItemData implements Serializable {
    private String id;
    private ArrayList<String> displayNames;
    private String spriteLoc;

    public ItemData(String id, ArrayList<String> displayNames, String spriteLoc) {
        this.id = id;
        this.displayNames = displayNames;
        this.spriteLoc = spriteLoc;
    }
}
