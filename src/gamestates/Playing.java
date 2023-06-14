package gamestates;

import inventory.InventoryManager;
import locations.EventChecker;
import main.GameController;
import main.GameModel;
import playerclasses.Player;
import main.Game;
import locations.LevelManager;
import playerclasses.PlayerController;
import playerclasses.PlayingUI;
import objects.ObjectManager;
import locations.CollisionChecker;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author Arsenijs
 * State for playing the game.
 * Class that acts a central coordinator, bringing together the model, view, and controller
 */
public class Playing extends State {

    // MODEL COMPONENTS
    private Player player;
    // holds information about the player's position, attributes, and state.
    private LevelManager levelManager;
    // manages the game levels and handles loading and switching between different levels.
    private ObjectManager objectManager;
    // manages the game objects and their behavior within the game.
    private CollisionChecker collisionChecker;
    // checks for collisions between game entities and handles collision resolution.
    private InventoryManager inventoryManager;
    // manages the player's inventory and handles interactions with inventory items.
    private EventChecker eventChecker;
    // manages the events hapenning in the level

    // CONTROLLER COMPONENTS
    private PlayerController playerController;
    //  handles player input and translates it into actions and movements for the player character.

    // VIEW COMPONENT
    private PlayingUI ui;
    // renders the game interface and responds to user input for the UI.

    private boolean paused = false;

    /**
     * State for playing the game.
     */
    public Playing(GameModel gameModel) {
        super(gameModel);
        loadGame();
    }

    /**
     * Creates LevelManager, loads current level,
     * connects object manager, collision checker, and puts player on the level
     */
    public void loadGame() {
        levelManager = new LevelManager(this);
        levelManager.setStartLevel(0);
        objectManager = new ObjectManager(this);
        collisionChecker = new CollisionChecker(levelManager);
        //npcManager = new NPCManager(this, collisionChecker);
        putPlayer();
        eventChecker = new EventChecker(this);

    }

    /**
     * Puts player on a level, adds collision checker to it, adds UI and inventory
     */
    private void putPlayer() {
        switch (getLevelManager().getCurrentLevelId()) {
            case 0 -> player = Player.getInstance(22, 21, this);
        }
        playerController = new PlayerController(this);
        player.addCollisionChecker(collisionChecker);
        ui = new PlayingUI(this);
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
        player.resetAll();
        ui.resetAll();
        objectManager.resetAll();
        inventoryManager.resetAll();
    }

    @Override
    public void update() {
        if (!paused) {
            levelManager.update();
            eventChecker.checkEvent();
            objectManager.update();
            //npcManager.update();
            player.update();
            inventoryManager.update();
        } else {
            ui.getPause().update();
        }
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g, player);
        eventChecker.draw(g);
        objectManager.drawObjects(g);
        player.render(g);
        ui.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Player update
        playerController.handleKeyPressed(e);

        // Game update
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            paused = !paused;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // View Update
        ui.keyReleased(e);
        // Player Update
        playerController.handleKeyReleased(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ui.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ui.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ui.mouseMoved(e);
    }

    public void unpause() {
        paused = false;
    }

    public void mouseDragged(MouseEvent e) {
        ui.mouseDragged(e);
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }


    public boolean isPaused() {
        return paused;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
}
