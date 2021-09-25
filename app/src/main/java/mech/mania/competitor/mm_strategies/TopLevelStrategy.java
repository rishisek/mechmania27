package mech.mania.competitor.mm_strategies;

import mech.mania.competitor.mm_core.Manager;
import mech.mania.competitor.mm_models.DecisionPair;
import mech.mania.competitor.model.ItemType;
import mech.mania.competitor.model.UpgradeType;
import mech.mania.competitor.model.decisions.DoNothingDecision;
import mech.mania.competitor.model.decisions.MoveDecision;
import mech.mania.competitor.networking.Logger;

import java.util.ArrayList;

public class TopLevelStrategy extends Strategy {
  private static final Logger logger = new Logger();

  @Override
  public ArrayList<DecisionPair> getDecisions(Manager manager) {
    ArrayList<DecisionPair> decisions = new ArrayList<>();

    if (manager.game_.getGameState().getOpponentPlayer().getItem()
        == ItemType.SCARECROW.toString()) {
      if (manager.game_.getGameState().getOpponentPlayer().getUpgrade()
          == UpgradeType.LONGER_LEGS.toString()) {
        if (!manager.opponentTracker.hasOpponentSpentEver) {
          decisions.add(
              new DecisionPair(
                  new MoveDecision(manager.game_.getGameState().getMyPlayer().getPosition()),
                  new DoNothingDecision()));
          return decisions;
        } else {
          // Do scarecrow
          Strategy strategy = new Strat_Scarecrow();
          decisions.addAll(strategy.getDecisions(manager));
          return decisions;
        }
      } else {
        // Do two plot.
      }
    } else {
      // Do peanut grief.
      Strategy strategy = new Strategy_Two();
      decisions.addAll(strategy.getDecisions(manager));
      return decisions;
    }

    return decisions;
  }
}
