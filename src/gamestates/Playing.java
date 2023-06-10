package gamestates;

import playerclasses.Player;
import inventory.Inventory;
import main.Game;
import locations.LevelManager;
import ui.Pause;
import ui.PlayingUI;
import objects.ObjectManager;
import locations.CollisionChecker;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods {

    private Player player;
    private PlayingUI ui;
    private LevelManager levelManager = new LevelManager();
    private ObjectManager placer;
    private CollisionChecker collisionChecker;
    private Pause pause;
    private Inventory inventory;
    private boolean paused = false;
    private boolean inventoryOn = false;

    public Playing(Game game) {
        super(game);
        initLevel();
    }

    private void initLevel() {
        levelManager = new LevelManager();
        loadLevel();
        pause = new Pause(this);

    }

    private void loadLevel() {
        levelManager.setCurrentLevel(0);
        placer = new ObjectManager(this);
        collisionChecker = new CollisionChecker(levelManager);
        //npcManager = new NPCManager(this, collisionChecker);
        putPlayer();
    }

    private void putPlayer() {
        switch (getLevelManager().getCurrentLevelId()) {
            case 0 -> {
                player = new Player(23, 21, this);
            }
        }
        player.addCollisionChecker(collisionChecker);
        ui = new PlayingUI(this);
        inventory = new Inventory(this);
    }

    public Player getPlayer() {
        return player;
    }

    public PlayingUI getUi() {
        return ui;
    }

    public void windowFocusLost() {
        player.resetDirections();
    }

    public void resetAll() {
        paused = false;
        inventoryOn = false;
        player.resetAll();
        placer.resetAll();
        inventory.resetAll();
    }

    @Override
    public void update() {
        if (!paused) {
            levelManager.update();
            placer.update();
            //npcManager.update();
            player.update();
            inventory.update();
        } else {
            pause.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g, player);
        placer.drawObjects(g);

        player.render(g);
        ui.draw(g);
        if (inventoryOn)
            inventory.draw(g);
        if (paused) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, Game.screenWidth, Game.screenHeight);
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
                    inventoryOn = !inventoryOn;
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

    public LevelManager getLevelManager() {
        return levelManager;
    }
}
