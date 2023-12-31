package inventory.modelfiles;

import utilities.Constants;

import java.awt.image.BufferedImage;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Abstract class that implements general methods to be shared between all item types
 * @author Cata Mihit
 */
public abstract class Item implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    protected String id;
    protected ArrayList<String> displayNames;
    protected boolean isVisible;
    protected String spriteLoc;
    protected BufferedImage sprite;

    /**
     * General constructor for item classes
     * @param id is the unique ID of the item
     */
    protected Item(String id){
        this.id = id;
        setDisplayNames();
        setVisibility();
        setSpriteLoc();
    }

    /**
     * Getter for ID of Item object
     * @return the unique ID string of the object (e.g. "Sword" has ID "1", "Bow" has ID "2", etc.)
     */
    public String getId(){
        return this.id;
    }

    /**
     * Setter for the display names of the Item object, realized by accessing the item list
     */
    protected void setDisplayNames(){
        this.displayNames = Constants.ItemList.ITEM_LIST.get(id).getDisplayNames();
    }

    /**
     * Gets the display name of the Item object, which is based on ID and current game language
     * @return the set display name based on language
     */
    public ArrayList<String> getDisplayNames(){
        return this.displayNames;
    }

    /**
     * Empty items (with ID "0") behave differently
     * @return true if the Item has ID "0"
     */
    public boolean isEmptyItem(){
        return Objects.equals(this.getId(), "0");
    }

    /**
     * Non-empty items are visible in the inventory (right now their texture is transparent)
     */
    protected void setVisibility(){
        this.isVisible = !this.isEmptyItem();
    }

    /**
     * Whether an Item is visible or not
     * @return true if the Item is not an empty item
     */
    public boolean getVisibility(){
        return this.isVisible;
    }

    /**
     * Sets the location of the sprite resource by accessing the item list
     */
    protected void setSpriteLoc(){
        this.spriteLoc = Constants.ItemList.ITEM_LIST.get(id).getSpriteLoc();
    }

    /**
     * Determines the location of the sprite resource
     * @return the path of the sprite for the current Item object
     */
    public String getSpriteLoc(){
        return this.spriteLoc;
    }

    /**
     * Determines the exact sprite of the Item
     * @return the sprite corresponding to this ID
     */
    public BufferedImage getSprite(){
        return this.sprite;
    }
}
