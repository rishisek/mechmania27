package mech.mania.competitor.mm_utils;

import mech.mania.competitor.Game;
import mech.mania.competitor.api.GameUtil;
import mech.mania.competitor.model.*;
import mech.mania.competitor.model.decisions.MoveDecision;

import java.util.ArrayList;

public class Utilities {
    private Game game_;
    private GameState gameState_;
    private Player myPlayer_;
    private Player opponent_;

    public Utilities(Game game) {
        game_ = game;
        gameState_ =  game.getGameState();
        myPlayer_ = gameState_.getMyPlayer();
        opponent_ = gameState_.getOpponentPlayer();
    }

    public static ItemType getItem(String item) {
        return ItemType.valueOf(item);
    }

    public static UpgradeType getUpgrade(String upgrade) {
        return UpgradeType.valueOf(upgrade);
    }

    public boolean isPositionValid(Position position) {
        return GameUtil.distance(myPlayer_.getPosition(), position)
                <= myPlayer_.getMaxMovement();
    }

    public boolean isHarvestValid(Position coord) {
        if (GameUtil.distance(myPlayer_.getPosition(), coord)
                > myPlayer_.getHarvestRadius()) {
            return false;
        }

        if (GameUtil.distance(opponent_.getPosition(), coord)
                <= opponent_.getProtectionRadius()) {
            return false;
        }

        Tile tile = gameState_.getTileMap().get(coord);
        Crop crop = tile.getCrop();

        if (crop.getType().equals(CropType.NONE) || crop.getGrowthTimer() > 0) {
            return false;
        }

        if (tile.hasScarecrowEffect()) {
            return false;
        }

        return true;
    }

    public boolean isHarvestValid(ArrayList<Position> coords) {
        for (Position coord : coords) {
            if (!isHarvestValid(coord)) return false;
        }

        return true;
    }

    public Position getRelativePosition(Position position) {
        return getRelativePosition(myPlayer_.getPosition(), position);
    }

    public Position getRelativePosition(Position from, Position to) {
        return new Position(to.getX() - from.getX(),
                to.getY() - from.getY());
    }

    public int getDistance(Position delta) {
        return delta.getX() + delta.getY();
    }
}
