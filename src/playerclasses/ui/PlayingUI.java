package playerclasses.ui;

import gamestates.Playing;
import main.GameModel;
import utilities.Load;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static utilities.Constants.GameLanguage.*;

/**
 * @author Arsenijs
 * Class for UI related logic to handle the View
 */
public class PlayingUI {

    private Playing playing;
    private Pause pause;
    private Font arial_40, arial_80B;
    private BufferedImage heart_full, heart_half, heart_blank;
    private boolean statsOn = false;
    private boolean inventoryOn = false;
    private boolean messageOn = false;
    private String message = "";
    private int messageCount = 0;

    private Graphics g;


    public PlayingUI(Playing playing) {
        this.playing = playing;

        arial_40 = new Font ("Arial", Font.PLAIN, 40);
        arial_80B = new Font ("Arial", Font.BOLD, 80);

        try {
            heart_full = ImageIO.read(getClass().getResourceAsStream("/items/heart_full.png"));
            heart_full = Load.scaleImage(heart_full, GameModel.tileSize/2, GameModel.tileSize/2);
            heart_half = ImageIO.read(getClass().getResourceAsStream("/items/heart_half.png"));
            heart_half = Load.scaleImage(heart_half, GameModel.tileSize/2, GameModel.tileSize/2);
            heart_blank = ImageIO.read(getClass().getResourceAsStream("/items/heart_blank.png"));
            heart_blank = Load.scaleImage(heart_blank, GameModel.tileSize/2, GameModel.tileSize/2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pause = new Pause(playing);
    }

    public void draw(Graphics g) {
        this.g = g;

        g.setFont(arial_40);
        g.setColor(Color.white);
        drawPlayerLife();
        if (statsOn)
            drawCharacterScreen();
        if (messageOn)
            drawMessage();
        if (inventoryOn)
            playing.getInventoryManager().draw(g);
        if (playing.isPaused()) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, GameModel.screenWidth, GameModel.screenHeight);
            pause.draw(g);
        }

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    private void drawMessage() {
        g.setFont(g.getFont().deriveFont(30F));
        g.drawString(message,
                xAlignCenterText(message, GameModel.screenWidth) + playing.getPlayer().getScreenX()
                        - GameModel.screenWidth/2 + GameModel.tileSize/2,
                playing.getPlayer().getScreenY());
        messageCount++;

        if (messageCount > 120) {
            messageCount = 0;
            messageOn = false;
        }
    }

    private void drawCharacterScreen() {
        int frX = GameModel.tileSize / 2;
        int frY = GameModel.tileSize;
        int frW = GameModel.tileSize * 2;
        int frH = GameModel.tileSize * 3;
        drawWindow(frX, frY, frW, frH);

        g.setColor(Color.white);
        int fontSize = (int) (GameModel.scale*16);
        g.setFont(g.getFont().deriveFont( Font.PLAIN, fontSize));
        String playerClass = playing.getPlayer().getPlayerClass();
        g.drawString(playerClass, frX + xAlignCenterText(playerClass, frW), (int) (frY + GameModel.scale * 20));

        g.setFont(g.getFont().deriveFont( Font.PLAIN, (float) (fontSize/1.25)));

        int textX = (int) (frX + GameModel.scale * 8);
        int textY = (int) (frY + GameModel.tileSize / 1.5);
        final int lineHeight = (int) (fontSize/1.1);

        String[] attributes = new String[]{
                "Level", "XP", "Life", "Strength", "Attack",
                "Defense", "Charisma", "Speed", "Weapon"
        };

        int rightX = (int) (frX + frW - GameModel.scale*8);
        textY = (int) (frY + GameModel.tileSize / 1.5);

        for (String attribute : attributes) {
            String value = "";
            int valueX = rightX;

            Map<String, String> attributeTranslations = new HashMap<>();

            switch (playing.getGameModel().getLanguage()) {
                case ENGLISH -> {
                    attributeTranslations.put("Level", "Level");
                    attributeTranslations.put("Life", "Life");
                    attributeTranslations.put("Strength", "Strength");
                    attributeTranslations.put("Attack", "Attack");
                    attributeTranslations.put("Defense", "Defense");
                    attributeTranslations.put("Charisma", "Charisma");
                    attributeTranslations.put("Speed", "Speed");
                    attributeTranslations.put("XP", "XP");
                    attributeTranslations.put("Weapon", "Weapon");
                }
                case DUTCH -> {
                    attributeTranslations.put("Level", "Niveau");
                    attributeTranslations.put("Life", "Leven");
                    attributeTranslations.put("Strength", "Kracht");
                    attributeTranslations.put("Attack", "Aanval");
                    attributeTranslations.put("Defense", "Verdediging");
                    attributeTranslations.put("Charisma", "Charisma");
                    attributeTranslations.put("Speed", "Snelheid");
                    attributeTranslations.put("XP", "XP");
                    attributeTranslations.put("Weapon", "Wapen");
                }
            }

            String translatedAttribute = attributeTranslations.getOrDefault(attribute, attribute);

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
                    value = playing.getPlayer().getExp() + "/" + String.valueOf(playing.getPlayer().getNextLevelExp());
                    break;
                case "Weapon":
                    valueX = rightX - GameModel.tileSize / 4;
                    textY += 10;
                    g.drawImage(Load.scaleImage(playing.getPlayer().getCurrentWeapon().getImage(),
                                    GameModel.tileSize / 4, GameModel.tileSize / 4),
                            valueX, textY - GameModel.tileSize / 5, null);
                    break;
            }

            g.drawString(translatedAttribute, textX, textY);
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
        g.drawRoundRect((int) (x+(GameModel.scale*2)), (int) (y+(GameModel.scale*2)),
                (int) (width-(GameModel.scale*5)), (int) (height-(GameModel.scale*5)),
                (int) (GameModel.scale*10), (int) (GameModel.scale*10));
    }

    private void drawPlayerLife() {
        int x = GameModel.tileSize / 2;
        int y = GameModel.tileSize / 4;
        int i = 0;

        while (i < playing.getPlayer().getMaxHealth() / 2) {
            g.drawImage(heart_blank, x, y, null);
            i++;
            x += GameModel.tileSize / 2;
        }

        x = GameModel.tileSize / 2;
        y = GameModel.tileSize / 4;
        i = 0;

        while (i < playing.getPlayer().getCurrentHealth()) {
            g.drawImage(heart_half, x, y, null);
            i++;
            if (i < playing.getPlayer().getCurrentHealth())
                g.drawImage(heart_full, x, y, null);
            i++;
            x += GameModel.tileSize / 2;
        }
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


    public Pause getPause() {
        return pause;
    }

    public void resetAll() {
        statsOn = false;
        inventoryOn = false;
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            // Inventory switch
            case KeyEvent.VK_I -> {
                if (!playing.isPaused()) {
                    inventoryOn = !inventoryOn;
                }
            }
            // Stats switch
            case KeyEvent.VK_Q -> {
                if (!playing.isPaused()) {
                    statsOn = !statsOn;
                }
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        if (playing.isPaused()) {
            pause.mousePressed(e);
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (playing.isPaused()) {
            pause.mouseDragged(e);
        }
    }

    public void mouseMoved(MouseEvent e) {
        if (playing.isPaused()) {
            pause.mouseMoved(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (playing.isPaused()) {
            pause.mouseReleased(e);
        }
    }

}