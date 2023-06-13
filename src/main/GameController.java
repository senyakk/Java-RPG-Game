package main;

import gamestates.*;
import java.awt.event.*;

import static main.Game.FPS;
import static main.Game.UPS;

public class GameController implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private GamePanel gamePanel;
    private GameModel gameModel;
    private State gameState;
    private boolean gameActive;

    public GameController (GamePanel gamePanel, GameModel gameModel) {
        this.gamePanel = gamePanel;
        this.gameActive = true;
        this.gameModel = gameModel;

    }

    /**
     * Update the game state
     */
    public void update() {
        if (getGameModel().getGameState() != null) {
            gameState = getGameModel().getGameState();
            gameState.update();
        }
    }

    /**
     * Set the game's active status
     */
    public void setGameActive(boolean gameActive) {
        this.gameActive = gameActive;
    }

    /**
     * Check if the game is active
     */
    public boolean isGameActive() {
        return gameActive;
    }

    /**
     * Get the game model
     */
    public GameModel getGameModel() {
        return gameModel;
    }


    /**
     * Run the game thread, updating the game UPS times per second and drawing the game FPS times per second
     */
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;
        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        while (gameActive) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                deltaU--;
            }
            if (deltaF >= 1) {
                gamePanel.repaint();
                deltaF--;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
            }
        }
    }

    // Key Listeners methods
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameState != null) {
            gameState.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (gameState != null) {
            gameState.keyReleased(e);
        }
    }

    // MouseListener methods
    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameState != null) {
            gameState.mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (gameState != null) {
            gameState.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (gameState != null) {
            gameState.mouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (gameState != null) {
            gameState.mouseDragged(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (gameState != null) {
            gameState.mouseMoved(e);
        }
    }

}
