package gamestates;

import inventory.InventoryManager;
import playerclasses.Player;
import inventory.Inventory;
import main.Game;
import locations.LevelManager;
import buttonUi.Pause;
import playerclasses.PlayerUI;
import objects.ObjectManager;
import locations.CollisionChecker;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods {

    private Player player;
    private PlayerUI ui;
    private LevelManager levelManager = new LevelManager();
    private ObjectManager placer;
    private CollisionChecker collisionChecker;
    private Pause pause;
    private InventoryManager inventoryManager;
    private boolean paused = false;
    private boolean inventoryOn = false;

    /**
     * State for playing the game
     * @param game Game object
     */
    public Playing(Game game) {
        super(game);
        initLevel();
    }

    /**
     * Creates LevelManager and loads Level
     */
    private void initLevel() {
        levelManager = new LevelManager();
        loadLevel();
        pause = new Pause(this);

    }

    /**
     * Loads current level, creates object manager, collision checker, and puts player on the level
     */
    private void loadLevel() {
        levelManager.setCurrentLevel(0);
        placer = new ObjectManager(this);
        collisionChecker = new CollisionChecker(levelManager);
        //npcManager = new NPCManager(this, collisionChecker);
        putPlayer();
    }

    /**
     * Puts player on a level, adds collision checker to it, draws UI and inventory
     */
    private void putPlayer() {
        switch (getLevelManager().getCurrentLevelId()) {
            case 0 -> {
                player = new Player(22, 21, this);
            }
            case 1 -> {
                player = new Player(3, 5, this);
            }
            case 2 -> {
                player = new Player(4, 5, this);
            }
        }
        player.addCollisionChecker(collisionChecker);
        ui = new PlayerUI(this);
        inventoryManager = new InventoryManager(this);
    }

    /**
     * @return player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return ui object
     */
    public PlayerUI getUi() {
        return ui;
    }

    public void windowFocusLost() {
        player.resetDirections();
    }

    /**
     * Resets the game
     */
    public void resetAll() {
        paused = false;
        inventoryOn = false;
        player.resetAll();
        placer.resetAll();
        inventoryManager.resetAll();
    }

    @Override
    public void update() {
        if (!paused) {
            levelManager.update();
            placer.update();
            //npcManager.update();
            player.update();
            inventoryManager.update();
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
            inventoryManager.draw(g);
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

            case KeyEvent.VK_SPACE -> player.setAttacking();

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
            case KeyEvent.VK_Q -> {
                if (!paused) {
                    ui.toggleStatus();
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
