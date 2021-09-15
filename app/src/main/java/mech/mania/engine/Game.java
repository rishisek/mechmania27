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
        sendItem(item.toString());
        sendUpgrade(upgrade.toString());

        gson = new Gson();
        String constantsJson = "";
        try {
            constantsJson = EngineCommunicator.readLine();

        } catch (IOException e) {
            System.exit(-1);
        }

        config = gson.fromJson(constantsJson, Config.class);
        EngineCommunicator.gameConfig = config;
//        constants = new Constants("mm27");
    }

    public void updateGame() throws IOException {
        String gameStateJson = EngineCommunicator.readLine();
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
