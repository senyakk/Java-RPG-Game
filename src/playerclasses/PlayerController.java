package playerclasses;

import gamestates.Playing;

import java.awt.event.KeyEvent;

public class PlayerController {
    private Playing playing;

    public PlayerController(Playing playing) {
        this.playing = playing;
    }

    public void handleKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // WASD for movement
            case KeyEvent.VK_W -> playing.getPlayer().setUp(true);
            case KeyEvent.VK_A -> playing.getPlayer().setLeft(true);
            case KeyEvent.VK_D -> playing.getPlayer().setRight(true);
            case KeyEvent.VK_S -> playing.getPlayer().setDown(true);
            case KeyEvent.VK_SPACE -> playing.getPlayer().setAttacking();
        }
    }

    public void handleKeyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> playing.getPlayer().setUp(false);
            case KeyEvent.VK_A -> playing.getPlayer().setLeft(false);
            case KeyEvent.VK_D -> playing.getPlayer().setRight(false);
            case KeyEvent.VK_S -> playing.getPlayer().setDown(false);
        }
    }
}