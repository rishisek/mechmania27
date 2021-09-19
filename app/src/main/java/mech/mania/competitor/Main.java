package mech.mania.competitor;

import mech.mania.competitor.model.Position;
import mech.mania.competitor.model.ItemType;
import mech.mania.competitor.model.UpgradeType;
import mech.mania.competitor.model.decisions.*;

import java.io.IOException;

/**
 * Class that runs the game.
 */
public class Main {

    private static MoveDecision getMoveDecision(Game game) {
        return new MoveDecision(new Position(1, 1));
    }

    private static ActionDecision getActionDecision(Game game) {
        return new NullDecision();
    }

    /**
     * @param args Program arguments
     */
    public static void main(String[] args) {
        Game game = new Game(ItemType.NONE, UpgradeType.NONE);

        while (true) {
            try {
                game.updateGame();
            } catch (IOException e) {
                System.exit(-1);
            }

            game.sendMoveDecision(getMoveDecision(game));
            game.sendActionDecision(getActionDecision(game));
        }
    }
}
