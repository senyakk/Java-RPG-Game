package gamestates;

import main.Game;
import ui.MenuButton;

import java.awt.event.MouseEvent;

public class State {

    protected Game game;

    public State (Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public boolean inBounds(MouseEvent e, MenuButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    public void setGameState(Gamestate state) {
        switch (state) {
            case MENU -> game.getAudioPlayer().playLightAmbient();
            case PLAYING -> game.getAudioPlayer().playAmbient();
        }
        Gamestate.state = state;
    }
}
