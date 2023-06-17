package utilities;


import main.GameModel;


public class HelpMethods {

    /**
     * @param worldX x location of the object
     * @param worldY y location of the object
     * @param playerX x location of the player
     * @param playerY y location of the player
     * @param playerScreenX x location of the player on the screen
     * @param playerScreenY y location of the player on the screen
     * @return true if non-player objects are located in the game window, false otherwise
     */
    public static boolean checkWorldCamera(int worldX, int worldY, int playerX, int playerY,
                                           int playerScreenX, int playerScreenY) {
        return (worldX + GameModel.tileSize > playerX - playerScreenX &&
                worldX - GameModel.tileSize < playerX + playerScreenX &&
                worldY + GameModel.tileSize > playerY - playerScreenY &&
                worldY - GameModel.tileSize < playerY+ playerScreenY);
    }
}