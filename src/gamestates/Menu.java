package gamestates;

import main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menu extends State implements Statemethods{
    public Menu(Game game) {
        super(game);
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void keyPressed(KeyEvent e, JPanel targetPanel) {

    }

    @Override
    public void keyReleased(KeyEvent e, JPanel targetPanel) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Gamestate.state = Gamestate.PLAYING;
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
