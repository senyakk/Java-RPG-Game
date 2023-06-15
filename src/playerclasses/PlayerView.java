package playerclasses;

import main.GameModel;

import java.awt.*;

import static utilities.Constants.Direction.*;
public class PlayerView {

    /**
     * Draw player
     * @param g Graphics object
     */
    public void render(Graphics g, PlayerModel player) {
        float drawX, drawY;
        if (player.isLockedScreen()) {
            drawX = player.getWorldX();
            drawY = player.getWorldY();
        }
        else {
            drawX = player.getScreenX();
            drawY = player.getScreenY();
        }

        // Draws the player
        g.drawImage(player.getAnimations()[player.getState()][player.getAnimIndex()], (int)drawX, (int) drawY,
                GameModel.tileSize, GameModel.tileSize, null);
        // Draws hitbox of the player
        Rectangle hitArea = player.getHitArea();
        drawPlayerHitArea(g, drawX, drawY, hitArea);
        if (player.isAttacking()) {
            Rectangle attackArea = player.getAttackArea();
            drawAttackHitArea(g, drawX, drawY, hitArea, attackArea, player.getWalkDir());
        }
    }

    /**
     * Draw player's hitbox
     * @param g Graphics object
     */
    protected void drawPlayerHitArea(Graphics g, float drawX, float drawY, Rectangle hitArea) {
        g.setColor(Color.PINK);
        g.drawRect((int) (drawX + hitArea.x), (int) (drawY + hitArea.y), hitArea.width, hitArea.height);
    }

    private void drawAttackHitArea(Graphics g, float drawX, float drawY,
                                   Rectangle hitArea, Rectangle attackArea, int walkDir) {
        g.setColor(Color.ORANGE);
        switch (walkDir) {
            case DOWN ->
                    g.drawRect((int) (drawX + hitArea.x), (int) (drawY + hitArea.y + hitArea.height),
                            attackArea.height, attackArea.width);
            case UP ->
                    g.drawRect((int) (drawX + hitArea.x), (int) (drawY + hitArea.y - attackArea.height),
                            attackArea.height, attackArea.width);
            case LEFT ->
                    g.drawRect((int) (drawX + hitArea.x - attackArea.width),
                            (int) (drawY + hitArea.y + hitArea.height/2),
                            attackArea.width, attackArea.height);
            case RIGHT ->
                    g.drawRect((int) (drawX + attackArea.width),
                            (int) (drawY + hitArea.y + hitArea.height/2),
                            attackArea.width, attackArea.height);
        }
    }

}
