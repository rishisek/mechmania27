package mech.mania.competitor.mm_strategies;

import mech.mania.competitor.Game;
import mech.mania.competitor.mm_core.Manager;
import mech.mania.competitor.model.decisions.ActionDecision;
import mech.mania.competitor.model.decisions.MoveDecision;

abstract public class Strategy {
  abstract MoveDecision getMoveDecision(Manager manager);
  abstract ActionDecision getActionDecision(Manager manager);
}
