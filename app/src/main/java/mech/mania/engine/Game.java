package mech.mania.engine;

import mech.mania.engine.api.Config;
import mech.mania.engine.model.GameState;
import mech.mania.engine.model.decisions.ActionDecision;
import mech.mania.engine.model.decisions.MoveDecision;
import mech.mania.engine.model.ItemType;
import mech.mania.engine.model.UpgradeType;
import mech.mania.engine.networking.EngineCommunicator;
import com.google.gson.Gson;

import java.io.IOException;

public class Game {

    public final Config config;
    public final Gson gson;
    public GameState gameState;
    // private Gson gson = new GsonBuilder().create();

    public Game(ItemType item, UpgradeType upgrade) {
        EngineCommunicator.sendOut("heartbeat");

        sendItem(item.toString());
        sendUpgrade(upgrade.toString());

        config = new Config("mm27");
        EngineCommunicator.gameConfig = config;

        gson = new Gson();
    }

    public void updateGame() throws IOException {
        System.err.println("Waiting for gamestate");
        String gameStateJson = EngineCommunicator.readLine();
        System.err.println("received gamestate");
        gameState = gson.fromJson(gameStateJson, GameState.class);
    }

    public void sendItem(String item) {
        EngineCommunicator.sendOut(item);
    }

    public void sendUpgrade(String upgrade) {
        EngineCommunicator.sendOut(upgrade);
    }

    public void sendMoveDecision(MoveDecision decision) {
        EngineCommunicator.sendOut(decision.toString());
    }

    public void sendActionDecision(ActionDecision decision) {
        EngineCommunicator.sendOut(decision.toString());
    }
}
