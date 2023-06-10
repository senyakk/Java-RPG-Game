package playerclasses;

import equipment.Object;
import gamestates.Playing;
import main.Game;
import npcs.Creature;
import objects.GameObject;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.Direction.DOWN;
import static utilities.Constants.Direction.*;
import static utilities.Constants.PlayerConstants.*;
import static utilities.Constants.PlayerConstants.WALKING_DOWN;

public class Player extends Creature {

    private Playing playing;

    // CAMERA SETTINGS
    private final int screenX;
    public final int screenY;

    // SPRITES AND MOTION SETTINGS
    private BufferedImage[][] animations;
    private int playerAction;
    private boolean left, up, right, down;

    // PLAYER VARIABLES
    private int level;
    private int strength;
    private int defense;
    private int charisma;
    private int exp;
    private int nextLevelExp;
    private Object weapon;
    private Object shield;

    public Player(int worldX, int worldY, Playing playing) {
        super(worldX, worldY, Game.tileSize, Game.tileSize);
        this.playing = playing;
        screenX = Game.screenWidth/2 - (Game.tileSize/2);
        screenY = Game.screenHeight/2 - (Game.tileSize/2);
        setDefaultVariables();
        loadAnimations();
        initHitArea(22, 24, 18, 32);
    }

    public int getScreenX() {
        return screenX;
    }
    public int getScreenY() {
        return screenY;
    }

    public void setDefaultVariables() {
        speed = 2.0f * Game.scale;
        strength = 1;
        charisma = 1;
        defense = 1;
        exp = 0;
        nextLevelExp = 5;
    }

    public void update() {
        updatePos();

        // CHECK COLLISIONS
        collisionOn = false;
        collisionChecker.checkTile(this);
        GameObject object = collisionChecker.checkObject(this, true);
        //pickUpObject(object);

        updateAnimation();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[state][animIndex], (int)screenX, (int)screenY,
                Game.tileSize, Game.tileSize, null);
        drawPlayerHitArea(g);
    }

    private void updatePos() {

        moving = false;

        if ((!left && !right && !up && !down) || (left && right) || (up && down)) {
            return;
        }

        float xSpeed = 0, ySpeed = 0;

        if (left && !right) {
            xSpeed -= speed;
        } else if (right && !left) {
            xSpeed += speed;
        }

        if (up && !down) {
            ySpeed -= speed;
        } else if (down && !up) {
            ySpeed += speed;
        }

        if (!collisionOn) {
            this.worldX += xSpeed;
            this.worldY += ySpeed;
            moving = true;
        }
    }

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

    private void resetAnimation() {
        animTick = 0;
        animIndex = 0;
    }

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

    private void loadAnimations() {
            BufferedImage image = Load.GetSpriteImg(Load.PLAYER_IMAGE);
            animations = new BufferedImage[4][4];
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = image.getSubimage(i * 64, j * 64, 64, 64);
                }
            }
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
        walkDir = LEFT;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
        walkDir = UP;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
        walkDir = RIGHT;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
        walkDir = DOWN;
    }

    public void resetDirections() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
    public void resetAll() {
        resetDirections();
        moving = false;
        walkDir = DOWN;
        worldX = 23 * Game.tileSize;
        worldY = 21 * Game.tileSize;
    }

    protected void drawPlayerHitArea(Graphics g) {
        g.setColor(Color.PINK);
        g.drawRect(screenX + solidArea.x,screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
