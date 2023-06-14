package locations;

import gamestates.Playing;
import main.GameModel;
import playerclasses.Player;

import java.awt.*;

public class EventChecker {

    Playing playing;
    Rectangle eventArea;
    int eventRectX, eventRectY;
    Player player;


    public EventChecker(Playing playing) {

        eventArea = new Rectangle();
        eventArea.x = GameModel.tileSize/4;
        eventArea.y = GameModel.tileSize/4;
        eventArea.width = GameModel.tileSize/2;
        eventArea.height = GameModel.tileSize/2;
        eventRectX = eventArea.x;
        eventRectY = eventArea.y;

        this.playing = playing;
        player = playing.getPlayer();
    }

    public void checkEvent() {

        switch (playing.getLevelManager().getCurrentLevelId()) {
            case 0 -> {
                if (hit(24, 23, 5))
                    takeDamage();
                if (hit(21, 23, 5))
                    restoreHealth();
            }
        }
    }

    public void draw (Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(eventArea.x,eventArea.y, eventArea.width, eventArea.height);
    }

    public boolean hit (int col, int row, int dir) {

        boolean hit = false;
        // Checking the collision ahead
        player.setHitAreaX(player.getWorldX() + player.getHitArea().x);
        player.setHitAreaY(player.getWorldY() + player.getHitArea().y);
        eventArea.x = col* GameModel.tileSize + eventRectX;
        eventArea.y = row* GameModel.tileSize + eventRectY;


        if (player.getHitArea().intersects(eventArea)) {
            if (player.getDirection() == dir || dir == 5) {
                hit = true;
            }
        }

        // Restoring the hit area
        player.setHitAreaX(player.getHitAreaDefaultX());
        player.setHitAreaY(player.getHitAreaDefaultY());
        eventArea.x = eventRectX;
        eventArea.y = eventRectY;

        return hit;
    }

    public void takeDamage() {
        player.takeDamage(1);
    }

    public void restoreHealth() {
        player.healthUp(1);
    }
}
