package inventory;

import buttonUi.Buttons.InventoryButton;
import gamestates.Playing;
import main.Game;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InventoryManager {
    private InventoryButton[] inventoryButtons;

    private Playing playing;
    private BufferedImage inventoryImage;
    private int invWidth, invHeight, invX, invY;

    private Inventory inventory;

    public InventoryManager(Playing playing) {
        this.inventory = new Inventory();

        this.playing = playing;
        loadInventoryImage();

        this.inventoryButtons = new InventoryButton[Inventory.MAX_INVENTORY_SIZE];
        for (int i = 0; i < Inventory.MAX_INVENTORY_SIZE; i++){
            InventoryButton button = new InventoryButton("UI/inventoryButton.png", Game.screenWidth/2, Game.screenHeight/2, 100, 100);
            this.inventoryButtons[i] = button;
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
        /*for(InventoryButton button : inventoryButtons) {
            button.draw(g);
        }*/
        inventoryButtons[0].draw(g);
    }

    public void resetAll() {
    }

    public void update() {
    }
}
