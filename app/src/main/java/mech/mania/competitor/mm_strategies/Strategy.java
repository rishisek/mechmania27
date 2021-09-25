package mech.mania.competitor.mm_strategies;

import mech.mania.competitor.mm_core.Manager;
import mech.mania.competitor.mm_models.DecisionPair;
import mech.mania.competitor.model.decisions.ActionDecision;
import mech.mania.competitor.model.decisions.MoveDecision;

import javax.swing.*;
import java.util.ArrayList;

abstract public class Strategy {
  public abstract ArrayList<DecisionPair> getDecisions(Manager manager);
}
