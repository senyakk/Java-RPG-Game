package locations;


import gamestates.Playing;
import main.GameModel;
import npcs.Creature;
import objects.GameObject;
import playerclasses.PlayerModel;

import static utilities.Constants.Direction.*;
import static utilities.Constants.GameLanguage.DUTCH;
import static utilities.Constants.GameLanguage.ENGLISH;


public class CollisionChecker {

    private LevelManager levelManager;
    private Level lvl;
    Playing playing;
    //private inventory GenericItem;

    /**
     * Creates a collision checker
     * @param playing playing state class
     */
    public CollisionChecker(Playing playing) {
        this.playing = playing;
        this.levelManager = playing.getLevelManager();
        lvl = levelManager.getCurrentLevel();
    }

    public void updateLevel() {
        lvl = levelManager.getCurrentLevel();
    }
    /**
     * Checks tile for the collision with it
     * @param entity Creature class that collides with a tile
     */
    public void checkTile(Creature entity) {


        int entityLeftWorldX = (int) (entity.getWorldX() + entity.getHitArea().x);
        int entityRightWorldX = (int) (entity.getWorldX() + entity.getHitArea().x + entity.getHitArea().width);
        int entityTopWorldY = (int) (entity.getWorldY() + entity.getHitArea().y);
        int entityBottomWorldY = (int) (entity.getWorldY() + entity.getHitArea().y + entity.getHitArea().height);

        int entityLeftCol = entityLeftWorldX/ GameModel.tileSize;
        int entityRightCol = entityRightWorldX/ GameModel.tileSize;
        int entityTopRow = entityTopWorldY/ GameModel.tileSize;
        int entityBottomRow = entityBottomWorldY/ GameModel.tileSize;


        // Can't move behind world borders
        if (entityLeftCol < 0 || entityRightCol >= lvl.getWidth() * GameModel.tileSize)
            entity.setCollision();
        if (entityTopRow < 0 || entityBottomRow >= lvl.getHeight() * GameModel.tileSize)
            entity.setCollision();

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case UP -> {
                entityTopRow = (entityTopWorldY - (int) (entity.getSpeed())) / GameModel.tileSize;
                tileNum1 = lvl.getTileIndex(entityLeftCol, entityTopRow);
                tileNum2 = lvl.getTileIndex(entityRightCol, entityTopRow);
                checkEvent(levelManager.getTile(tileNum1), levelManager.getTile(tileNum2));
                if (levelManager.getTile(tileNum1).collision && levelManager.getTile(tileNum2).collision) {
                    entity.setCollision();
                }
            }
            case DOWN -> {
                entityBottomRow = (entityBottomWorldY + (int) (entity.getSpeed())) / GameModel.tileSize;
                tileNum1 = lvl.getTileIndex(entityLeftCol, entityBottomRow);
                tileNum2 = lvl.getTileIndex(entityRightCol, entityBottomRow);
                checkEvent(levelManager.getTile(tileNum1), levelManager.getTile(tileNum2));
                if (levelManager.getTile(tileNum1).collision && levelManager.getTile(tileNum2).collision) {
                    entity.setCollision();
                }
            }
            case RIGHT -> {
                entityRightCol = (entityRightWorldX + (int) (entity.getSpeed())) / GameModel.tileSize;
                tileNum1 = lvl.getTileIndex(entityRightCol, entityTopRow);
                tileNum2 = lvl.getTileIndex(entityRightCol, entityBottomRow);
                checkEvent(levelManager.getTile(tileNum1), levelManager.getTile(tileNum2));
                if (levelManager.getTile(tileNum1).collision && levelManager.getTile(tileNum2).collision) {
                    entity.setCollision();
                }
            }
            case LEFT -> {
                entityLeftCol = (entityLeftWorldX - (int) (entity.getSpeed())) / GameModel.tileSize;
                tileNum1 = lvl.getTileIndex(entityLeftCol, entityTopRow);
                tileNum2 = lvl.getTileIndex(entityLeftCol, entityBottomRow);
                checkEvent(levelManager.getTile(tileNum1), levelManager.getTile(tileNum2));
                if (levelManager.getTile(tileNum1).collision && levelManager.getTile(tileNum2).collision) {
                    entity.setCollision();
                }
            }
        }
    }

    public void checkEvent(Tile tile1, Tile tile2) {
        if ((tile1.getName().equals("house")) || (tile2.getName().equals("house")))
            levelManager.changeLevel(1);
        else if (((tile1.getName().equals("transparentExitProfsHouse")) || (tile2.getName().equals("transparentExitProfsHouse"))))
            levelManager.changeLevel(0);
        else if (((tile1.getName().equals("transparentExitWitchHouse")) || (tile2.getName().equals("transparentExitWitchHouse"))))
            levelManager.changeLevel(8);
        else if (((tile1.getName().equals("witchHouseDoor")) || (tile2.getName().equals("witchHouseDoor"))))
            levelManager.changeLevel(2);
        else if (((tile1.getName().equals("gate")) || (tile2.getName().equals("gate"))))
            if (playing.getInventoryManager().isInInventory("5")) {
                levelManager.changeLevel(7);
            }
            else {
                switch (playing.getGameModel().getLanguage()) {
                    case ENGLISH ->
                            playing.getUi().showMessage("You cannot enter the gate without a sleutel!");
                    case DUTCH ->
                            playing.getUi().showMessage("Je kan niet de poort betreden zonder sleutel!");
                }
            }
        else if (((tile1.getName().equals("fontain")) || (tile2.getName().equals("fontain"))))
        if (playing.getInventoryManager().isInInventory("7")) {
            levelManager.changeLevel(8);
        }
        else {
            switch (playing.getGameModel().getLanguage()) {
                case ENGLISH ->
                        playing.getUi().showMessage("You cannot enter the portal without dragonbone, find the old dragon statue");
                case DUTCH ->
                        playing.getUi().showMessage("Je kan niet door de portaal zonder het drakenbot, zoek het oude drakenbeeld");
            }
        }
        else if (((tile1.getName().equals("treeDoor")) || (tile2.getName().equals("treeDoor"))))
            if (playing.getInventoryManager().isInInventory("8")) {
                levelManager.changeLevel(9);
            }
            else {
                switch (playing.getGameModel().getLanguage()) {
                    case ENGLISH ->
                            playing.getUi().showMessage("You cannot enter the portal without the special firefly, find the witch hut");
                    case DUTCH ->
                            playing.getUi().showMessage("Je kan niet door de portaal zonder het vuurvliegje, zoek het heksenhuisje");
                }
            }
        else if (((tile1.getName().equals("portal")) || (tile2.getName().equals("portal"))))
            if (playing.getInventoryManager().isInInventory("10")) {
                levelManager.changeLevel(0);
            }
            else {
                switch (playing.getGameModel().getLanguage()) {
                    case ENGLISH ->
                            playing.getUi().showMessage("You cannot enter the portal without the dragon cure, find the magic tree");
                    case DUTCH ->
                            playing.getUi().showMessage("Je kan niet door de portaal zonder het geneesmiddel, zoek de grote boom");
                }
            }
    }

    /**
     * Function for checking player's collision with an object
     * @param entity Player object
     * @return Object that player collided with
     */
    public GameObject checkObject(PlayerModel entity) {

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
                            selectedObject = obj;
                        }
                    }
                    case DOWN -> {
                        entity.getHitArea().y += entity.getSpeed();
                        if (entity.getHitArea().intersects(obj.getHitArea())) {
                            if (obj.getCollision()) {
                                entity.setCollision();
                            }
                            selectedObject = obj;
                        }
                    }
                    case LEFT -> {
                        entity.getHitArea().x -= entity.getSpeed();
                        if (entity.getHitArea().intersects(obj.getHitArea())) {
                            if (obj.getCollision()) {
                                entity.setCollision();
                            }
                            selectedObject = obj;
                        }
                    }
                    case RIGHT -> {
                        entity.getHitArea().x += entity.getSpeed();
                        if (entity.getHitArea().intersects(obj.getHitArea())) {
                            if (obj.getCollision()) {
                                entity.setCollision();
                            }
                            selectedObject = obj;
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