package mech.mania.engine;

public class Game {

    public final Constants constants;
    private Gson gson = new GsonBuilder().create();

    public Game() {
        String constantsJson = EngineCommunicator.readLine();
        constants = gson.fromJson(constantsJson, Constants.class);
    }

    public void updateGame() {
        String gameStateJson = EngineCommunicator.readLine();
        GameState gameState = gson.fromJson(gameStateJson, GameState.class);

        // TODO: update game object using game state
    }

    public void sendMoveDecision(MoveDecision decision) {
        EngineCommunicator.sendString(decision.toString());
    }

    public void sendActionDecision(ActionDecision decision) {
        EngineCommunicator.sendString(decision.toString());
    }
}
