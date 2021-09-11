package mech.mania.engine;

import mech.mania.engine.api.Config;
import mech.mania.engine.model.GameState;
import mech.mania.engine.model.decisions.ActionDecision;
import mech.mania.engine.model.decisions.MoveDecision;
import mech.mania.engine.networking.EngineCommunicator;
import com.google.gson.Gson;

public class Game {

    public final Config config;
    public final Gson gson;
    public GameState gameState;
    // private Gson gson = new GsonBuilder().create();

    public Game() {
        // TODO player: set your name and select your item and upgrade
        sendPlayerName("ABCD");
        sendItem("NONE");
        sendUpgrade("NONE");

        String constantsJson = EngineCommunicator.readLine();
        gson = new Gson();
        config = gson.fromJson(constantsJson, Config.class);
//        constants = new Constants("mm27");
    }

    public void updateGame() {
        while (true) { // TODO fix this?
            String gameStateJson = EngineCommunicator.readLine();
            gameState = gson.fromJson(gameStateJson, GameState.class);

            makeDecision();
        }

    }

    public void makeDecision() {
        // TODO: PLAYER DO SOMETHING HERE
    }

    public void sendPlayerName(String name) {
        EngineCommunicator.sendString(name);
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
