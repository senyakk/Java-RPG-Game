package locations;


import main.Game;
import npcs.Creature;
import objects.GameObject;

import static utilities.Constants.Direction.*;


public class CollisionChecker {

    private LevelManager levelManager;
    private Level lvl;
    public CollisionChecker(LevelManager levelManager) {
        this.levelManager = levelManager;
        lvl = levelManager.getCurrentLevel();
    }

    public void checkTile(Creature entity) {

        int entityLeftWorldX = (int) (entity.getWorldX() + entity.getHitArea().x);
        int entityRightWorldX = (int) (entity.getWorldX() + entity.getHitArea().x + entity.getHitArea().width);
        int entityTopWorldY = (int) (entity.getWorldY() + entity.getHitArea().y);
        int entityBottomWorldY = (int) (entity.getWorldY() + entity.getHitArea().y + entity.getHitArea().height);

        int entityLeftCol = entityLeftWorldX/ Game.tileSize;
        int entityRightCol = entityRightWorldX/ Game.tileSize;
        int entityTopRow = entityTopWorldY/ Game.tileSize;
        int entityBottomRow = entityBottomWorldY/ Game.tileSize;


        // Can't move behind world borders
        if (entityLeftCol < 0 || entityRightCol >= lvl.getWidth() * Game.tileSize)
            entity.setCollision();
        if (entityTopRow < 0 || entityBottomRow >= lvl.getHeigth() * Game.tileSize)
            entity.setCollision();

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case UP -> {
                entityTopRow = (entityTopWorldY - (int) (entity.getSpeed())) / Game.tileSize;
                tileNum1 = lvl.getTileIndex(entityLeftCol, entityTopRow);
                tileNum2 = lvl.getTileIndex(entityRightCol, entityTopRow);
                if (levelManager.getTile(tileNum1).collision && levelManager.getTile(tileNum2).collision) {
                    entity.setCollision();
                }
            }
            case DOWN -> {
                entityBottomRow = (entityBottomWorldY + (int) (entity.getSpeed())) / Game.tileSize;
                tileNum1 = lvl.getTileIndex(entityLeftCol, entityBottomRow);
                tileNum2 = lvl.getTileIndex(entityRightCol, entityBottomRow);
                if (levelManager.getTile(tileNum1).collision && levelManager.getTile(tileNum2).collision) {
                    entity.setCollision();
                }
            }
            case RIGHT -> {
                entityRightCol = (entityRightWorldX + (int) (entity.getSpeed())) / Game.tileSize;
                tileNum1 = lvl.getTileIndex(entityRightCol, entityTopRow);
                tileNum2 = lvl.getTileIndex(entityRightCol, entityBottomRow);
                if (levelManager.getTile(tileNum1).collision && levelManager.getTile(tileNum2).collision) {
                    entity.setCollision();
                }
            }
            case LEFT -> {
                entityLeftCol = (entityLeftWorldX - (int) (entity.getSpeed())) / Game.tileSize;
                tileNum1 = lvl.getTileIndex(entityLeftCol, entityTopRow);
                tileNum2 = lvl.getTileIndex(entityLeftCol, entityBottomRow);
                if (levelManager.getTile(tileNum1).collision && levelManager.getTile(tileNum2).collision) {
                    entity.setCollision();
                }
            }
        }
    }

    public GameObject checkObject(Creature entity, boolean isPlayer) {

        GameObject selectedObject = null;

        for (GameObject obj : lvl.getObjects()) {

            if (obj.checkActive()) {

                entity.getHitArea().x = (int) (entity.getWorldX() + entity.getHitArea().x);
                entity.getHitArea().y = (int) (entity.getWorldY() + entity.getHitArea().y);

                obj.getHitArea().x = (int) (obj.getWorldX() + obj.getHitArea().x);
                obj.getHitArea().y = (int) (obj.getWorldY() + obj.getHitArea().y);


                switch (entity.getDirection()) {
                    case UP -> {
                        entity.getHitArea().y -= entity.getSpeed();
                        if (entity.getHitArea().intersects(obj.getHitArea())) {
                            if (obj.getCollision()) {
                                entity.setCollision();
                            }
                            if (isPlayer) {
                                selectedObject = obj;
                            }
                        }
                    }
                    case DOWN -> {
                        entity.getHitArea().y += entity.getSpeed();
                        if (entity.getHitArea().intersects(obj.getHitArea())) {
                            if (obj.getCollision()) {
                                entity.setCollision();
                            }
                            if (isPlayer) {
                                selectedObject = obj;
                            }
                        }
                    }
                    case LEFT -> {
                        entity.getHitArea().x -= entity.getSpeed();
                        if (entity.getHitArea().intersects(obj.getHitArea())) {
                            if (obj.getCollision()) {
                                entity.setCollision();
                            }
                            if (isPlayer) {
                                selectedObject = obj;
                            }
                        }
                    }
                    case RIGHT -> {
                        entity.getHitArea().x += entity.getSpeed();
                        if (entity.getHitArea().intersects(obj.getHitArea())) {
                            if (obj.getCollision()) {
                                entity.setCollision();
                            }
                            if (isPlayer) {
                                selectedObject = obj;
                            }
                        }
                    }
                }

                entity.getHitArea().x = (int) entity.getHitAreaDefaultX();
                entity.getHitArea().y = (int) entity.getHitAreaDefaultY();
                obj.getHitArea().x = (int) obj.getHitAreaDefaultX();
                obj.getHitArea().y = (int) obj.getHitAreaDefaultY();
            }

        }
        return selectedObject;
    }
}