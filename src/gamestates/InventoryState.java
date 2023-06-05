package gamestates;

import inventory.Inventory;
import main.Game;
import utilities.Load;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class InventoryState extends State implements Statemethods {
    private Inventory inventory;
    private BufferedImage inventoryImage;
    private int invWidth, invHeight, invX, invY;

    public InventoryState(Game game) {
        super(game);

        loadInventoryImage();
    }

    private void loadInventoryImage() {

        inventoryImage = Load.GetSpriteImg(Load.INVENTORY);
        invWidth = (int) (inventoryImage.getWidth() * Game.scale);
        invHeight = (int) (inventoryImage.getHeight() * Game.scale);
        invX = Game.screenWidth / 2 - invWidth / 2;
        invY = (int)(240 * Game.scale);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(inventoryImage, invX, invY, invWidth, invHeight, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_I) {
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
