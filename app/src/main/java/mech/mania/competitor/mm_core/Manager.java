package mech.mania.competitor.mm_core;

import mech.mania.competitor.Game;
import mech.mania.competitor.mm_models.DecisionPair;
import mech.mania.competitor.mm_models.StartGameConfig;
import mech.mania.competitor.mm_models.TurnState;
import mech.mania.competitor.mm_strategies.Strat_Scarecrow;
import mech.mania.competitor.mm_strategies.Strategy;
import mech.mania.competitor.mm_strategies.TopLevelStrategy;
import mech.mania.competitor.mm_utils.Utilities;
import mech.mania.competitor.model.ItemType;
import mech.mania.competitor.model.Position;
import mech.mania.competitor.model.UpgradeType;
import mech.mania.competitor.model.decisions.ActionDecision;
import mech.mania.competitor.model.decisions.DoNothingDecision;
import mech.mania.competitor.model.decisions.MoveDecision;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Queue;

public class Manager {
  public Utilities utilities;

  public OpponentTracker opponentTracker = new OpponentTracker();
  public Game game_;

  // TODO: write code to switch strategies.
  // TODO: implement the strategy abstract class, and the individual strategies
  public TurnState turnState_;
  // TODO: store current strategy used here.
  // TODO: store queue of decisions here, make a mm_models containing both Move Action Decision
  // Pair.
  private ArrayList<DecisionPair> decisionQueue = new ArrayList<>();

  public Manager() {}

  public static StartGameConfig startGame() {
    return new StartGameConfig(ItemType.SCARECROW, UpgradeType.LONGER_LEGS);
  }

  public DecisionPair nextDecision() {
    // TODO: ideally, pull from queue.
    // TODO: template code, remove.
    if (decisionQueue.isEmpty())
      return new DecisionPair(
          new MoveDecision(game_.getGameState().getMyPlayer().getPosition()),
          new DoNothingDecision());
    return decisionQueue.remove(0);
  }

  public void setGame(Game game) {
    game_ = game;

    utilities = new Utilities(game);
    opponentTracker.update(game, utilities);

    // TODO: you should check and update strategy here, or in a helper function you call here.
    if (decisionQueue.isEmpty()) {
      Strategy strategy = new TopLevelStrategy();
      decisionQueue.addAll(strategy.getDecisions(this));
    }
  }

  public void setTurnState(TurnState turnState) {
    turnState_ = turnState;
  }
}
