package mech.mania.competitor.mm_strategies;

import mech.mania.competitor.mm_core.Manager;
import mech.mania.competitor.mm_models.DecisionPair;

import java.util.ArrayList;

abstract public class Strategy {
  private ArrayList<DecisionPair> decisions;

  public ArrayList<DecisionPair> getDecisions() {
    return decisions;
  }

  protected abstract void makeMoveDecision(Manager manager);
  protected abstract void makeActionDecision(Manager manager);
}
