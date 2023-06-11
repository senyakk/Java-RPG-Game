package utilities;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;

/**
 * Audio Player for playing different sounds
 */
public class AudioPlayer {

    public static int ACTION_1 = 0;
    public static int ACTION_2 = 1;
    public static int AMBIENT_1 = 2;
    public static int AMBIENT_2 = 3;
    public static int AMBIENT_3 = 4;
    public static int DARK_AMBIENT_1 = 5;
    public static int DARK_AMBIENT_2 = 6;
    public static int FX_1 = 7;
    public static int FX_2 = 8;
    public static int FX_3 = 9;
    public static int LIGHT_AMBIENCE_1 = 10;
    public static int LIGHT_AMBIENCE_2 = 11;

    public static int BLOCKED = 0;
    public static int BURNING = 1;
    public static int CHIPWALL = 2;
    public static int COIN = 3;
    public static int CURSOR = 4;
    public static int CUTTREE = 5;
    public static int DOOROPEN = 6;
    public static int FANFARE = 7;
    public static int GAMEOVER = 8;
    public static int HITMONSTER = 9;
    public static int LEVELUP = 10;
    public static int PARRY = 11;
    public static int POWERUP = 12;
    public static int RECEIVE_DAMAGE = 13;
    public static int SLEEP = 14;
    public static int SPEAK = 15;
    public static int STAIRS = 16;
    public static int UNLOCK = 17;

    private Clip[] songs, effects;
    private int currentSongInd;
    private float volume = 0.85f;
    private boolean muteSong, muteEffect;
    private Random random = new Random();

    /**
     * Constructor for AudioPlayer that loads songs and sound effects
     */
    public AudioPlayer() {
        loadSongs();
        loadEffects();
    }

    /**
     * Loads songs from resource folder
     */
    private void loadSongs() {
        String[] songNames = {"Action 1", "Action 2", "Ambient 1", "Ambient 2",
                "Ambient 3","Dark Ambient 1", "Dark Ambient 2", "Fx 1", "Fx 2", "Fx 3", "Light Ambience 1",
                "Light Ambience 2"};
        songs = new Clip[songNames.length];
        for (int i =0; i < songs.length; i ++) {
            songs[i] = getClip(songNames[i], "songs");
        }
    }

    /**
     * Loads sound effects from resource folder
     */
    private void loadEffects() {
        String[] effectNames = {"blocked", "burning", "chipwall", "coin", "cursor", "cuttree", "dooropen", "fanfare",
                "gameover", "hitmonster", "levelup", "parry", "powerup", "receivedamage", "sleep", "speak",
                "stairs", "unlock"};
        effects = new Clip[effectNames.length];
        for (int i =0; i < effectNames.length; i ++) {
            effects[i] = getClip(effectNames[i], "sfx");
        }

        updateEffectsVolume();
    }

    /**
     * Plays ambient music
     */
    public void playAmbient() {
        int start = AMBIENT_1;
        start += random.nextInt(3);
        playSong(start);
    }

    /**
     * Plays light ambient music
     */
    public void playLightAmbient() {
        int start = LIGHT_AMBIENCE_1;
        start += random.nextInt(2);
        playSong(start);
    }

    /**
     * Plays dark ambient music
     */
    public void playDarkAmbient() {
        int start = DARK_AMBIENT_1;
        start += random.nextInt(2);
        playSong(start);
    }

    /**
     * Plays action music
     */
    public void playAction() {
        int start = ACTION_1;
        start += random.nextInt(2);
        playSong(start);
    }

    /**
     * Loads sounds
     * @param name name of the sound
     * @param type music or sfx
     * @return sound clip
     */
    private Clip getClip (String name, String type) {
        URL url = null;
        if (Objects.equals(type, "songs")) {
            url = getClass().getResource("/audio/music/" + name + ".wav");
        } else if (Objects.equals(type, "sfx")) {
            url = getClass().getResource("/audio/sfx/" + name + ".wav");
        }
        AudioInputStream audio;

        try {
            audio = AudioSystem.getAudioInputStream(url);
            Clip c = AudioSystem.getClip();
            c.open(audio);
            return c;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Sets volume of the audio
     * @param volume float volumne
     */
    public void setVolume(float volume) {
        this.volume = volume;
        updateSongVolume();
        updateEffectsVolume();
    }

    /**
     * Stops the song
     */
    public void stopSong() {
        if (songs[currentSongInd].isActive())
            songs[currentSongInd].stop();
    }

    public void setLevelSong(int levelIndex) {

    }

    /**
     * Plays the effect
     * @param effect index of the effect
     */
    public void playEffect (int effect) {
        effects[effect].setMicrosecondPosition(0);
        effects[effect].start();
    }

    /**
     * Plays song
     * @param song index of the song
     */
    public void playSong (int song) {
        stopSong();
        currentSongInd = song;
        updateSongVolume();
        songs[currentSongInd].setMicrosecondPosition(0);
        songs[currentSongInd].loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Mutes the music
     */
    public void turnSongMute() {
        this.muteSong = !muteSong;
        for (Clip c : songs) {
            BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(muteSong);
        }
    }

    /**
     * Mutes the sound effects
     */
    public void turnEffectMute() {
        this.muteEffect = !muteEffect;
        for (Clip c : effects) {
            BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(muteEffect);
        }
        if (!muteEffect) {
            playEffect(CURSOR);
        }
    }

    /**
     * Updates the volume of music
     */
    private void updateSongVolume() {
        FloatControl gainControl = (FloatControl) songs[currentSongInd].getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volume) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }

    /**
     * Updates the volume of sound effects
     */
    private void updateEffectsVolume() {
        for (Clip c : effects) {
            FloatControl gainControl = (FloatControl) songs[currentSongInd].getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
            gainControl.setValue(gain);
        }
    }
}