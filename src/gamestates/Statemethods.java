package gamestates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Statemethods {
    public void update();
    public void draw(Graphics g);
    public void keyPressed(KeyEvent e, JPanel targetPanel);
    public void keyReleased(KeyEvent e, JPanel targetPanel);
    public void mousePressed(MouseEvent e);
    public void mouseReleased(MouseEvent e);
    public void mouseMoved(MouseEvent e);
    public void mouseClicked(MouseEvent e);
}
