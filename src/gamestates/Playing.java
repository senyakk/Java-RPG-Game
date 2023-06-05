package gamestates;

import creatures.Player;
import main.Game;
import tile.TileManager;
import ui.Pause;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods {

    private Player player;
    private TileManager tileManager = new TileManager();
    private Pause pause;
    private boolean paused = false;
    public Playing(Game game) {
        super(game);
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

    public void resetAll() {
        paused = false;
        player.resetAll();
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
        tileManager.draw(g, player);
        player.render(g);
        if (paused) {
            g.setColor(new Color(0,0,0,150));
            g.fillRect(0,0, Game.screenWidth, Game.screenHeight);
            pause.draw(g);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // WASD for movement
            case KeyEvent.VK_W -> player.setUp(true);
            case KeyEvent.VK_A ->  player.setLeft(true);
            case KeyEvent.VK_D -> player.setRight(true);
            case KeyEvent.VK_S -> player.setDown(true);

            case KeyEvent.VK_ESCAPE -> paused = !paused;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_W -> player.setUp(false);
            case KeyEvent.VK_A -> player.setLeft(false);
            case KeyEvent.VK_D -> player.setRight(false);
            case KeyEvent.VK_S -> player.setDown(false);

            // Inventory switch
            case KeyEvent.VK_I -> {
                if (!paused) {
                    Gamestate.state = Gamestate.INVENTORY;
                    game.getPlaying().getPlayer().resetDirections();
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
