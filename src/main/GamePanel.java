package main;

import gamestates.State;

import javax.swing.*;
import java.awt.*;
import static main.Game.screenHeight;
import static main.Game.screenWidth;

public class GamePanel extends JPanel {

    private GameController gameController;

    /**
     * Creates a game pane
     */
    public GamePanel() {

        setPanelSize();
        setFocusable(true);

        setBackground(new Color(100, 180, 100));
        setDoubleBuffered(true);
        requestFocus();
    }

    public void setGameController(GameController gameController) {
        this.gameController =gameController;
        addKeyListener(gameController);
        addMouseListener(gameController);
        addMouseMotionListener(gameController);
    }

    /**
     * Sets GamePanel size
     */
    private void setPanelSize() {
        Dimension size = new Dimension(screenWidth, screenHeight);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public GameController getGameController() {
        return gameController;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        State gameState = gameController.getGameModel().getGameState();
        if (gameState != null) {
            gameState.draw(g);
        }
    }
}
