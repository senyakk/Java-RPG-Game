package utilities;


import main.Game;

public class HelpMethods {

    public static boolean checkWorldCamera(int worldX, int worldY, int playerX, int playerY,
                                           int playerScreenX, int playerScreenY) {
        return (worldX + Game.tileSize > playerX - playerScreenX &&
                worldX - Game.tileSize < playerX + playerScreenX &&
                worldY + Game.tileSize > playerY - playerScreenY &&
                worldY - Game.tileSize < playerY+ playerScreenY);
    }
}