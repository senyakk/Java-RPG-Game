package gamestates;

import buttonUi.AudioHandler;
import buttonUi.GameButton;
import buttonUi.Buttons.ReplayButton;
import main.GameModel;
import utilities.Load;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilities.Constants.GameLanguage.*;
import static utilities.Constants.UI.MenuButtons.*;
import static utilities.Constants.UI.PauseButtons.URM_SIZE;

/**
 * @author Arsenijs
 * Class that handles options state
 */
public class Options extends State {
    private AudioHandler audio;
    private BufferedImage backgroundImage, optionsBackgroundImage;
    private int bgX, bgY, bgW, bgH;
    private ReplayButton menuB;
    private GameButton languageButton;
    public Options(GameModel gameModel) {
        super(gameModel);
        loadImages();
        loadButtons();
        audio = gameModel.getAudio();
    }

    private void loadButtons() {
        int menuX = (int) (300 * GameModel.scale);
        int menuY = (int) (235 * GameModel.scale);
        menuB = new ReplayButton(menuX, menuY, URM_SIZE, URM_SIZE, 2);

        int langX = (int) (GameModel.screenWidth/3 - B_WIDTH);
        int langY = (int) (GameModel.screenHeight/2);
        languageButton = new GameButton(langX, langY, B_WIDTH, B_HEIGHT);
        languageButton.setText("Switch Language");
    }

    private void loadImages() {
        switch (gameModel.getLanguage()) {
            case ENGLISH -> {
                backgroundImage = Load.GetSpriteImg("UI/English/Startscreen.png");
                optionsBackgroundImage = Load.GetSpriteImg("UI/English/Options/options_background.png");
            }
            case DUTCH -> {
                // Dutch buttons here
                backgroundImage = Load.GetSpriteImg("UI/English/Startscreen.png");
                optionsBackgroundImage = Load.GetSpriteImg("UI/Dutch/Options/options_background_Dutch.png");
            }
        }

        bgW = (int) (optionsBackgroundImage.getWidth() * GameModel.scale/1.5);
        bgH = (int) (optionsBackgroundImage.getHeight() * GameModel.scale/1.5);
        bgX = GameModel.screenWidth / 2 - bgW / 2;
        bgY = (int) (33 * GameModel.scale);

    }

    @Override
    public void update() {
        menuB.update();
        languageButton.update();
        audio.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0,0, GameModel.screenWidth, GameModel.screenHeight, null);
        g.drawImage(optionsBackgroundImage, bgX,bgY, bgW, bgH, null);

        menuB.draw(g);
        languageButton.draw(g);
        audio.draw(g);

        // Draw game controls in the right
        String controls = "Controls: \nW-A-S-D to move\nI to open the inventory\nQ to see the characteristics\nE to enter/progress dialogue\nEsc to pause";
        // Split the controls string into individual lines
        String[] lines = controls.split("\n");
        Font font = new Font("Arial", Font.BOLD, (int) (16 * GameModel.scale));
        FontMetrics metrics = g.getFontMetrics(font);
        int textHeight = metrics.getHeight();
        int x = (int) (GameModel.screenWidth - GameModel.scale * metrics.stringWidth(controls) + 1150 * GameModel.scale);
        int y = GameModel.screenHeight / 2 - (textHeight * lines.length) / 2;
        g.setFont(font);
        g.setColor(Color.WHITE);

        // Draw each line of the controls string
        for (String line : lines) {
            g.drawString(line, x, y);
            y += textHeight; // Move to the next line
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gameModel.setGameState(Gamestate.MENU);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        audio.mouseDragged(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isInOBorder(e, menuB)) {
            menuB.setMousePressed(true);
        }
        if (isInOBorder(e, languageButton)) {
            languageButton.setMousePressed(true);
        }
        else {
            audio.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isInOBorder(e, menuB)) {
            if (menuB.isMousePressed()) {
                gameModel.setGameState(Gamestate.MENU);
            }
        }
        else if (isInOBorder(e, languageButton)) {
            if (languageButton.isMousePressed()) {
                gameModel.setLanguage((gameModel.getLanguage()+1) % 2);
                loadImages();
                System.out.println("Language: " + gameModel.getLanguage());
            }
        }
        else {
            audio.mouseReleased(e);
        }
        menuB.reset();
        languageButton.reset();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        menuB.setMouseOver(false);
        if (isInOBorder(e, menuB)) {
            menuB.setMouseOver(true);
        }
        else {
            audio.mouseMoved(e);
        }
    }

    private boolean isInOBorder(MouseEvent e, GameButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

}