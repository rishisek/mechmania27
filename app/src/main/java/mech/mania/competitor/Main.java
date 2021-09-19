package mech.mania.competitor;

import mech.mania.competitor.api.Constants;
import mech.mania.competitor.model.GameState;
import mech.mania.competitor.model.Position;
import mech.mania.competitor.model.ItemType;
import mech.mania.competitor.model.UpgradeType;
import mech.mania.competitor.model.decisions.*;

import mech.mania.competitor.api.GameUtil.*;
import mech.mania.competitor.networking.Logger;

import java.io.IOException;

/**
 * Class that runs the game.
 */
public class Main {

    private static final Constants constants = new Constants();
    private static final Logger logger = new Logger();

    /**
     * Returns a move decision for the turn given the current game state.
     * This is part 1 of 2 of the turn.
     *
     * Remember, you can only sell crops once you get to a Green Grocer tile,
     * and you can only harvest or plant within your harvest or plant radius.
     *
     * After moving (and submitting the move decision), you will be given a new
     * game state with both players in their updated positions.
     *
     * @param game The object that contains the game state and other related information
     * @return MoveDecision A location for the bot to move to this turn
     */
    private static MoveDecision getMoveDecision(Game game) {
        GameState gameState = game.getGameState();
        logger.debug(String.format("[Turn %d] Feedback received from engine: [%s]",
                gameState.getTurn(),
                String.join(", ", gameState.getFeedback())));

        Position currentPosition = gameState.getMyPlayer().getPosition();
        logger.debug(String.format("[Turn %d] Sending MoveDecision: [%s]",
                gameState.getTurn(), currentPosition));
        return new MoveDecision(currentPosition);
    }

    /**
     * Returns an action decision for the turn given the current game state.
     * This is part 2 of 2 of the turn.
     *
     * There are multiple action decisions that you can return here: BuyDecision,
     * HarvestDecision, PlantDecision, or UseItemDecision.
     *
     * After this action, the next turn will begin.
     *
     * @param game The object that contains the game state and other related information
     * @return ActionDecision A decision for the bot to make this turn
     */
    private static ActionDecision getActionDecision(Game game) {
        GameState gameState = game.getGameState();
        logger.debug(String.format("[Turn %d] Feedback received from engine: [%s]",
                gameState.getTurn(),
                String.join(", ", gameState.getFeedback())));

        ActionDecision toSend = new UseItemDecision();
        logger.debug(String.format("[Turn %d] Sending ActionDecision: [%s]",
                gameState.getTurn(), toSend));
        return toSend;
    }

    /**
     * Competitor TODO: choose an item and upgrade for your bot
     *
     * @param args Program arguments (ignored)
     */
    public static void main(String[] args) {
        Game game = new Game(ItemType.NONE, UpgradeType.NONE);

        while (true) {
            // Turn part 1: Move Decision
            try {
                game.updateGame();
            } catch (IOException e) {
                System.exit(-1);
            }
            game.sendMoveDecision(getMoveDecision(game));

            // Turn part 2: Action Decision
            try {
                game.updateGame();
            } catch (IOException e) {
                System.exit(-1);
            }
            game.sendActionDecision(getActionDecision(game));
        }
    }
}
