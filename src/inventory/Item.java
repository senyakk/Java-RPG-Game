package inventory;

import utilities.Load;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Represents a generic item
 */
public class Item {
    private String id;
    private String displayName;
    private boolean isVisible;
    private BufferedImage sprite;

    // ID getter
    public String getId(){
        return this.id;
    }

    // Display name
    private void setDisplayName(){
        // TODO: Access language database based on ID and game language; Game language -> static in main game class
        if (this.id.equals("1")) this.displayName = "Arrow";
        if (this.id.equals("2")) this.displayName = "Bow";
    }

    public String getDisplayName(){
        return this.displayName;
    }

    // Empty item
    public boolean isEmptyItem(){
        return Objects.equals(this.getId(), "0");
    }

    // Visibility
    private void setVisibility(){
        this.isVisible = !this.isEmptyItem();
    }

    public boolean getVisibility(){
        return this.isVisible;
    }

    // Sprite
    private void setSprite(){
        // TODO: Access database based on ID
        String impath = "";
        if (this.id.equals("1")) impath = "arrowItem.png";
        if (this.id.equals("2")) impath = "bowItem.png";
    }

    public BufferedImage getSprite(){
        return this.sprite;
    }

    public Item(String id){
        this.id = id;
        setDisplayName();
        setVisibility();
        //setSprite();
    }
}
