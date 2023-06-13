package main;

import buttonUi.AudioHandler;
import gamestates.*;
import playerclasses.Player;
import utilities.AudioPlayer;

import static utilities.Constants.GameLanguage.*;

public class GameModel {
    private State gameState; // Current game state
    private Menu menu; // Instance of the menu state
    private Playing playing; // Instance of the playing state
    private ClassSelection classSelection; // Instance of the class selection state
    private Options options; // Instance of the options state

    // AUDIO SETTINGS
    private AudioHandler audio; // Audio handler for managing game audio
    private AudioPlayer audioPlayer; // Audio player for playing audio clips

    private int language = DUTCH; // Default language setting

    public GameModel() {
        // Creating audio
        audio = new AudioHandler(this);
        audioPlayer = new AudioPlayer();
        // Creating instances of game states
        menu = new Menu(this);
        classSelection = new ClassSelection(this);
        playing = new Playing(this);
        options = new Options(this);

        setGameState(Gamestate.MENU);
    }

    public void setGameState(Gamestate gameState) {
        switch (gameState) {
            case MENU -> this.gameState = menu;
            case PLAYING -> this.gameState = playing;
            case CLASS_SELECTION -> this.gameState = classSelection;
            case OPTIONS -> this.gameState = options;
            case QUIT -> System.exit(0);
            default -> {
            }
        }
    }

    public State getGameState() {
        return gameState;
    }

    public Player getPlayer() { return playing.getPlayer(); }

    public AudioHandler getAudio() {
        return audio;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

}