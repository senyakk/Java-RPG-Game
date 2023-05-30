package inputs;

import main.GamePanel;
import main.InventoryPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    Object targetObject;

    public KeyboardInputs(Object targetObject){
        this.targetObject = targetObject;
    }

    /**
     * Creates keyboard listener connected to the GamePanel
     * @param gamePanel
     */
    public KeyboardInputs(GamePanel gamePanel) {
        this.targetObject = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                ((GamePanel)this.targetObject).changeYDelta(-5);
                break;
            case KeyEvent.VK_A:
                ((GamePanel)this.targetObject).changeXDelta(-5);
                break;
            case KeyEvent.VK_S:
                ((GamePanel)this.targetObject).changeYDelta(+5);
                break;
            case KeyEvent.VK_D:
                ((GamePanel)this.targetObject).changeXDelta(+5);
                break;
            case KeyEvent.VK_I:
                if (this.targetObject instanceof InventoryPanel) ((InventoryPanel)this.targetObject).switchVisibility();
                if (this.targetObject instanceof GamePanel) ((GamePanel)this.targetObject).switchVisibility();
                break;
        }

    }
}
