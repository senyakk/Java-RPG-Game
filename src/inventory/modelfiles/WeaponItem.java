package inventory.modelfiles;

import java.io.Serial;
import java.io.Serializable;

/**
 * An item class representing a weapon - has an additional damage component
 * @author Cata Mihit
 */
public class WeaponItem extends Item implements Serializable {
    @Serial
    private static final long serialVersionUID = 4L;

    private float damage;

    /**
     * Creates a new WeaponItem from an item ID, and gives it a specific damage value
     * @param id is the string specifying the unique identifier of the item to be created
     */
    public WeaponItem(String id, float damage){
        super(id);
        this.damage = damage;
    }
}
