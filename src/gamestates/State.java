package gamestates;

import main.Game;

public class State {

    protected Game game;
    public State (Game game) {
        this.game = game;
    }
    public Game getGame() {
        return game;
    }

    /**
     * Sets game state and changes music
     * @param state Gamestate
     */
    public void setGameState(Gamestate state) {
        switch (state) {
            case MENU -> game.getAudioPlayer().playLightAmbient();
            case PLAYING -> {
                game.getAudioPlayer().playAmbient();
            }
        }
        Gamestate.state = state;
    }

}
