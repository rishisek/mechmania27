package mech.mania.engine;

import mech.mania.engine.api.Constants;
import mech.mania.engine.model.GameState;
import mech.mania.engine.model.decisions.ActionDecision;
import mech.mania.engine.model.decisions.MoveDecision;
import mech.mania.engine.model.ItemType;
import mech.mania.engine.model.UpgradeType;
import mech.mania.engine.networking.EngineCommunicator;
import com.google.gson.Gson;

import java.io.IOException;

public class Game {

    public final Constants constants;
    public final Gson gson;
    public GameState gameState;
    // private Gson gson = new GsonBuilder().create();

    public Game(ItemType item, UpgradeType upgrade) {
        EngineCommunicator.sendString("heartbeat");

        sendItem(item.toString());
        sendUpgrade(upgrade.toString());

        constants = new Constants("mm27");
        EngineCommunicator.gameConstants = constants;

        gson = new Gson();
    }

    public void updateGame() throws IOException {
        System.err.println("Waiting for gamestate");
        String gameStateJson = EngineCommunicator.readLine();
        System.err.println("received gamestate");
        gameState = gson.fromJson(gameStateJson, GameState.class);
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
