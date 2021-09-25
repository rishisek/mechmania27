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

  enum Phase { SCARECROW_PLANT, PEANUT_PLANT, TWO_PLOT, HARVEST}

  private Strategy getStrategy(Phase phase){
     switch(phase) {
       case SCARECROW_PLANT: return new Strat_Scarecrow();
       case PEANUT_PLANT: return new Strategy_Two();
       default: return new Strategy_Harvester();
    }
  }

  private ArrayList<DecisionPair> getDecisionsForStrategy(Phase phase, Manager manager){
    return getStrategy(phase).getDecisions(manager);
  }

  @Override
  public ArrayList<DecisionPair> getDecisions(Manager manager) {
    ArrayList<DecisionPair> decisions = new ArrayList<>();
//
//    if (manager.game_.getGameState().getOpponentPlayer().getItem()
//        == ItemType.SCARECROW.toString()) {
//      if (manager.game_.getGameState().getOpponentPlayer().getUpgrade()
//          == UpgradeType.LONGER_LEGS.toString()) {
//        if (!manager.opponentTracker.hasOpponentSpentEver) {
//          decisions.add(
//              new DecisionPair(
//                  new MoveDecision(manager.game_.getGameState().getMyPlayer().getPosition()),
//                  new DoNothingDecision()));
//          return decisions;
//        } else {
//          // Do scarecrow
//          decisions.addAll(getDecisionsForStrategy(Phase.SCARECROW_PLANT, manager));
//        }
//      } else {
//        // Do two plot.
//      }
//    } else {
//      decisions.addAll(getDecisionsForStrategy(Phase.SCARECROW_PLANT, manager));
//
//      // Do peanut grief.
//      decisions.addAll(getDecisionsForStrategy(Phase.PEANUT_PLANT,manager));
//    }

    decisions.addAll(getDecisionsForStrategy(Phase.HARVEST, manager));

    return decisions;
  }
}
