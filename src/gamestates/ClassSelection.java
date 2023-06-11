package gamestates;

import buttonUi.Buttons.ClassButton;
import buttonUi.Buttons.MenuButton;
import buttonUi.GameButton;
import main.Game;
import utilities.Load;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilities.Constants.PlayerConstants.*;
import static utilities.Constants.UI.MenuButtons.B_HEIGHT;
import static utilities.Constants.UI.MenuButtons.B_WIDTH;


public class ClassSelection extends State implements Statemethods {

    private BufferedImage backgroundImage;
    private ClassButton[] buttons = new ClassButton[3];
    private MenuButton returnButton;
    private Graphics g;

    /**
     * State for class selection
     * @param game
     */
    public ClassSelection(Game game) {
        super(game);
        backgroundImage = Load.GetSpriteImg("UI/Startscreen.png");
        loadButtons();
    }

    private void loadButtons() {

        buttons[0] = new ClassButton(Game.screenWidth/4, (int) (Game.screenHeight/1.8),
                Game.tileSize, 100, WARRIOR);
        buttons[0].setText("WARRIOR");
        buttons[1] = new ClassButton(Game.screenWidth/2 - Game.tileSize/2, (int) (Game.screenHeight/1.8),
                Game.tileSize, 100, ARCHER);
        buttons[1].setText("ARCHER");
        buttons[2] = new ClassButton((int) (Game.screenWidth/1.5), (int) (Game.screenHeight/1.8),
                Game.tileSize, 100, BARD);
        buttons[2].setText("BARD");

        returnButton = new MenuButton(Game.screenWidth/2, (int) (Game.screenHeight/1.3),
                B_WIDTH, B_WIDTH, 2, Gamestate.MENU);

    }


    @Override
    public void update() {
        returnButton.update();
    }

    /**
     * Draw the class selection state
     * @param g Graphics object
     */
    @Override
    public void draw(Graphics g) {
        this.g = g;

        // Draw background image
        g.drawImage(backgroundImage, 0, 0, Game.screenWidth, Game.screenHeight, null);

        // Set font and color
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.WHITE);

        // Draw title
        String title = "Select your class!";
        int titleX = getCenterTextX(title);
        int titleY = (int) (Game.screenHeight / 2);
        g.drawString(title, titleX, titleY);

        for(ClassButton button : buttons) {
            button.draw(g);
        }

        returnButton.draw(g);
    }

    public int getCenterTextX(String text) {
        int length = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        int x = Game.screenWidth/2 - length/2;
        return x;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(ClassButton button : buttons) {
            if (isInOBorder(e, button)) {
                button.setMousePressed(true);
                break;
            }
        }
        if (isInOBorder(e, returnButton))
            returnButton.setMousePressed(true);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(ClassButton button : buttons) {
            if (isInOBorder(e, button)) {
                if (button.isMousePressed()) {
                    game.getPlaying().getPlayer().setClass(button.getGameClassClass());
                    setGameState(Gamestate.PLAYING);
                }
                break;
            }
        }
        if (isInOBorder(e, returnButton)) {
            if (returnButton.isMousePressed()) {
                setGameState(Gamestate.MENU);
            }
        }
    }

    private void resetButtons() {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
            returnButton.setMouseOver(false);
            if (isInOBorder(e, returnButton))
                returnButton.setMouseOver(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private boolean isInOBorder(MouseEvent e, GameButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}