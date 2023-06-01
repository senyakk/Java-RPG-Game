package creatures;

import equipment.Object;
import utilz.SaveLoad;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.PlayerConstants.WALKING_DOWN;

public class Player extends Creature {

    // SPRITES AND MOTION SETTINGS
    private BufferedImage[][] animations;
    private int animTick, animIndex, animSpeed = 25;
    private int playerAction;
    private boolean left, up, right, down;
    private boolean moving = false;
    private float speed = 2.0f;

    // PLAYER VARIABLES
    private int level;
    private int strength;
    private int defense;
    private int charisma;
    private int exp;
    private int nextLevelExp;
    private Object weapon;
    private Object shield;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
        setDefaultVariables();
    }

    public void setDefaultVariables() {
        strength = 1;
        charisma = 1;
        defense = 1;
        exp = 0;
        nextLevelExp = 5;
    }

    public void update() {
        updatePos();
        updateAnimation();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animIndex], (int)x, (int)y, 128, 144, null);
    }

    private void updatePos() {

        moving = false;

        if (left && !right) {
            x-=speed;
            moving = true;
        } else if (right && !left) {
            x+=speed;
            moving = true;
        }

        if (up && !down) {
            y-= speed;
            moving = true;
        } else if (down && !up) {
            y+= speed;
            moving = true;
        }
    }

    private void setAnimation() {

        int startAnim = playerAction;

        if (moving) {
            if (left && !right) {
                playerAction = WALKING_LEFT;
            } else if (right && !left) {
                playerAction = WALKING_RIGHT;
            }

            if (up && !down) {
                playerAction = WALKING_UP;
            } else if (down && !up) {
                playerAction = WALKING_DOWN;
            }
        }

        if (startAnim != playerAction){
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
            int spriteAmount = moving ? getSpriteAmount(playerAction) : 0;
            if (animIndex >= spriteAmount) {
                animIndex = 0;
            }
        }
    }

    private void loadAnimations() {
            BufferedImage image = SaveLoad.GetSpriteImg(SaveLoad.PLAYER_IMAGE);
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
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void resetDirections() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
}
