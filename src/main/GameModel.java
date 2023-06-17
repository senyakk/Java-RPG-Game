package main;

import buttonUi.AudioHandler;
import gamestates.*;
import playerclasses.PlayerModel;
import utilities.AudioPlayer;

import static utilities.Constants.GameLanguage.*;

public class GameModel {

    // SCREEN SETTINGS
    public final static int defaultTileSize = 64;
    public final static float scale = 1.5f;
    public static final int tileSize = (int)(defaultTileSize * scale);
    // MAXIMUM NUMBER OF TILES IN A COLUMN
    public static final int maxTileCol = 10;
    public final static int screenWidth = tileSize * maxTileCol;
    // MAXIMUM NUMBER OF ROWS IN A COLUMN
    public static final int maxTileRow = 6;
    public final static int screenHeight = tileSize * maxTileRow;

    // GAME LOOP SETTINGS
    public static int FPS = 120;
    public static int UPS = 120;

    // MODEL STATES
    private State gameState; // Current game state
    private final Menu menu; // Instance of the menu state
    private final Playing playing; // Instance of the playing state
    private final ClassSelection classSelection; // Instance of the class selection state
    private final Options options; // Instance of the options state

    // AUDIO SETTINGS
    private final AudioHandler audio; // Audio handler for managing game audio
    private final AudioPlayer audioPlayer; // Audio player for playing audio clips

    private int language = ENGLISH; // Default language setting

    // SAVING PATH
    public static String SAVE_DIR = "..\\..\\resources\\saves\\";

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
            case MENU -> {
                this.gameState = menu;
                audioPlayer.playLightAmbient();
            }
            case PLAYING -> {
                this.gameState = playing;
                audioPlayer.playAmbient();
            }
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

    public PlayerModel getPlayer() { return playing.getPlayer(); }

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