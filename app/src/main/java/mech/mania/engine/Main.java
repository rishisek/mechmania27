package mech.mania.engine;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mech.mania.engine.model.Position;
import mech.mania.engine.model.decisions.ActionDecision;
import mech.mania.engine.model.decisions.MoveDecision;

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
        return new MoveDecision(new Position(1, 1));
    }

    private static ActionDecision getActionDecision(Game game) {
        return new ActionDecision();
    }

    /**
     * @param args Program arguments
     */
    public static void main(String[] args) {
        Game game = new Game();

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
