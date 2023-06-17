package inventory.modelfiles;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a generic item (i.e. it has no extra properties from the Item)
 * @author Cata Mihit
 */
public class GenericItem extends Item implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;

    /**
     * Creates a new GenericItem from an item ID
     * @param id is the string specifying the unique identifier of the item to be created
     */
    public GenericItem(String id){
        super(id);
    }
}
