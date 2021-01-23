package mech.mania.engine.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import mech.mania.engine.config.Config;

public class GameState {
    @Expose
    private int turn;
    @Expose
    @SerializedName("p1")
    private Player player1;
    @Expose
    @SerializedName("p2")
    private Player player2;
    @Expose
    private TileMap tileMap;
    @Expose
    private int playerNum;

    public int getTurn() {
        return turn;
    }

    public TileMap getTileMap() {
        return tileMap;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getPlayer(int playerID) {
        if (playerID == 0) {
            return player1;
        }
        return player2;
    }

    public Player getOpponentPlayer(int playerID) {
        if (playerID == 0) {
            return player2;
        }
        return player1;
    }
}
