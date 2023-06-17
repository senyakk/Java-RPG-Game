package npcs;

import main.GameModel;

public class DragonStatue extends NPC {

    public DragonStatue(float x, float y) {
        super(x, y, GameModel.tileSize, GameModel.tileSize, 1);
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "Ah yes the human";
        dialogues[1] = "Bring food";
        dialogues[2] = "Or I eat you";
        dialogues[3] = "And this line just to test the \nbreak lien hahahahahahaha";
    }

}
