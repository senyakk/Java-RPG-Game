package inventory.modelfiles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class of generic data that defines an item - ID, display names in different languages, sprite path
 * @author Cata Mihit
 */
public class ItemData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private ArrayList<String> displayNames;
    private String spriteLoc;

    /**
     * Constructor for an ItemData object from the individual components
     * @param id is the unique ID of the item
     * @param displayNameEnglish is the name of the item in English (when displayed on screen)
     * @param displayNameDutch is the name of the item in Dutch (when displayed on screen)
     * @param spriteLoc is the path to the sprite corresponding to the item
     */
    public ItemData(String id, String displayNameEnglish, String displayNameDutch, String spriteLoc) {
        this.id = id;
        this.displayNames = new ArrayList<>();
        displayNames.add(displayNameEnglish);
        displayNames.add(displayNameDutch);
        this.spriteLoc = "items/" + spriteLoc + ".png";
    }

    public ArrayList<String> getDisplayNames(){
        return this.displayNames;
    }

    public String getSpriteLoc(){
        return this.spriteLoc;
    }
}
