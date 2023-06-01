package inputs;

import main.GamePanel;
import main.InventoryPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private JPanel targetPanel;

    /**
     * Creates keyboard listener connected to the target panel
     * @param targetPanel x
     */
    public KeyboardInputs(JPanel targetPanel){
        this.targetPanel = targetPanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // WASD for movement
            case KeyEvent.VK_W -> {
                if (targetPanel instanceof GamePanel) ((GamePanel) this.targetPanel)
                        .getGame().getPlayer().setUp(true);
            }
            case KeyEvent.VK_A -> {
                if (targetPanel instanceof GamePanel) ((GamePanel) this.targetPanel)
                        .getGame().getPlayer().setLeft(true);
            }
            case KeyEvent.VK_D -> {
                if (targetPanel instanceof GamePanel) ((GamePanel) this.targetPanel)
                        .getGame().getPlayer().setRight(true);
            }
            case KeyEvent.VK_S -> {
                if (targetPanel instanceof GamePanel) ((GamePanel) this.targetPanel)
                        .getGame().getPlayer().setDown(true);
            }
            // P for pause
            case KeyEvent.VK_P -> {
                int gameState = ((GamePanel) this.targetPanel).getGame().gameState;
                if (gameState ==
                        ((GamePanel) this.targetPanel).getGame().playing) {
                    gameState = ((GamePanel) this.targetPanel).getGame().pause;
                }
                else if (gameState ==
                        ((GamePanel) this.targetPanel).getGame().pause) {
                    gameState = ((GamePanel) this.targetPanel).getGame().playing;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            // WASD for movement
            case KeyEvent.VK_W -> {
                if (targetPanel instanceof GamePanel) ((GamePanel) this.targetPanel)
                        .getGame().getPlayer().setUp(false);
            }
            case KeyEvent.VK_A -> {
                if (targetPanel instanceof GamePanel) ((GamePanel) this.targetPanel)
                        .getGame().getPlayer().setLeft(false);
            }
            case KeyEvent.VK_D -> {
                if (targetPanel instanceof GamePanel) ((GamePanel) this.targetPanel)
                        .getGame().getPlayer().setRight(false);
            }
            case KeyEvent.VK_S -> {
                if (targetPanel instanceof GamePanel) ((GamePanel) this.targetPanel)
                        .getGame().getPlayer().setDown(false);
            }
            // Inventory switch
            case KeyEvent.VK_I -> {
                if (this.targetPanel instanceof InventoryPanel) ((InventoryPanel) this.targetPanel).switchVisibility();
                if (this.targetPanel instanceof GamePanel) ((GamePanel) this.targetPanel).switchVisibility();
            }
        }
    }
}
