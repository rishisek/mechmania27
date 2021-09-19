package mech.mania.competitor;

import mech.mania.competitor.api.Constants;
import mech.mania.competitor.model.GameState;
import mech.mania.competitor.model.decisions.ActionDecision;
import mech.mania.competitor.model.decisions.MoveDecision;
import mech.mania.competitor.model.ItemType;
import mech.mania.competitor.model.UpgradeType;
import mech.mania.competitor.networking.EngineCommunicator;
import com.google.gson.Gson;

import java.io.IOException;

public class Game {

    public final Constants constants;
    public final Gson gson;
    public GameState gameState;
    // private Gson gson = new GsonBuilder().create();

    public Game(ItemType item, UpgradeType upgrade) {
        EngineCommunicator.sendOut("heartbeat");

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
