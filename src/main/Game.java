package main;

import utilities.AudioPlayer;
import gamestates.*;
import gamestates.Menu;
import buttonUi.AudioHandler;

import java.awt.*;

import static utilities.Constants.GameLanguage.*;

public class Game implements Runnable {

    // SCREEN SETTINGS
    public final static int defaultTileSize = 64;
    public final static float scale = 1.5f;
    public static final int tileSize = (int)(defaultTileSize * scale);
    // MAXIMUM NUMBER OF TILES IN A COLUMN
    public static final int maxTileCol = 10;
    // MAXIMUM NUMBER OF ROWS IN A COLUMN
    public static final int maxTileRow = 6;
    public final static int screenWidth = tileSize * maxTileCol;
    public final static int screenHeight = tileSize * maxTileRow;

    // WINDOW SETTINGS
    private GameScreen gameWindow;
    private GamePanel gamePanel;

    // GAME STATES SETTINGS
    private Menu menu;
    private Playing playing;
    private ClassSelection classSelection;
    private AudioHandler audio;
    private AudioPlayer audioPlayer;
    private Options options;
    public Thread gameLoop;

    public int language = ENGLISH;

    // GAME LOOP SETTINGS
    private final int FPS = 120;
    private final int UPS = 120;

    /**
     * Game constructor that initialises all game states, then
     * creates GamePanel and GameWindow and starts the game loop
     */
    public Game() {
        init();
        gamePanel = new GamePanel(this);
        gameWindow = new GameScreen(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void init() {
        audio = new AudioHandler(this);
        audioPlayer = new AudioPlayer();
        audioPlayer.playLightAmbient();

        menu = new Menu(this);
        classSelection = new ClassSelection(this);
        playing = new Playing(this);
        options = new Options(this);
    }

    private void startGameLoop() {
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    /**
     * Update the game status for each given state
     */
    public void update() {
        switch (Gamestate.state) {
            case MENU -> menu.update();
            case CLASS_SELECTION -> classSelection.update();
            case PLAYING -> playing.update();
            case OPTIONS -> options.update();
            case QUIT -> System.exit(0);
            default -> System.exit(0);
        }
    }

    /**
     * Draw game for each given state
     * @param g Graphics object
     */
    public void render(Graphics g) {
        switch (Gamestate.state) {
            case MENU -> menu.draw(g);
            case CLASS_SELECTION -> classSelection.draw(g);
            case PLAYING -> playing.draw(g);
            case OPTIONS -> options.draw(g);
            default -> {
            }
        }
    }

    /**
     * Run a game thread, updating the game UPS time per second and drawing the game FPS time per second
     */
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;
        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        while (gameLoop != null) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                deltaU--;
            }
            if (deltaF >= 1) {
                gamePanel.repaint();
                deltaF--;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
            }
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public ClassSelection getClassSelection() {return classSelection;}

    public Playing getPlaying() {
        return playing;
    }

    public AudioHandler getAudio() {
        return audio;
    }

    public Options getOptions() {
        return options;
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