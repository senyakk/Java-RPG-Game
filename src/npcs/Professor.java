package npcs;

import main.GameModel;
import utilities.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.NPCs.NPCSize;
import static utilities.Constants.NPCs.NPCSizeDef;
import static utilities.Load.GetSpriteImg;

public class Professor extends NPC {

    /**
     * Constructs an NPC based on the superclass Creature
     *
     * @param worldX x position in the world
     * @param worldY y position in the world
     */
    public Professor(float worldX, float worldY) {
        super(worldX, worldY, 53 * GameModel.scale, 65 * GameModel.scale,
                "prof", "Professor");
        setDialogue();
        images = new BufferedImage[1][1];
        images[0][0] =Load.GetSpriteImg("characters/prof.png");
        state = 0;
        animIndex = 0;

        initHitArea(22, 24, 18, 32);
    }

    public void setDialogue() {
        dialogues[0] = "Hello Milo, why do you look so worried?";
        dialogues[1] = "I am in as much despair as you to hear about Aurelio";
        dialogues[2] = "Luckily, I know the perfect cure. But this will require an adventure to gather 3 ingredients";
        dialogues[3] = "You will need a dragon bone, which you can find in the dragon cemetery";
        dialogues[4] = "Next you will need to visit the forbidden swamp to retrieve the special firefly";
        dialogues[5] = "Finally, you'll find the sap of the oldest tree in the cursed hallows";
        dialogues[6] = "Be careful son, take this key. It will aid you in this journey";
    }

}
