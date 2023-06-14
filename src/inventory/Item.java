package inventory;

import java.awt.image.BufferedImage;

public interface Item {
    /**
     * Getter for ID of Item object
     * @return the unique ID string of the object (e.g. "Sword" has ID "15", "Bow" has ID "23", etc.)
     */
    String getId();

    /**
     * The proper display name of the Item object is based on ID and current game language
     * @return the set display name based on language
     */
    String getDisplayName();

    /**
     * Empty items (with ID "0") behave differently
     * @return true if the Item has ID "0"
     */
    boolean isEmptyItem();

    /**
     * Whether an Item is visible or not
     * @return true if the Item is not an empty item
     */
    boolean getVisibility();

    /**
     * Determines the location of the sprite resource
     * @return the path of the sprite for the current Item object
     */
    String getSpriteLoc();

    /**
     * Determines the exact sprite of the Item
     * @return the sprite corresponding to this ID
     */
    BufferedImage getSprite();
}
