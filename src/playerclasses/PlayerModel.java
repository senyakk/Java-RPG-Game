package playerclasses;

import gamestates.Playing;
import main.GameModel;
import npcs.Creature;
import objects.GameObject;
import objects.objectsclasses.Bow;
import objects.objectsclasses.Flower;
import objects.objectsclasses.Sword;
import objects.objectsclasses.Weapon;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.AudioPlayer.*;
import static utilities.Constants.Direction.*;
import static utilities.Constants.GameLanguage.*;
import static utilities.Constants.PlayerConstants.*;

/**
 * @author Arsenijs
 * Class that handles the player, and it's interaction with the game
 */
public class PlayerModel extends Creature {

    private Playing playing;

    // Starting world coordinates
    private int playerX, playerY;


    // SPRITES AND MOTION SETTINGS
    private BufferedImage[][] animations;
    private boolean left, up, right, down;

    private boolean lockedScreen = false;


    // PLAYER CLASSES
    private int playerClass;

    // PLAYER VARIABLES
    private int maxHealth;
    private int currentHealth;
    private float attack;
    private int level;
    private int strength;
    private int defense;
    private int charisma;
    private int exp;
    private int nextLevelExp;

    private Weapon currentWeapon;
    private boolean attacking = false;
    private int attackCounter = 0;
    private Rectangle attackArea = new Rectangle(0,0,0,0);

    private static PlayerModel instance;

    /**
     * Creates a singleton player instance in a game. If instance exists, returns existing player
     * @param playerX x coordinates in the world
     * @param playerY y coordinates in the world
     * @param playing game Playing state
     */
    public static PlayerModel getInstance(int playerX, int playerY, Playing playing) {
        if (instance == null) {
            instance = new PlayerModel(playerX, playerY, playing);
        }
        return instance;
    }

    private PlayerModel(int playerX, int playerY, Playing playing) {
        super(playerX, playerY, GameModel.tileSize, GameModel.tileSize);
        this.playing = playing;
        this.playerX = playerX;
        this.playerY = playerY;
        screenX = GameModel.screenWidth/2 - (GameModel.tileSize/2);
        screenY = GameModel.screenHeight/2 - (GameModel.tileSize/2);
        setDefaultVariables();
        loadAnimations();
        initHitArea(22, 24, 18, 32);
    }


    /**
     * Sets default variables of thr player
     */
    public void setDefaultVariables() {

        switch (playerClass) {
            case WARRIOR -> {
                maxHealth = 6;
                strength = 3;
                defense = 2;
                charisma = 1;
                speed = GameModel.scale;
                currentWeapon = new Sword(-1,-1);

            }
            case ARCHER -> {
                maxHealth = 4;
                strength = 2;
                defense = 3;
                charisma = 2;
                speed = 2.0f * GameModel.scale;
                currentWeapon = new Bow(-1,-1);
            }
            case BARD -> {
                maxHealth = 4;
                strength = 1;
                defense = 2;
                charisma = 3;
                speed = 1.5f * GameModel.scale;
                currentWeapon = new Flower(-1,-1);
            }
        }
        attackArea.width = GameModel.tileSize/2;
        attackArea.height = GameModel.tileSize/4;
        currentHealth = maxHealth;
        attack = strength * currentWeapon.getAttack();
        level = 1;
        exp = 0;
        nextLevelExp = 5;
    }

    /**
     * Update position of the player, check collisions with tiles and objects, update and set animation
     */
    public void update() {
        updatePos();
        collisionOn = false;
        collisionChecker.checkTile(this);
        GameObject object = collisionChecker.checkObject(this);
        pickUpObject(object);
        if (attacking)
            attack();
        updateAnimation();
        setAnimation();

        //System.out.println("X: " + (int)(worldX/ GameModel.tileSize) + " Y: " + (int)(worldY/ GameModel.tileSize));
    }

    /**
     * Sends pickup information to inventory manager
     * @param itemID is the unique ID of the item corresponding to the GameObject
     * @author Cata Mihit
     */
    private void notifyInventoryManager(String itemID, String itemClass){
        playing.getInventoryManager().notifyPickup(itemID, itemClass);
    }

    /** Method for picking op object
     * @param object
     */
    private void pickUpObject(GameObject object) {
        if (object != null) {
            switch (object.getName()) {
                case "keyItem" -> {
                    playing.getGameModel().getAudioPlayer().playEffect(COIN);
                    playing.getUi().showMessage("Key picked!");
                    notifyInventoryManager("5", "GenericItem");
                    object.deactivate();
                }
                case "boots" -> {
                    speed+= 2;
                    playing.getGameModel().getAudioPlayer().playEffect(POWERUP);
                    playing.getUi().showMessage("Speed boost!");
                    notifyInventoryManager("6", "GenericItem");
                    object.deactivate();
                }
            }
        }
    }

    private void attack() {
        attackCounter++;

        if (attackCounter == 60) {
            attacking = false;
            attackCounter = 0;
        }
    }

