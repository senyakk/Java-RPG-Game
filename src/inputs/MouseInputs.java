package inputs;

import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private Object targetObject;

    /**
     * Creates MouseListener and MouseMotionListener object connected to the target object
     * @param targetObject x
     */
    public MouseInputs (Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
       // if (targetObject instanceof GamePanel) ((GamePanel)targetObject).setRectPos(e.getX(), e.getY());
    }
}
