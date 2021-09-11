package mech.mania.engine.api;

import mech.mania.engine.model.GameState;
import mech.mania.engine.model.Player;
import mech.mania.engine.model.Position;
import mech.mania.engine.model.TileType;

import java.util.ArrayList;
import java.util.List;

public class GameUtil {
    public static Player getPlayerFromName(GameState state, String playerName) {
        return state.getPlayer1().getName().equals(playerName) ? state.getPlayer1() : state.getPlayer2();
    }

    public static List<Position> withinRange(GameState state, String playerName) {
        Player myPlayer = getPlayerFromName(state, playerName);
        int speed = myPlayer.getMaxMovement();

        List<Position> res = new ArrayList<>();

        for (int i = myPlayer.getPosition().getY() - speed; i < myPlayer.getPosition().getY() + speed; i++) {
            int leftoverTravel = Math.abs(speed - (myPlayer.getPosition().getY() - i));
            for (int j = myPlayer.getPosition().getX() - leftoverTravel; j < myPlayer.getPosition().getX() + leftoverTravel; j++) {
                res.add(new Position(j, i));
            }
        }

        return res;
    }

    public static TileType typeInTurns(Position pos, int numTurns) {
        return null;
    }
}
