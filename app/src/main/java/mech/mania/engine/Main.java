package mech.mania.engine;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mech.mania.engine.config.Config;
import mech.mania.engine.core.GameLogic;
import mech.mania.engine.core.PlayerEndState;
import mech.mania.engine.logging.JsonLogger;
import mech.mania.engine.model.GameLog;
import mech.mania.engine.model.GameState;
import mech.mania.engine.model.PlayerDecisionParseException;
import mech.mania.engine.model.decisions.MoveAction;
import mech.mania.engine.model.decisions.PlayerDecision;
import mech.mania.engine.networking.PlayerCommunicationInfo;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Class that runs the game.
 */
public class Main {

    private static MoveDecision getMoveDecision(Game game) {

    }

    private static ActionDecision getActionDecision(Game game) {

    }

    /**
     * @param args Program arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.sendItem(ItemType.NONE);
        game.sendUpgrade(ItemType.NONE);

        while (true) {
            game.updateGame();
            game.sendMoveDecision(getMoveDecision(game));
            game.sendActionDecision(getActionDecision(game));
        }
    }
}
