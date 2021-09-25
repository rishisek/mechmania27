package mech.mania.competitor.mm_strategies;

import mech.mania.competitor.Game;
import mech.mania.competitor.model.decisions.ActionDecision;
import mech.mania.competitor.model.decisions.MoveDecision;

abstract public class Strategy {
  abstract MoveDecision getMoveDecision(Game game);
  abstract ActionDecision getActionDecision(Game game);
}
