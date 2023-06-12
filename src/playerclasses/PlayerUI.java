package playerclasses;

import gamestates.Playing;
import main.Game;
import utilities.Load;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class PlayerUI {

    private Playing playing;
    private Font arial_40, arial_80B;
    private BufferedImage heart_full, heart_half, heart_blank;
    private boolean statusOn = false;
    private Graphics g;


    public PlayerUI(Playing playing) {
        this.playing = playing;

        arial_40 = new Font ("Arial", Font.PLAIN, 40);
        arial_80B = new Font ("Arial", Font.BOLD, 80);

        try {
            heart_full = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            heart_full = Load.scaleImage(heart_full, Game.tileSize/2, Game.tileSize/2);
            heart_half = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            heart_half = Load.scaleImage(heart_half, Game.tileSize/2, Game.tileSize/2);
            heart_blank = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
            heart_blank = Load.scaleImage(heart_blank, Game.tileSize/2, Game.tileSize/2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        this.g = g;

        g.setFont(arial_40);
        g.setColor(Color.white);
        drawPlayerLife();
        if (statusOn)
            drawCharacterScreen();
    }

    private void drawCharacterScreen() {
        int frX = Game.tileSize / 2;
        int frY = Game.tileSize;
        int frW = Game.tileSize * 2;
        int frH = Game.tileSize * 3;
        drawWindow(frX, frY, frW, frH);

        g.setColor(Color.white);
        int fontSize = (int) (Game.scale*16);
        g.setFont(g.getFont().deriveFont( Font.PLAIN, fontSize));
        String playerClass = playing.getPlayer().getPlayerClass();
        g.drawString(playerClass, frX + xAlignCenterText(playerClass, frW), (int) (frY + Game.scale * 20));

        g.setFont(g.getFont().deriveFont( Font.PLAIN, (float) (fontSize/1.25)));

        int textX = (int) (frX + Game.scale * 8);
        int textY = (int) (frY + Game.tileSize / 1.5);
        final int lineHeight = (int) (fontSize/1.1);

        String[] attributes = {
                "Level", "XP", "Life", "Strength", "Attack",
                "Defense", "Charisma", "Speed", "Weapon"
        };

        int rightX = (int) (frX + frW - Game.scale*8);
        textY = (int) (frY + Game.tileSize / 1.5);

        for (String attribute : attributes) {
            String value = "";
            int valueX = rightX;

            switch (attribute) {
                case "Level":
                    value = String.valueOf(playing.getPlayer().getLevel());
                    break;
                case "Life":
                    value = String.valueOf(playing.getPlayer().getCurrentHealth() + "/" + playing.getPlayer().getMaxHealth());
                    break;
                case "Strength":
                    value = String.valueOf(playing.getPlayer().getStrength());
                    break;
                case "Attack":
                    value = String.valueOf(playing.getPlayer().getAttack());
                    break;
                case "Defense":
                    value = String.valueOf(playing.getPlayer().getDefense());
                    break;
                case "Charisma":
                    value = String.valueOf(playing.getPlayer().getCharisma());
                    break;
                case "Speed":
                    value = String.valueOf(playing.getPlayer().getSpeed());
                    break;
                case "XP":
                    value = String.valueOf(playing.getPlayer().getExp() + "/" + String.valueOf(playing.getPlayer().getNextLevelExp()));
                    break;
                case "Weapon":
                    valueX = rightX - Game.tileSize / 4;
                    textY += 10;
                    g.drawImage(Load.scaleImage(playing.getPlayer().getCurrentWeapon().getImage(),
                                    Game.tileSize / 4, Game.tileSize / 4),
                            valueX, textY - Game.tileSize / 5, null);
                    break;
            }

            g.drawString(attribute, textX, textY);
            valueX = xAlignRightText(value, valueX);
            g.drawString(value, valueX, textY);
            textY += lineHeight;
        }
    }

    private void drawWindow(int x, int y, int width, int height) {
        Color color = new Color (0, 0,0 , 210);
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g.setColor(color);
        g.drawRoundRect((int) (x+(Game.scale*2)), (int) (y+(Game.scale*2)),
                (int) (width-(Game.scale*5)), (int) (height-(Game.scale*5)),
                (int) (Game.scale*10), (int) (Game.scale*10));
    }

    private void drawPlayerLife() {
        int x = Game.tileSize/2;
        int y = Game.tileSize/4;
        int i = 0;

        while (i < playing.getPlayer().getMaxHealth()/2) {
            g.drawImage(heart_blank, x, y, null);
            i++;
            x+= Game.tileSize/2;
        }

        x = Game.tileSize/2;
        y = Game.tileSize/4;
        i = 0;

        while (i < playing.getPlayer().getCurrentHealth()) {
            g.drawImage(heart_half, x, y, null);
            i++;
            if (i <playing.getPlayer().getCurrentHealth())
                g.drawImage(heart_full, x, y, null);
            i++;
            x+= Game.tileSize/2;
        }
    }

    public void toggleStatus() {
        statusOn = !statusOn;
    }

    private int xAlignRightText (String text, int rightX) {
        int length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        int x = rightX - length;
        return x;
    }

    private int xAlignCenterText (String text, int width) {
        int length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        int x = width/2 - length/2;
        return x;
    }
}