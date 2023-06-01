package inputs;

import main.GamePanel;
import main.InventoryPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utilz.Constants.Direction.*;

public class KeyboardInputs implements KeyListener {
    private Object targetObject;

    /**
     * Creates keyboard listener connected to the target object
     * @param targetObject x
     */
    public KeyboardInputs(Object targetObject){
        this.targetObject = targetObject;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // WASD for movement
            case KeyEvent.VK_W -> {
                if (targetObject instanceof GamePanel) ((GamePanel) this.targetObject)
                        .getGame().getPlayer().setUp(true);
            }
            case KeyEvent.VK_A -> {
                if (targetObject instanceof GamePanel) ((GamePanel) this.targetObject)
                        .getGame().getPlayer().setLeft(true);
            }
            case KeyEvent.VK_D -> {
                if (targetObject instanceof GamePanel) ((GamePanel) this.targetObject)
                        .getGame().getPlayer().setRight(true);
            }
            case KeyEvent.VK_S -> {
                if (targetObject instanceof GamePanel) ((GamePanel) this.targetObject)
                        .getGame().getPlayer().setDown(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            // WASD for movement
            case KeyEvent.VK_W -> {
                if (targetObject instanceof GamePanel) ((GamePanel) this.targetObject)
                        .getGame().getPlayer().setUp(false);
            }
            case KeyEvent.VK_A -> {
                if (targetObject instanceof GamePanel) ((GamePanel) this.targetObject)
                        .getGame().getPlayer().setLeft(false);
            }
            case KeyEvent.VK_D -> {
                if (targetObject instanceof GamePanel) ((GamePanel) this.targetObject)
                        .getGame().getPlayer().setRight(false);
            }
            case KeyEvent.VK_S -> {
                if (targetObject instanceof GamePanel) ((GamePanel) this.targetObject)
                        .getGame().getPlayer().setDown(false);
            }
            // Inventory switch
            case KeyEvent.VK_I -> {
                if (this.targetObject instanceof InventoryPanel) ((InventoryPanel) this.targetObject).switchVisibility();
                if (this.targetObject instanceof GamePanel) ((GamePanel) this.targetObject).switchVisibility();
            }
        }
    }
}
