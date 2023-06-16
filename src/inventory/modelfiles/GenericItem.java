package inventory.modelfiles;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a generic item
 */
public class GenericItem extends Item implements Serializable {
    @Serial
    private static final long serialVersionUID = 4L;

    public GenericItem(String id){
        this.id = id;
        setDisplayName();
        setVisibility();
        setSpriteLoc();
    }
}
