package mech.mania.competitor;

import mech.mania.competitor.api.Constants;
import mech.mania.competitor.mm_core.Manager;
import mech.mania.competitor.mm_models.DecisionPair;
import mech.mania.competitor.mm_models.TurnState;
import mech.mania.competitor.mm_models.StartGameConfig;
import mech.mania.competitor.model.*;
import mech.mania.competitor.model.decisions.*;

import mech.mania.competitor.networking.Logger;

import java.io.IOException;
import java.util.*;

/**
 * Class that runs the game.
 */
public class Bot {

    private static final Constants constants = new Constants();
    private static final Logger logger = new Logger();
    private static final Random rand = new Random();

    private static final Manager manager = new Manager();

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
        DecisionPair decision = manager.nextDecision();

        GameState gameState = game.getGameState();
        logger.debug(String.format("[Turn %d] Feedback received from engine: [%s]",
                gameState.getTurn(),
                String.join(", ", gameState.getFeedback())));

        logger.debug(String.format("[Turn %d] Sending MoveDecision: %s",
                gameState.getTurn(), decision.moveDecision));

        return decision.moveDecision;
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
        DecisionPair decision = manager.nextDecision();

        GameState gameState = game.getGameState();
        logger.debug(String.format("[Turn %d] Feedback received from engine: [%s]",
                gameState.getTurn(),
                String.join(", ", gameState.getFeedback())));
        
        logger.debug(String.format("[Turn %d] Sending ActionDecision: %s",
                gameState.getTurn(), decision.actionDecision));
        return decision.actionDecision;
    }

    /**
     * Competitor TODO: choose an item and upgrade for your bot
     *
     * @param args Program arguments (ignored)
     */
    public static void main(String[] args) {
        StartGameConfig startGameConfig = Manager.startGame();
        Game game = new Game(startGameConfig.item_, startGameConfig.upgrade_);

        while (true) {
            // Turn part 1: Move Decision
            try {
                game.updateGame();
                manager.setGame(game);
            } catch (IOException e) {
                System.exit(-1);
            }
            DecisionPair decision = manager.nextDecision();

            manager.setTurnState(TurnState.Move);
            game.sendMoveDecision(decision.moveDecision);

            // Turn part 2: Action Decision
            try {
                game.updateGame();
                manager.setGame(game);
            } catch (IOException e) {
                System.exit(-1);
            }

            manager.setTurnState(TurnState.Action);
            game.sendActionDecision(decision.actionDecision);
        }
    }
}
