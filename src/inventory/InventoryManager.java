package inventory;

import buttonUi.Buttons.InventoryButton;
import gamestates.Playing;
import main.Game;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class InventoryManager {
    private final int INVENTORY_ROWS = 3;
    private final int INVENTORY_COLS = 12;
    private final InventoryButton[] inventoryButtons;

    private Playing playing;
    private BufferedImage inventoryImage;
    private int invWidth, invHeight, invX, invY;

    private Inventory inventory;

    /**
     * Creates an instance of the inventory manager, which prepares the UI panel
     * @param playing the game state that controls the inventory manager
     */
    public InventoryManager(Playing playing) {
        this.inventory = new Inventory();
        this.playing = playing;

        loadInventoryImage();

        this.inventoryButtons = new InventoryButton[INVENTORY_ROWS * INVENTORY_COLS];
        updateButtons();
    }

    private void updateButtons(){
        ArrayList<Item> currentItemList = inventory.getItemList();

        // Improper calculation of position<3
        int pos0x = (int) (invX + 55 * Game.scale);
        int pos0y = (int) (invY + 85 * Game.scale);
        int spriteSize = 16; //(int)(16 * Game.scale);
        int skip = (int) (8 * Game.scale);

        int i = 0;
        int posx, posy;

        for (int row = 0; row < INVENTORY_ROWS; row++){
            for (int col = 0; col < INVENTORY_COLS; col++){
                Item currentItem = currentItemList.get(row * INVENTORY_COLS + col);

                posx = pos0x + col*((int)(spriteSize*Game.scale) + skip);
                posy = pos0y + row*((int)(spriteSize*Game.scale) + skip);

                System.out.println(currentItem.getId() + " - " + currentItem.getSpriteLoc());
                InventoryButton button = new InventoryButton(currentItem.getSpriteLoc(), posx, posy, spriteSize, spriteSize);

                this.inventoryButtons[i] = button;
                i++;
            }
        }
    }

    private void loadInventoryImage() {
        this.inventoryImage = Load.GetSpriteImg(Load.INVENTORY);

        this.invWidth = (int) (inventoryImage.getWidth() * Game.scale);
        this.invHeight = (int) (inventoryImage.getHeight() * Game.scale);
        this.invX = Game.screenWidth / 2 - invWidth / 2;
        this.invY = Game.screenHeight * 3/4 - invHeight / 2;
        //this.invY = (int)(200 * Game.scale);
    }

    public void draw(Graphics g) {
        g.drawImage(inventoryImage, invX, invY, invWidth, invHeight, null);
        for(InventoryButton button : inventoryButtons) {
            button.draw(g);
        }
    }

    public void resetAll() {
    }

    public void update() {
    }
}
