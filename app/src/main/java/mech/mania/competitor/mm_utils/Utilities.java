package mech.mania.competitor.mm_utils;

import mech.mania.competitor.Game;
import mech.mania.competitor.api.GameUtil;
import mech.mania.competitor.model.GameState;
import mech.mania.competitor.model.Player;
import mech.mania.competitor.model.Position;
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

        // TODO: CHECK SCARECROW
    }

    public boolean isHarvestValid(ArrayList<Position> coords) {
        for (Position coord : coords) {
            if (!isHarvestValid(coord)) return false;
        }

        return true;
    }

    public Position getRelativePosition() {
        return getRelativePosition(gameState_.getOpponentPlayer().getPosition());
    }

    public Position getRelativePosition(Position position) {
        Position myPosition = myPlayer_.getPosition();

        return new Position(position.getX() - myPosition.getX(),
                position.getY() - myPosition.getY());
    }
}
