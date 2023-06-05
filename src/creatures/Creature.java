package creatures;

import static utilz.Constants.Direction.*;

public abstract class Creature {

    // Position variables
    protected int worldX, worldY;
    protected float speed;

    protected int walkDir = DOWN;

}
