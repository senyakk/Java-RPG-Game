package main;

import audio.AudioPlayer;
import gamestates.*;
import gamestates.Menu;
import ui.Audio;

import java.awt.*;

public class Game implements Runnable {

    // SCREEN SETTINGS
    public final static int defaultTileSize = 64;
    public final static float scale = 2.5f;
    public static final int tileSize = (int)(defaultTileSize * scale);
    public static final int maxTileCol = 10;
    public static final int maxTileRow = 6;
    public final static int screenWidth = tileSize * maxTileCol;
    public final static int screenHeight = tileSize * maxTileRow;

    // WINDOW SETTINGS
    private GameScreen gameWindow;
    private GamePanel gamePanel;
    private Menu menu;
    private Playing playing;
    private Audio audio;
    private AudioPlayer audioPlayer;
    private Options options;
    public Thread gameLoop;

    // GAME LOOP SETTINGS
    private final int FPS = 120;
    private final int UPS = 120;

    /**
     * Game constructor that creates GamePanel and GameScreen
     */
    public Game() {
        init();
        gamePanel = new GamePanel(this);
        gameWindow = new GameScreen(gamePanel);

        gamePanel.requestFocus();

        startGameLoop();

    }

    private void init() {
        audio = new Audio(this);
        audioPlayer = new AudioPlayer();
        menu = new Menu(this);
        playing = new Playing(this);
        options = new Options(this);
    }

    private void startGameLoop() {
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    public void update() {
        switch (Gamestate.state) {
            case MENU -> menu.update();
            case PLAYING -> playing.update();
            case OPTIONS -> options.update();
            case QUIT -> System.exit(0);
            default -> System.exit(0);
        }
    }

    public void render(Graphics g) {
        switch (Gamestate.state) {
            case MENU -> menu.draw(g);
            case PLAYING -> playing.draw(g);
            case OPTIONS -> options.draw(g);
            default -> {
            }
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
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
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING) {
            playing.getPlayer().resetDirections();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Audio getAudio() {
        return audio;
    }

    public Options getOptions() {
        return options;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
}