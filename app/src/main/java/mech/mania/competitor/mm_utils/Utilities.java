package mech.mania.competitor.mm_utils;

import mech.mania.competitor.Game;
import mech.mania.competitor.api.GameUtil;
import mech.mania.competitor.mm_models.DecisionPair;
import mech.mania.competitor.model.*;
import mech.mania.competitor.model.decisions.ActionDecision;
import mech.mania.competitor.model.decisions.DoNothingDecision;
import mech.mania.competitor.model.decisions.MoveDecision;

import java.util.ArrayList;
import java.util.Comparator;

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

    public Position getNearestGrocer() {
        return gameState_.getTileMap().getGreenGrocerTiles().stream()
                .min(Comparator.comparingInt(this::getRelativeDistance)).get();
    }

    public boolean isPositionValid(Position position) {
        return GameUtil.validPosition(position) && GameUtil.distance(myPlayer_.getPosition(), position)
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

    public int getRelativeDistance(Position position) {
        return getDistance(getRelativePosition(position));
    }

    public int getRelativeDistance(Position from, Position to) {
        return getDistance(getRelativePosition(from, to));
    }

    public int getDistance(Position delta) {
        return Math.abs(delta.getX()) + Math.abs(delta.getY());
    }

    public int turnToReach(Player player, Position delta) {
        return (int) Math.ceil((double) getDistance(delta) / player.getMaxMovement());
    }

    public int turnToReach(Position delta) {
        return turnToReach(myPlayer_, delta);
    }

    public ArrayList<DecisionPair> moveToPosition(Position currentPosition, Position futurePosition,
                                                  ActionDecision actionDecision) {
        ArrayList<DecisionPair> pair = new ArrayList<>();

        Position movedPosition = currentPosition;
        while (movedPosition != futurePosition) {
            Position delta = getRelativePosition(movedPosition, futurePosition);

            int maxMoves = myPlayer_.getMaxMovement();

            if (getDistance(delta) <= maxMoves) {
                pair.add(new DecisionPair(new MoveDecision(futurePosition), actionDecision));
                break;
            }

            int deltaX = delta.getX();
            int deltaY = delta.getY();

            int newDeltaX = (deltaX < 0 ? -1 : 1) * Math.min(Math.abs(deltaX), maxMoves);
            int newDeltaY = (deltaY < 0 ? -1 : 1) * Math.min(Math.abs(deltaY), maxMoves - Math.abs(newDeltaX));

            movedPosition = new Position(movedPosition.getX() + newDeltaX,
                    movedPosition.getY() + newDeltaY);

            pair.add(new DecisionPair(new MoveDecision(movedPosition), new DoNothingDecision()));
        }

        return pair;
    }

    public ArrayList<DecisionPair> moveToPosition(Position futurePosition, ActionDecision actionDecision) {
        return moveToPosition(myPlayer_.getPosition(), futurePosition, actionDecision);
    }
}
