package gamestates;

/**
 * Parent gamestate class for storing state names;
 */
public enum Gamestate {

    MENU, PLAYING, CLASS_SELECTION, OPTIONS, QUIT;

    public static Gamestate state = MENU;
}
