package inputs;

import gamestates.Gamestate;
import main.Game;
import main.GamePanel;
import main.InventoryPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private JPanel targetPanel;
    private Game game;

    /**
     * Creates keyboard listener connected to the target panel
     * @param targetPanel x
     */
    public KeyboardInputs(JPanel targetPanel, Game game){
        this.targetPanel = targetPanel;
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                game.getMenu().keyPressed(e, targetPanel);
                break;
            case PLAYING:
                game.getPlaying().keyPressed(e, targetPanel);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                game.getMenu().keyReleased(e, targetPanel);
                break;
            case PLAYING:
                game.getPlaying().keyReleased(e, targetPanel);
                break;
            default:
                break;
        }
    }
}
