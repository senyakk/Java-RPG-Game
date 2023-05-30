package inventory;

import java.util.Objects;

/**
 * Represents a generic item
 */
public class Item {
    private String id;
    private String displayName;
    private boolean isVisible;

    public String getId(){
        return this.id;
    }

    public String getDisplayName(){
        return this.displayName;
    }

    private void setDisplayName(){
        // TODO: Access language database based on ID and game language; Game language -> static in main game class
        this.displayName = "";
    }

    public boolean isEmptyItem(){
        return Objects.equals(this.getId(), "0");
    }

    private void setVisibility(){
        this.isVisible = !this.isEmptyItem();
    }

    public boolean getVisibility(){
        return this.isVisible;
    }

    public Item(String id){
        this.id = id;
        setDisplayName();
        setVisibility();
    }
}
