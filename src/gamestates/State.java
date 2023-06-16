package gamestates;
import main.GameModel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class State {

    protected GameModel gameModel;
    public State (GameModel gameController) {
        this.gameModel = gameController;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    /**
     * Method for updating a given state and handling game's logic
     */
    public void update() {
        
    }

    /**
     * Method for drawing a current state
     */
    public void draw(Graphics g) {
        
    }

    /**
     * Controller for Keyboard pressed interaction
     *
     * @param e KeyEvent
     */
    public void keyPressed(KeyEvent e) {
        
    }

    /**
     * Controller for Keyboard released interaction
     *
     * @param e KeyEvent
     */
    public void keyReleased(KeyEvent e) {
        
    }

    /**
     * Controller for Mouse pressed interaction
     *
     * @param e KeyEvent
     */
    public void mousePressed(MouseEvent e) {
        
    }

    /**
     * Controller for Mosue released interaction
     *
     * @param e KeyEvent
     */
    public void mouseReleased(MouseEvent e) {
        
    }

    /**
     * Controller for Mouse moved interaction
     *
     * @param e KeyEvent
     */
    public void mouseMoved(MouseEvent e) {
        
    }

    /**
     * Controller for Mouse clicked interaction
     *
     * @param e KeyEvent
     */
    public void mouseClicked(MouseEvent e) {
        
    }

    public void mouseDragged(MouseEvent e) {
    }
}
