package mech.mania.engine;

import mech.mania.engine.api.Constants;
import mech.mania.engine.model.GameState;
import mech.mania.engine.model.decisions.ActionDecision;
import mech.mania.engine.model.decisions.MoveDecision;
import mech.mania.engine.networking.EngineCommunicator;

public class Game {

    public final Constants constants;
    // private Gson gson = new GsonBuilder().create();

    public Game() {
        String constantsJson = EngineCommunicator.readLine();
        // constants = gson.fromJson(constantsJson, Constants.class);
    }

    public void updateGame() {
        String gameStateJson = EngineCommunicator.readLine();
        // GameState gameState = gson.fromJson(gameStateJson, GameState.class);

        // TODO: update game object using game state
    }

    public void sendItem(String item) {
        EngineCommunicator.sendString(item);
    }

    public void sendUpgrade(String upgrade) {
        EngineCommunicator.sendString(upgrade);
    }

    public void sendMoveDecision(MoveDecision decision) {
        EngineCommunicator.sendString(decision.toString());
    }

    public void sendActionDecision(ActionDecision decision) {
        EngineCommunicator.sendString(decision.toString());
    }
}
