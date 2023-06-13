package inventory;

import buttonUi.Buttons.InventoryButton;
import gamestates.Playing;
import main.GameModel;
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
            InventoryButton button = new InventoryButton("UI/inventoryButton.png", GameModel.screenWidth/2, GameModel.screenHeight/2, 100, 100);
            this.inventoryButtons[i] = button;
        }
    }

    private void loadInventoryImage() {
        this.inventoryImage = Load.GetSpriteImg(Load.INVENTORY);

        this.invWidth = (int) (inventoryImage.getWidth() * GameModel.scale);
        this.invHeight = (int) (inventoryImage.getHeight() * GameModel.scale);
        this.invX = GameModel.screenWidth / 2 - invWidth / 2;
        this.invY = (int) (GameModel.screenHeight /1.25 - invHeight / 2);
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
