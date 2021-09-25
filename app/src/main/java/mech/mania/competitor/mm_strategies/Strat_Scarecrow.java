package mech.mania.competitor.mm_strategies;

import mech.mania.competitor.mm_core.Manager;
import mech.mania.competitor.mm_models.DecisionPair;
import mech.mania.competitor.model.CropType;
import mech.mania.competitor.model.Position;
import mech.mania.competitor.model.decisions.*;
import mech.mania.competitor.networking.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Strat_Scarecrow extends Strategy {
  private static final Logger logger = new Logger();

  public ArrayList<DecisionPair> getDecisions(Manager manager) {
    ArrayList<DecisionPair> decisions = new ArrayList<>();

    // Green Grocer
    Position closestGrocerTile = manager.utilities.getNearestGrocer();

    // Buy 25 potatoes needed to grow them around the scarecrow
    List<String> potatoes = new ArrayList<>();
    potatoes.add(CropType.POTATO.toString());

    List<Integer> amount = new ArrayList<>();
    amount.add(25);

    decisions.add(
        new DecisionPair(new MoveDecision(closestGrocerTile), new BuyDecision(potatoes, amount)));


    // Scarecrows
    Position currentPosition = closestGrocerTile;

    //New Middle Scarecrow
    Position middleScarecrowPosition = new Position(15, 47);

    int distanceToMiddleScarecrow = manager.utilities.getDistance(middleScarecrowPosition);

    // Move to the nearest scarecrow and place the scarecrow
    int distanceToClosestScarecrow = manager.utilities.getRelativeDistance(currentPosition, middleScarecrowPosition);
    while (distanceToClosestScarecrow > 20) {
      currentPosition =
          new Position(
              middleScarecrowPosition.getX(),
              currentPosition.getY() + 20 - middleScarecrowPosition.getX() + currentPosition.getX());
      decisions.add(new DecisionPair(new MoveDecision(currentPosition), new DoNothingDecision()));
      distanceToClosestScarecrow = manager.utilities.getRelativeDistance(currentPosition, middleScarecrowPosition);
      logger.debug(Integer.toString(distanceToClosestScarecrow));
    }

    logger.debug("kkkkkkkkkkkkkkkkkkkk");
    decisions.add(new DecisionPair(new MoveDecision(middleScarecrowPosition), new UseItemDecision()));

    // TODO: Write method to plant the potatoes in a surrounding border, make a double for loop for
    // the diff changes
    List<Position> changePositionsToPlant = getChangePositionToPlant();
    List<Position> potatoPositions = getPotatoPositionToPlant();

    currentPosition = middleScarecrowPosition;
    for (int i = 0; i < changePositionsToPlant.size(); i++) {
      int tempCurrentPositionX = currentPosition.getX() + changePositionsToPlant.get(i).getX();
      int tempCurrentPositionY = currentPosition.getY() + changePositionsToPlant.get(i).getY();

      currentPosition = new Position(tempCurrentPositionX, tempCurrentPositionY);

      decisions.add(
          new DecisionPair(
              new MoveDecision(currentPosition),
              new PlantDecision(potatoes, potatoPositions)));
    }

    logger.debug(decisions.toString());

    return decisions;
  }

  public List<Position> getChangePositionToPlant() {
    // Positions where player should be at to plant
    Position plantingPosition1 = new Position(-1, -1);
    Position plantingPosition2 = new Position(2, 0);
    Position plantingPosition3 = new Position(0, 2);
    Position plantingPosition4 = new Position(-2, 0);

    List<Position> changePositionsToPlant = new ArrayList<>();

    changePositionsToPlant.add(plantingPosition1);
    changePositionsToPlant.add(plantingPosition2);
    changePositionsToPlant.add(plantingPosition3);
    changePositionsToPlant.add(plantingPosition4);

    return changePositionsToPlant;
  }

  public List<Position> getPotatoPositionToPlant() {
    // Specific coordinates for where each potato should be planted
    Position potatoPosition1 = new Position(0, 0);
    Position potatoPosition2 = new Position(0, -1);
    Position potatoPosition3 = new Position(-1, -1);
    Position potatoPosition4 = new Position(1, 0);
    Position potatoPosition5 = new Position(1, 1);
    Position potatoPosition6 = new Position(0, 1);
    Position potatoPosition7 = new Position(-1, 1);
    Position potatoPosition8 = new Position(-1, 0);
    Position potatoPosition9 = new Position(-1, -1);

    List<Position> potatoPositionsToPlant = new ArrayList<>();

    potatoPositionsToPlant.add(potatoPosition1);
    potatoPositionsToPlant.add(potatoPosition2);
    potatoPositionsToPlant.add(potatoPosition3);
    potatoPositionsToPlant.add(potatoPosition4);
    potatoPositionsToPlant.add(potatoPosition5);
    potatoPositionsToPlant.add(potatoPosition6);
    potatoPositionsToPlant.add(potatoPosition7);
    potatoPositionsToPlant.add(potatoPosition8);
    potatoPositionsToPlant.add(potatoPosition9);

    return potatoPositionsToPlant;
  }
}
