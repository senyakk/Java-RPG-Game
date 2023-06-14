package npcs;

public class BabyDragon extends NPC {

    public BabyDragon(float worldX, float worldY, float width, float height) {
        super(worldX, worldY, width, height);
        initHitArea(0, 5, 26, 26);
        loadNPCImages();
        setDialogue();
    }


}
