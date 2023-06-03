package gamestates;

import creatures.Player;
import main.Game;
import main.GamePanel;
import main.InventoryPanel;
import tile.TileManager;
import ui.Pause;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods {

    private Player player;
    private TileManager tileManager;

    private Pause pause;
    private boolean paused = false;
    public Playing(Game game, TileManager tileManager) {
        super(game);
        this.tileManager = tileManager;
        init();
    }

    private void init() {
        player = new Player();
        pause = new Pause(this);
    }

    public Player getPlayer() {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirections();
    }

    @Override
    public void update() {
        if (!paused) {
            player.update();
        } else {
            pause.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        tileManager.draw(g);
        player.render(g);
        if (paused) {
            pause.draw(g);
        }

    }

    @Override
    public void keyPressed(KeyEvent e, JPanel targetPanel) {
        switch (e.getKeyCode()) {
            // WASD for movement
            case KeyEvent.VK_W -> {
                if (targetPanel instanceof GamePanel) {
                    player.setUp(true);
                }
            }
            case KeyEvent.VK_A -> {
                if (targetPanel instanceof GamePanel) {
                    player.setLeft(true);
                }
            }
            case KeyEvent.VK_D -> {
                if (targetPanel instanceof GamePanel) {
                    player.setRight(true);
                }
            }
            case KeyEvent.VK_S -> {
                if (targetPanel instanceof GamePanel) {
                    player.setDown(true);
                }
            }
            case KeyEvent.VK_ESCAPE -> {
                if (targetPanel instanceof GamePanel)
                    paused = !paused;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e, JPanel targetPanel) {
        switch (e.getKeyCode()) {
            // WASD for movement
            case KeyEvent.VK_W -> {
                if (targetPanel instanceof GamePanel) {
                    player.setUp(false);
                }
            }
            case KeyEvent.VK_A -> {
                if (targetPanel instanceof GamePanel) {
                    player.setLeft(false);
                }
            }
            case KeyEvent.VK_D -> {
                if (targetPanel instanceof GamePanel) {
                    player.setRight(false);
                }
            }
            case KeyEvent.VK_S -> {
                if (targetPanel instanceof GamePanel) {
                    player.setDown(false);
                }
            }
            // Inventory switch
            case KeyEvent.VK_I -> {
                if (!paused) {
                    if (targetPanel instanceof InventoryPanel) ((InventoryPanel) targetPanel).switchVisibility();
                    if (targetPanel instanceof GamePanel) ((GamePanel) targetPanel).switchVisibility();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused) {
            pause.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused) {
            pause.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused) {
            pause.mouseMoved(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void unpause() {
        paused = false;
    }

    public void mouseDragged(MouseEvent e) {
        if (paused) {
            pause.mouseDragged(e);
        }
    }
}
