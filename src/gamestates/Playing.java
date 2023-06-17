package gamestates;

import inventory.viewfiles.InventoryManager;
import locations.EventChecker;
import locations.LevelView;
import main.GameModel;
import npcs.NPCView;
import npcs.NPC_Manager;
import objects.ObjectView;
import playerclasses.PlayerModel;
import locations.LevelManager;
import playerclasses.PlayerController;
import playerclasses.PlayerView;
import playerclasses.ui.PlayingUI;
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
    private PlayerModel player;
    // holds information about the player's position, attributes, and state.
    private LevelManager levelManager;
    // manages the game levels and handles loading and switching between different levels.
    private ObjectManager objectManager;
    // manages the game objects and their behavior within the game.
    private NPC_Manager npcManager;
    // manages NPCs
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
    private PlayerView playerRenderer;
    // renders the player.
    private LevelView levelView;
    // renders the view
    private ObjectView objectView;
    // renders the objects
    private NPCView npcView;
    //renders NPCs
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
        objectManager = new ObjectManager(this);
        npcManager = new NPC_Manager(this);
        collisionChecker = new CollisionChecker(this);
        //npcManager = new NPCManager(this, collisionChecker);

        putPlayer();

        levelView = new LevelView(this);
        objectView = new ObjectView(this);
        npcView = new NPCView(this);
        eventChecker = new EventChecker(this);
    }

    /**
     * Puts player on a level, adds collision checker to it, adds UI and inventory
     */
    private void putPlayer() {
        if (getLevelManager().getCurrentLevelId() == 0)
            player = PlayerModel.getInstance(22, 21, this);

        playerController = new PlayerController(this);
        player.addCollisionChecker(collisionChecker);
        ui = new PlayingUI(this);
        playerRenderer = new PlayerView(this);
        inventoryManager = new InventoryManager(this);
    }


    /**
     * @return player object
     */
    public PlayerModel getPlayer() {
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
        levelManager.movePlayer(0);
        paused = false;
        player.resetAll();
        ui.resetAll();
        objectManager.resetAll();
        inventoryManager.resetAll();
        npcManager.resetAll();
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
            npcManager.update();
        } else {
            ui.getPause().update();
        }
    }

    @Override
    public void draw(Graphics g) {
        levelView.draw(g);
        //eventChecker.draw(g);
        objectView.drawObjects(g);
        playerRenderer.render(g);
        npcView.drawNPC(g);
        ui.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Player update
        playerController.handleKeyPressed(e);

        // Game update
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            paused = !paused;

        // Inventory view update
        inventoryManager.keyPressed(e);
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
        inventoryManager.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ui.mouseMoved(e);
        inventoryManager.mouseMoved(e);
    }

    public void mouseDragged(MouseEvent e) {
        ui.mouseDragged(e);
    }

    public void unpause() {
        paused = false;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public CollisionChecker getCollisionChecker() {
        return  collisionChecker;
    }

    public PlayingUI getUi() {
        return ui;
    }
    public boolean isPaused() {
        return paused;
    }
    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
    public ObjectManager getObjectManager() {
        return objectManager;
    }
    public NPC_Manager getNpcManager(){return npcManager;}

}
