package mech.mania.competitor.mm_core;

import mech.mania.competitor.Game;
import mech.mania.competitor.mm_models.StartGameConfig;
import mech.mania.competitor.mm_models.TurnState;
import mech.mania.competitor.mm_utils.Utilities;
import mech.mania.competitor.model.ItemType;
import mech.mania.competitor.model.Position;
import mech.mania.competitor.model.UpgradeType;
import mech.mania.competitor.model.decisions.ActionDecision;
import mech.mania.competitor.model.decisions.DoNothingDecision;
import mech.mania.competitor.model.decisions.MoveDecision;

public class Manager {
    public Utilities utilities;

    public OpponentTracker opponentTracker = new OpponentTracker();

    // TODO: store current strategy used here.
    // TODO: store queue of decisions here, make a mm_models containing both Move Action Decision Pair.

    // TODO: write code to switch strategies.
    // TODO: implement the strategy abstract class, and the individual strategies

    public Game game_;
    public TurnState turnState_;

    public Manager() {}

    public static StartGameConfig startGame() {
        return new StartGameConfig(ItemType.SCARECROW, UpgradeType.LONGER_LEGS);
    }

    public MoveDecision nextMoveDecision() {
        // TODO: ideally, pull from queue.
        // TODO: template code, remove.
        return new MoveDecision(new Position(0, 0));
    }

    public ActionDecision nextActionDecision() {
        // TODO: ideally, pull from queue.
        // TODO: template code, remove.
        return new DoNothingDecision();
    }

    public void setGame(Game game) {
        game_ = game;

        utilities = new Utilities(game);
        opponentTracker.update(game, utilities);

        // TODO: you should check and update strategy here, or in a helper function you call here.
    }

    public void setTurnState(TurnState turnState) {
        turnState_ = turnState;
    }
}
