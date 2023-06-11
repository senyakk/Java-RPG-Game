package playerclasses;

import gamestates.Playing;
import main.Game;

import java.awt.*;


public class PlayerUI {

    private Playing playing;
    private Font arial_40, arial_80B;
    private String message = "";


    public PlayerUI(Playing playing) {
        this.playing = playing;

        arial_40 = new Font ("Arial", Font.PLAIN, 40);
        arial_80B = new Font ("Arial", Font.BOLD, 80);
    }

    public void draw(Graphics g) {

        g.setFont(arial_40);
        g.setColor(Color.white);
        g.drawString("UI placeholder", (int) (55 * Game.scale), (int) (50 * Game.scale));

    }

}