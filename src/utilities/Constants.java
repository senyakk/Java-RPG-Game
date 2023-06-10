package utilities;

import main.Game;

public class Constants {


    public static class Objects {

        public static final int OBJECT_SIZE_DEFAULT = 16;
        public static final int OBJECT_SIZE = (int) (16 * Game.scale);
    }
    public static class UI {
        public static class MenuButtons {
            public static final int B_WIDTH_DEFAULT = (int) (100 * 1.5);
            public static final int B_HEIGHT_DEFAULT = (int) (50 * 1.5);
            public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT * Game.scale);
            public static final int B_HEIGHT = (int)(B_HEIGHT_DEFAULT * Game.scale);
        }

        public static class PauseButtons {
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int)(SOUND_SIZE_DEFAULT * Game.scale/1.5);
            public static final int URM_SIZE_DEFAULT = 56;
            public static final int URM_SIZE = (int)(URM_SIZE_DEFAULT * Game.scale/1.5);
        }
        public static class VolumeButton {
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;
            public static final int SLIDER_WIDTH = (int)(SLIDER_DEFAULT_WIDTH * Game.scale/1.5);
            public static final int VOLUME_WIDTH = (int)(VOLUME_DEFAULT_WIDTH * Game.scale/1.5);
            public static final int VOLUME_HEIGHT = (int)(VOLUME_DEFAULT_HEIGHT * Game.scale/1.5);
        }
    }

    public static class Direction {
        public static final int DOWN = 0;
        public static final int LEFT = 1;
        public static final int RIGHT = 2;
        public static final int UP = 3;
    }

    public static class PlayerConstants {

        // Write all the animations here
        public static final int WALKING_DOWN = 0;
        public static final int WALKING_LEFT = 1;
        public static final int WALKING_RIGHT = 2;
        public static final int WALKING_UP = 3;

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
