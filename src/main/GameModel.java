package main;

import buttonUi.AudioHandler;
import gamestates.*;
import utilities.AudioPlayer;

import static utilities.Constants.GameLanguage.DUTCH;

public class GameModel {
    private State gameState;
    private Menu menu;
    private Playing playing;
    private ClassSelection classSelection;
    private Options options;

    // AUDIO SETTINGS
    private AudioHandler audio;
    private AudioPlayer audioPlayer;

    private int language = DUTCH;

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

    public Menu getMenu() { return menu;}
    public Playing getPlaying() {
        return playing;
    }
    public ClassSelection getClassSelection() {
        return classSelection;
    }
    public Options getOptions() {
        return options;
    }

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