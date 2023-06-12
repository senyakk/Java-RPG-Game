package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import static main.Game.screenHeight;
import static main.Game.screenWidth;

public class GamePanel extends JPanel {

    private Game game;

    /**
     * Creates a game panel and adds even listeners to the panel;
     * @param game Game object
     */
    public GamePanel(Game game) {

        this.game = game;
        setPanelSize();

        addKeyListener(new KeyboardInputs(this));
        MouseInputs mouseInputs = new MouseInputs(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        requestFocus();

        setBackground(new Color(100, 180, 100));
        setDoubleBuffered(true);
        requestFocus();
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

    public Game getGame() {
        return game;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        game.render(g2);
    }
}
