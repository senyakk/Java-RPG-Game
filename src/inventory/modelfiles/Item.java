package inventory.modelfiles;

import java.awt.image.BufferedImage;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public abstract class Item implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;

    protected String id;
    protected String displayName;
    protected boolean isVisible;
    protected String spriteLoc;
    protected BufferedImage sprite;

    /**
     * Getter for ID of Item object
     * @return the unique ID string of the object (e.g. "Sword" has ID "15", "Bow" has ID "23", etc.)
     */
    public String getId(){
        return this.id;
    }

    protected void setDisplayName(){
        // TODO: Access language database based on ID and game language; Game language -> static in main game class
        if (this.id.equals("1")) this.displayName = "Arrow";
        if (this.id.equals("2")) this.displayName = "Bow";
        if (this.id.equals("10")) this.displayName = "Key";
    }

    /**
     * The proper display name of the Item object is based on ID and current game language
     * @return the set display name based on language
     */
    public String getDisplayName(){
        return this.displayName;
    }

    /**
     * Empty items (with ID "0") behave differently
     * @return true if the Item has ID "0"
     */
    public boolean isEmptyItem(){
        return Objects.equals(this.getId(), "0");
    }

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
     * Determines the location of the sprite resource
     * @return the path of the sprite for the current Item object
     */
    protected void setSpriteLoc(){
        // TODO: Access database based on ID
        if (this.id.equals("0")) this.spriteLoc = "items/0.png";
        if (this.id.equals("1")) this.spriteLoc = "items/arrowItem.png";
        if (this.id.equals("2")) this.spriteLoc = "items/bowItem.png";
        if (this.id.equals("10")) this.spriteLoc = "items/keyItem.png";
    }

    public String getSpriteLoc(){
        // TODO: Access database based on ID
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
