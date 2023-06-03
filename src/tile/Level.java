package tile;

public class Level {

    private int[][] lvlData;
    private int width, heigth;

    public Level(int[][] lvlData) {
        this.lvlData = lvlData;
        this.width = lvlData.length;
        this.heigth = lvlData[0].length;
    }

    public int getTileIndex(int x, int y) {
        return lvlData[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }
}