package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    // SCREEN SETTINGS
    final int originalTileSize = 64;
    final int scale = 2;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 8;
    final int maxScreenRow = 6;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    // INVENTORY STATUS
    private boolean isVisible;

    // GAME VARIABLE
    private Game game;

    public GamePanel(Game game) {

        this.game = game;
        setPanelSize();

        setBackground(new Color(100, 200, 90));
        setDoubleBuffered(true);

        this.isVisible = true;
        this.setVisible(true);
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

    public void updateGame() {
    }

    public Game getGame() {
        return game;
    }

    public void switchVisibility(){
        this.isVisible = !isVisible;
        this.setVisible(isVisible);
        System.out.println("Switched game panel to ".concat(Boolean.toString(isVisible)));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
    }
}
