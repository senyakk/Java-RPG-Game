package playerclasses;

import gamestates.Playing;
import main.Game;
import npcs.Creature;
import objects.GameObject;
import objects.objectsclasses.Bow;
import objects.objectsclasses.Flower;
import objects.objectsclasses.Sword;
import objects.objectsclasses.Weapon;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.Direction.*;
import static utilities.Constants.PlayerConstants.*;

/**
 * @author arseniy
 * Class that handles the player and it's interaction with the game
 */
public class Player extends Creature {

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

    /**
     * Creates a player in a game
     * @param playerX x coordinates in the world
     * @param playerY y coordinates in the world
     * @param playing game Playing state
     */
    public Player(int playerX, int playerY, Playing playing) {
        super(playerX, playerY, Game.tileSize, Game.tileSize);
        this.playing = playing;
        this.playerX = playerX;
        this.playerY = playerY;
        screenX = Game.screenWidth/2 - (Game.tileSize/2);
        screenY = Game.screenHeight/2 - (Game.tileSize/2);
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
                speed = Game.scale;
                currentWeapon = new Sword(-1,-1);

            }
            case ARCHER -> {
                maxHealth = 4;
                strength = 2;
                defense = 3;
                charisma = 2;
                speed = 2.0f * Game.scale;
                currentWeapon = new Bow(-1,-1);
            }
            case BARD -> {
                maxHealth = 4;
                strength = 1;
                defense = 2;
                charisma = 3;
                speed = 1.5f * Game.scale;
                currentWeapon = new Flower(-1,-1);
            }
        }
        attackArea.width = Game.tileSize/2;
        attackArea.height = Game.tileSize/4;
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
        //GameObject object = collisionChecker.checkObject(this);
        //pickUpObject(object);
        if (attacking)
            attack();
        updateAnimation();
        setAnimation();
    }


    /**
     * Draw player
     * @param g Graphics object
     */
    public void render(Graphics g) {
        float drawX, drawY;
        if (lockedScreen) {
            drawX = worldX;
            drawY = worldY;
        }
        else {
            drawX = screenX;
            drawY = screenY;
        }

        // Draws the player
        g.drawImage(animations[state][animIndex], (int)drawX, (int) drawY,
                Game.tileSize, Game.tileSize, null);
        // Draws hitbox of the player
        drawPlayerHitArea(g, drawX, drawY);
        if (attacking)
            drawAttackHitArea(g, drawX, drawY);
    }

    private void drawAttackHitArea(Graphics g, float drawX, float drawY) {
        g.setColor(Color.ORANGE);
        switch (walkDir) {
            case DOWN ->
                    g.drawRect((int) (drawX + solidArea.x), (int) (drawY + solidArea.y + solidArea.height),
                            attackArea.height, attackArea.width);
            case UP ->
                    g.drawRect((int) (drawX + solidArea.x), (int) (drawY + solidArea.y - attackArea.height),
                            attackArea.height, attackArea.width);
            case LEFT ->
                    g.drawRect((int) (drawX + solidArea.x - attackArea.width),
                            (int) (drawY + solidArea.y + solidArea.height/2),
                            attackArea.width, attackArea.height);
            case RIGHT ->
                    g.drawRect((int) (drawX + attackArea.width),
                            (int) (drawY + solidArea.y + solidArea.height/2),
                            attackArea.width, attackArea.height);
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

        if ((!left && !right && !up && !down) || (left && right) || (up && down)) {
            return;
        }

        float xSpeed = 0, ySpeed = 0;

        if (left) {
            xSpeed -= speed;
        } else if (right) {
            xSpeed += speed;
        }

        if (up) {
            ySpeed -= speed;
        } else if (down) {
            ySpeed += speed;
        }

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
        worldX = (playerX * Game.tileSize) - width /2 + (float) Game.tileSize / 2;
        worldY = (playerY * Game.tileSize) - width /2 + (float) Game.tileSize / 2;
    }

    /**
     * Draw player's hitbox
     * @param g Graphics object
     */
    protected void drawPlayerHitArea(Graphics g, float drawX, float drawY) {
        g.setColor(Color.PINK);
        g.drawRect((int) (drawX + solidArea.x), (int) (drawY + solidArea.y), solidArea.width, solidArea.height);
    }

    public void setAttacking() {
        attacking = true;
    }

    /**
     * @return Player's x position on the screen
     */
    public int getScreenX() {
        return screenX;
    }

    /**
     * @return Player's y position on the screen
     */
    public int getScreenY() {
        return screenY;
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

    public String getPlayerClass() {
        switch (playerClass) {
            case WARRIOR -> {
                return "Warrior";
            }
            case ARCHER -> {
                return "Archer";
            }
            case BARD -> {
                return "Bard";
            }
        }
        return null;
    }

    public void lockScreen() {
        lockedScreen = true;
    }
    public void unlockScreen() {
        lockedScreen = false;
    }


}
