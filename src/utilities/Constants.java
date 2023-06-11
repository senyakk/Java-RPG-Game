package utilities;

import main.Game;

public class Constants {

    public static class Objects {
        // SIZE CONSTANTS FOR OBJECTS
        public static final int OBJECT_SIZE_DEFAULT = 16;
        public static final int OBJECT_SIZE = (int) (OBJECT_SIZE_DEFAULT * Game.scale);
    }
    public static class UI {
        // SIZE CONSTANT FOR MENU BUTTONS
        public static class MenuButtons {
            public static final int B_WIDTH_DEFAULT = (int) (100 * 1.5);
            public static final int B_HEIGHT_DEFAULT = (int) (50 * 1.5);
            public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT * Game.scale);
            public static final int B_HEIGHT = (int)(B_HEIGHT_DEFAULT * Game.scale);
        }
        // SIZE CONSTANTS FOR PAUSE BUTTONS
        public static class PauseButtons {
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int)(SOUND_SIZE_DEFAULT * Game.scale/1.5);
            public static final int URM_SIZE_DEFAULT = 56;
            public static final int URM_SIZE = (int)(URM_SIZE_DEFAULT * Game.scale/1.5);
        }
        // SIZE CONSTANTS FOR VOLUME BUTTONS
        public static class VolumeButton {
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;
            public static final int SLIDER_WIDTH = (int)(SLIDER_DEFAULT_WIDTH * Game.scale/1.5);
            public static final int VOLUME_WIDTH = (int)(VOLUME_DEFAULT_WIDTH * Game.scale/1.5);
            public static final int VOLUME_HEIGHT = (int)(VOLUME_DEFAULT_HEIGHT * Game.scale/1.5);
        }
    }

    // Direction a creature is walking
    public static class Direction {
        public static final int DOWN = 0;
        public static final int LEFT = 1;
        public static final int RIGHT = 2;
        public static final int UP = 3;
    }

    public static class PlayerConstants {

        // PLAYER CLASSES
        public static final int WARRIOR = 0;
        public static final int ARCHER = 1;
        public static final int BARD = 2;

        // ANIMATIONS OF THE PLAYER
        public static final int WALKING_DOWN = 0;
        public static final int WALKING_LEFT = 1;
        public static final int WALKING_RIGHT = 2;
        public static final int WALKING_UP = 3;

        /**
         * Get number of sprites for a given action of a player
         * @param action action
         * @return number of sprites
         */
        public static int getSpriteAmount(int action) {

            switch (action){
                case WALKING_DOWN, WALKING_RIGHT, WALKING_LEFT, WALKING_UP:
                    return 4;
                default:
                    return 1;
            }
        }
    }
}
