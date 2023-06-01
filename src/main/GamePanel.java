package main;

import javax.swing.*;
import java.awt.*;
import static main.Game.screenHeight;
import static main.Game.screenWidth;

public class GamePanel extends JPanel {


    // INVENTORY STATUS
    private boolean isVisible;

    // GAME VARIABLE
    private Game game;
    public UI ui = new UI(this);

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
        Graphics2D g2 = (Graphics2D)g;

        game.render(g2);
        ui.draw(g2);
    }
}