    /**
     * Update movement position of the player
     */
    private void updatePos() {

        moving = false;

        if ((!left && !right && !up && !down) || (left && right) || (up && down))
            return;

        float xSpeed = 0, ySpeed = 0;

        if (left)
            xSpeed -= speed;
        else if (right)
            xSpeed += speed;

        if (up)
            ySpeed -= speed;
        else if (down)
            ySpeed += speed;

        if (!collisionOn) {
            this.worldX += xSpeed;
            this.worldY += ySpeed;
            moving = true;
        }
    }

    /**
     * Set player's animation
     */
    private void setAnimation() {

        int startAnim = state;

        if (moving) {
            if (left && !right) {
                state = WALKING_LEFT;
            } else if (right && !left) {
                state = WALKING_RIGHT;
            }

            if (up && !down) {
                state = WALKING_UP;
            } else if (down && !up) {
                state = WALKING_DOWN;
            }
        }

        if (startAnim != state){
            resetAnimation();
        }
    }

    /**
     * Reset player animation
     */
    private void resetAnimation() {
        animTick = 0;
        animIndex = 0;
    }

    /**
     * Update player's animation
     */
    private void updateAnimation() {
        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            int spriteAmount = moving ? getSpriteAmount(state) : 0;
            if (animIndex >= spriteAmount) {
                animIndex = 0;
            }
        }
    }

    /**
     * Load player sprites
     */
    private void loadAnimations() {
            BufferedImage image = Load.GetSpriteImg("characters/player_sprites.png");
            animations = new BufferedImage[4][4];
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = image.getSubimage(i * 64, j * 64, 64, 64);
                }
            }
    }

    /**
     * Resets directions of the player
     */
    public void resetDirections() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    /**
     * Resets all the variables of the player
     */
    public void resetAll() {
        resetDirections();
        setDefaultVariables();
        moving = false;
        attacking = false;
        walkDir = DOWN;
        worldX = (playerX * GameModel.tileSize) - width /2 + (float) GameModel.tileSize / 2;
        worldY = (playerY * GameModel.tileSize) - width /2 + (float) GameModel.tileSize / 2;
    }

    public int getPlayerClassAsInt(){
        return this.playerClass;
    }

    public String getPlayerClass() {
        switch (playerClass) {
            case WARRIOR -> {
                switch (playing.getGameModel().getLanguage()) {
                    case ENGLISH -> {
                        return "Warrior";
                    }
                    case DUTCH -> {
                        return "Krijger";
                    }
                }
            }
            case ARCHER -> {
                switch (playing.getGameModel().getLanguage()) {
                    case ENGLISH -> {
                        return "Archer";
                    }
                    case DUTCH -> {
                        return "Boogschutter";
                    }
                }
            }
            case BARD -> {
                switch (playing.getGameModel().getLanguage()) {
                    case ENGLISH -> {
                        return "Bard";
                    }
                    case DUTCH -> {
                        return "Bard";
                    }
                }
            }
        }
        return null;
    }

    /**
     * Set's player walking left direction
     * @param left true/false
     */
    public void setLeft(boolean left) {
        this.left = left;
        walkDir = LEFT;
    }

    /**
     * Set's player walking up direction
     * @param up true/false
     */
    public void setUp(boolean up) {
        this.up = up;
        walkDir = UP;
    }

    /**
     * Set's player walking right direction
     * @param right true/false
     */
    public void setRight(boolean right) {
        this.right = right;
        walkDir = RIGHT;
    }

    /**
     * Set's player walking down direction
     * @param down true/false
     */
    public void setDown(boolean down) {
        this.down = down;
        walkDir = DOWN;
    }


    public void lockScreen() {
        lockedScreen = true;
    }
    public void unlockScreen() {
        lockedScreen = false;
    }

    public void setCoordinates(float x, float y) {
        worldX = (x * GameModel.tileSize) - width /2 + (float) GameModel.tileSize / 2;;
        worldY = (y * GameModel.tileSize) - width /2 + (float) GameModel.tileSize / 2;;
    }

    public void takeDamage(int dmg) {
        if (currentHealth > 0)
            currentHealth-= dmg;
    }

    public void healthUp(int i) {
        if (currentHealth != maxHealth)
            currentHealth += i;
    }

    public void setAttacking() {
        attacking = true;
    }

    public boolean isLockedScreen() {
        return lockedScreen;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public Image[][] getAnimations() {
        return animations;
    }

    public int getAnimIndex() {
        return animIndex;
    }

    public int getWalkDir() {
        return walkDir;
    }

    public Rectangle getAttackArea() {
        return attackArea;
    }

    public int getScreenX() {
        if (!lockedScreen)
            return screenX;
        else
            return (int) worldX;
    }

    /**
     * @return Player's y position on the screen
     */
    public int getScreenY() {
        if (!lockedScreen)
            return screenY;
        else
            return (int) worldY;
    }

    public void setClass(int playerClass) {
        this.playerClass = playerClass;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public float getAttack() {
        return attack;
    }

    public int getLevel() {
        return level;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getExp() {
        return exp;
    }

    public int getNextLevelExp() {
        return nextLevelExp;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }
}
