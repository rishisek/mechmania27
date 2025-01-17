package mech.mania.competitor.mm_models;

import mech.mania.competitor.model.decisions.ActionDecision;
import mech.mania.competitor.model.decisions.MoveDecision;

public class DecisionPair {
  public MoveDecision moveDecision;
  public ActionDecision actionDecision;

  public DecisionPair(MoveDecision moveDecision, ActionDecision actionDecision) {
    this.moveDecision = moveDecision;
    this.actionDecision = actionDecision;
  }
}
