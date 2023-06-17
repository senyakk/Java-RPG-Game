package locations;

import gamestates.Playing;
import main.GameModel;
import playerclasses.PlayerModel;

import java.awt.*;

import static utilities.Constants.Direction.*;
import static utilities.Constants.GameLanguage.DUTCH;
import static utilities.Constants.GameLanguage.ENGLISH;

public class EventChecker {

    Playing playing;
    Rectangle eventArea;
    int eventRectX, eventRectY;
    PlayerModel player;


    public EventChecker(Playing playing) {

        eventArea = new Rectangle();
        eventArea.x = GameModel.tileSize/4;
        eventArea.y = GameModel.tileSize/4;
        eventArea.width = (int) (GameModel.tileSize/1.5);
        eventArea.height = (int) (GameModel.tileSize/1.5);
        eventRectX = eventArea.x;
        eventRectY = eventArea.y;

        this.playing = playing;
        player = playing.getPlayer();
    }

    public void checkEvent() {

        switch (playing.getLevelManager().getCurrentLevelId()) {
            case 2 -> {
                if ( hit(5, 1, UP)) {
                    switch (playing.getGameModel().getLanguage()) {
                        case ENGLISH ->
                                playing.getUi().showMessage("You're burning!");
                        case DUTCH ->
                                playing.getUi().showMessage("Je verbrandt!");
                    }
                    takeDamage();
                }

                if (hit(4, 3, 5)) {
                    switch (playing.getGameModel().getLanguage()) {
                        case ENGLISH ->
                                playing.getUi().showMessage("You've taken a rest");
                        case DUTCH ->
                                playing.getUi().showMessage("Je bent aan het rusten");
                    }
                    restoreHealth();
                }
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
