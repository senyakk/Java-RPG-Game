package inputs;

import main.GamePanel;
import main.InventoryPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
                if (targetObject instanceof GamePanel) ((GamePanel) this.targetObject).changeYDelta(-5);
            }
            case KeyEvent.VK_A -> {
                if (targetObject instanceof GamePanel) ((GamePanel) this.targetObject).changeXDelta(-5);
            }
            case KeyEvent.VK_S -> {
                if (targetObject instanceof GamePanel) ((GamePanel) this.targetObject).changeYDelta(+5);
            }
            case KeyEvent.VK_D -> {
                if (targetObject instanceof GamePanel) ((GamePanel) this.targetObject).changeXDelta(+5);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Inventory switch
        if (e.getKeyCode() == KeyEvent.VK_I) {
            if (this.targetObject instanceof InventoryPanel) ((InventoryPanel) this.targetObject).switchVisibility();
            if (this.targetObject instanceof GamePanel) ((GamePanel) this.targetObject).switchVisibility();
        }

    }
}
