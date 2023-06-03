package inputs;

import gamestates.Gamestate;
import main.Game;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private Object targetObject;
    private Game game;

    /**
     * Creates MouseListener and MouseMotionListener object connected to the target object
     * @param targetObject x
     */
    public MouseInputs (Object targetObject, Game game) {
        this.targetObject = targetObject;
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                game.getMenu().mouseClicked(e);
                break;
            case PLAYING:
                game.getPlaying().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                game.getMenu().mousePressed(e);
                break;
            case PLAYING:
                game.getPlaying().mousePressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                game.getMenu().mouseReleased(e);
                break;
            case PLAYING:
                game.getPlaying().mouseReleased(e);
                break;
            default:
                break;
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
        switch (Gamestate.state) {
            case PLAYING:
                game.getPlaying().mouseDragged(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                game.getMenu().mouseMoved(e);
                break;
            case PLAYING:
                game.getPlaying().mouseMoved(e);
                break;
            default:
                break;
        }
    }
}
