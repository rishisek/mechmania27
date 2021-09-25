package mech.mania.competitor.mm_core;

import mech.mania.competitor.Game;
import mech.mania.competitor.mm_models.StartGameConfig;
import mech.mania.competitor.mm_models.TurnState;
import mech.mania.competitor.mm_utils.Utilities;
import mech.mania.competitor.model.ItemType;
import mech.mania.competitor.model.UpgradeType;

;

public class Manager {
    public Utilities utilities;

    public OpponentTracker opponentTracker = new OpponentTracker();

    private Game game_;
    private TurnState turnState_;

    public Manager() {

    }

    public static StartGameConfig startGame() {
        return new StartGameConfig(ItemType.SCARECROW, UpgradeType.LONGER_LEGS);
    }

    public void setGame(Game game) {
        game_ = game;

        utilities = new Utilities(game);
        opponentTracker.update(game, utilities);
    }

    public void setTurnState(TurnState turnState) {
        turnState_ = turnState;
    }
}
