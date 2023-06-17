package utilities;

import inventory.modelfiles.ItemData;
import main.GameModel;

import java.util.HashMap;

public class Constants {

    public static final int anim_speed = 25;

    public static class GameLanguage {
        public static final int ENGLISH = 0;
        public static final int DUTCH = 1;
    }

    public static class Objects {
        // SIZE CONSTANTS FOR OBJECTS
        public static final int OBJECT_SIZE_DEFAULT = 16;
        public static final int OBJECT_SIZE = (int) (OBJECT_SIZE_DEFAULT * GameModel.scale);
    }

    public static class NPCs {
        public static final int NPCSizeDef = 16;
        public static final int NPCSize = (int) (NPCSizeDef * GameModel.scale);
    }
    public static class UI {
        // SIZE CONSTANT FOR MENU BUTTONS
        public static class MenuButtons {
            public static final int B_WIDTH_DEFAULT = (int) (100 * 1.5);
            public static final int B_HEIGHT_DEFAULT = (int) (50 * 1.5);
            public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT * GameModel.scale);
            public static final int B_HEIGHT = (int)(B_HEIGHT_DEFAULT * GameModel.scale);
        }
        // SIZE CONSTANTS FOR PAUSE BUTTONS
        public static class PauseButtons {
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int)(SOUND_SIZE_DEFAULT * GameModel.scale/1.5);
            public static final int URM_SIZE_DEFAULT = 56;
            public static final int URM_SIZE = (int)(URM_SIZE_DEFAULT * GameModel.scale/1.5);
        }
        // SIZE CONSTANTS FOR VOLUME BUTTONS
        public static class VolumeButton {
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;
            public static final int SLIDER_WIDTH = (int)(SLIDER_DEFAULT_WIDTH * GameModel.scale/1.5);
            public static final int VOLUME_WIDTH = (int)(VOLUME_DEFAULT_WIDTH * GameModel.scale/1.5);
            public static final int VOLUME_HEIGHT = (int)(VOLUME_DEFAULT_HEIGHT * GameModel.scale/1.5);
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

    /**
     * Hashmap of all items that are implemented, along with their general data
     * @author Cata Mihit
     */
    public static class ItemList {
        public static final HashMap<String, ItemData> ITEM_LIST = new HashMap<String, ItemData>(){
            {
                put("0", new ItemData("0","","","0"));
                put("1", new ItemData("1","Sword","Zwaard","online_resources/sword_01b"));
                put("2", new ItemData("2","Bow","Boog","bowItem"));
                put("3", new ItemData("3","Arrow","Pijl","arrowItem"));
                put("4", new ItemData("4","Flower","Bloem","online_resources/flower_02a"));
                put("5", new ItemData("5","Key","Sleutel","keyItem"));
                put("6", new ItemData("6","Boots","Laarzen","online_resources/boots_01e"));
                put("7", new ItemData("7","Dragon Bone","Drakenbot","DragonBoneItem"));
                put("8", new ItemData("8","Firefly","Vuurvliegjes","Fireflyitem"));
                put("9", new ItemData("9","Juice Extractor","Tap Boomsap","JuiceExtractorItem"));
                put("10", new ItemData("10","Potion Cure Dragon","Genezingsdrankje Draak","PotionStoneDragon"));
            }
        };
    }
}
