package gamestates;

import inventory.InventoryManager;
import playerclasses.Player;
import main.Game;
import locations.LevelManager;
import buttonUi.Pause;
import playerclasses.PlayerUI;
import objects.ObjectManager;
import locations.CollisionChecker;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author Arsenijs
 * Class that handles the gameplay and controllers
 */
public class Playing extends State implements Statemethods {

    private Player player;
    private PlayerUI ui;
    private LevelManager levelManager;
    private ObjectManager placer;
    private CollisionChecker collisionChecker;
    private Pause pause;
    private InventoryManager inventoryManager;
    private boolean paused = false;
    private boolean inventoryOn = false;

    /**
     * State for playing the game. Creates LevelManager and loads game
     * @param game Game object
     */
    public Playing(Game game) {
        super(game);
        levelManager = new LevelManager(this);
        loadGame();
    }

    /**
     * Loads current level, creates object manager, collision checker, and puts player on the level
     */
    public void loadGame() {
        levelManager.setStartLevel(0);
        placer = new ObjectManager(this);
        collisionChecker = new CollisionChecker(levelManager);
        //npcManager = new NPCManager(this, collisionChecker);
        putPlayer();
        pause = new Pause(this);
    }

    /**
     * Puts player on a level, adds collision checker to it, draws UI and inventory
     */
    private void putPlayer() {
        switch (getLevelManager().getCurrentLevelId()) {
            case 0 -> player = new Player(22, 21, this);
        }
        player.addCollisionChecker(collisionChecker);
        ui = new PlayerUI(this);
        inventoryManager = new InventoryManager(this);
    }

    public void movePlayer(int origin) {
        switch (getLevelManager().getCurrentLevelId()) {
            case 0 -> {
                if (origin == 1)
                    player.setCoordinates(22, 19);
                else if (origin == 2)
                    player.setCoordinates(24, 20);
                else if (origin == 0)
                    player.setCoordinates(22, 21);
            }
            case 1 -> player.setCoordinates(3, 5);
            case 2 -> player.setCoordinates(4, 5);
        }
        collisionChecker.updateLevel();
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
        levelManager.setStartLevel(0);
        movePlayer(0);
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
                    ui.toggleStats();
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
