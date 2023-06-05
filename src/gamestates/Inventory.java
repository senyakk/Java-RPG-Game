package gamestates;

import main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Inventory extends State implements Statemethods {
    public Inventory(Game game) {
        super(game);

        JLabel inventory = new JLabel("inventory");
        inventory.setFont(new Font("Sans", Font.PLAIN, 20));
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawString("Inventory", Game.screenHeight/2, Game.screenWidth/2);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_I) {
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
