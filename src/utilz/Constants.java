package utilz;

import main.Game;

public class Constants {

    public static class UI {
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT * Game.scale);
            public static final int B_HEIGHT = (int)(B_HEIGHT_DEFAULT * Game.scale);
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
