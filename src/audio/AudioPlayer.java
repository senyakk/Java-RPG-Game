package audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;

public class AudioPlayer {

    public static int ACTION_1 = 0;
    public static int ACTION_2 = 1;
    public static int ACTION_3 = 2;
    public static int ACTION_4 = 3;
    public static int ACTION_5 = 4;
    public static int AMBIENT_1 = 5;
    public static int AMBIENT_2 = 6;
    public static int AMBIENT_3 = 7;
    public static int AMBIENT_4 = 8;
    public static int AMBIENT_5 = 9;
    public static int AMBIENT_6 = 10;
    public static int AMBIENT_7 = 11;
    public static int AMBIENT_8 = 12;
    public static int AMBIENT_9 = 13;
    public static int AMBIENT_10 = 14;
    public static int DARK_AMBIENT_1 = 15;
    public static int DARK_AMBIENT_2 = 16;
    public static int DARK_AMBIENT_3 = 17;
    public static int DARK_AMBIENT_4 = 18;
    public static int DARK_AMBIENT_5 = 19;
    public static int FX_1 = 20;
    public static int FX_2 = 21;
    public static int FX_3 = 22;
    public static int LIGHT_AMBIENCE_1 = 23;
    public static int LIGHT_AMBIENCE_2 = 24;
    public static int LIGHT_AMBIENCE_3 = 25;
    public static int LIGHT_AMBIENCE_4 = 26;
    public static int LIGHT_AMBIENCE_5 = 27;
    public static int MERCHANT = 27;

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

    public AudioPlayer() {
        loadSongs();
        loadEffects();
        playLightAmbient();
    }

    private void loadSongs() {
        String[] songNames = {"Action 1", "Action 2", "Action 3", "Action 4", "Action 5", "Ambient 1", "Ambient 2",
                "Ambient 3", "Ambient 4", "Ambient 5", "Ambient 6", "Ambient 7", "Ambient 8", "Ambient 9", "Ambient 10"
                ,"Dark Ambient 1", "Dark Ambient 2", "Dark Ambient 3", "Dark Ambient 4", "Dark Ambient 5"
                , "Fx 1", "Fx 2", "Fx 3", "Light Ambience 1", "Light Ambience 2", "Light Ambience 3",
                "Light Ambience 4", "Light Ambience 5", "Merchant"};
        songs = new Clip[songNames.length];
        for (int i =0; i < songs.length; i ++) {
            songs[i] = getClip(songNames[i], "songs");
        }
    }

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

    public void playAmbient() {
        int start = AMBIENT_1;
        start += random.nextInt(10);
        playSong(start);
    }

    public void playLightAmbient() {
        int start = LIGHT_AMBIENCE_1;
        start += random.nextInt(5);
        playSong(start);
    }

    public void playDarkAmbient() {
        int start = DARK_AMBIENT_1;
        start += random.nextInt(5);
        playSong(start);
    }

    public void playAction() {
        int start = ACTION_1;
        start += random.nextInt(5);
        playSong(start);
    }

    public void playMerchant() {
        playSong(MERCHANT);
    }

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

    public void setVolume(float volume) {
        this.volume = volume;
        updateSongVolume();
        updateEffectsVolume();
    }

    public void stopSong() {
        if (songs[currentSongInd].isActive())
            songs[currentSongInd].stop();
    }

    public void setLevelSong(int levelIndex) {

    }

    public void playEffect (int effect) {
        effects[effect].setMicrosecondPosition(0);
        effects[effect].start();
    }

    public void playSong (int song) {
        stopSong();
        currentSongInd = song;
        updateSongVolume();
        songs[currentSongInd].setMicrosecondPosition(0);
        songs[currentSongInd].loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void turnSongMute() {
        this.muteSong = !muteSong;
        for (Clip c : songs) {
            BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(muteSong);
        }
    }

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

    private void updateSongVolume() {
        FloatControl gainControl = (FloatControl) songs[currentSongInd].getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volume) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }

    private void updateEffectsVolume() {
        for (Clip c : effects) {
            FloatControl gainControl = (FloatControl) songs[currentSongInd].getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
            gainControl.setValue(gain);
        }
    }
}