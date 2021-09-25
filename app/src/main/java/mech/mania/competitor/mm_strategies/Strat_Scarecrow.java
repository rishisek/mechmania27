package mech.mania.competitor.mm_strategies;

import javafx.geometry.Pos;
import mech.mania.competitor.mm_core.Manager;
import mech.mania.competitor.mm_models.DecisionPair;
import mech.mania.competitor.model.CropType;
import mech.mania.competitor.model.Position;
import mech.mania.competitor.model.decisions.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Strat_Scarecrow extends Strategy {

    /*
     * 1) Depending as to where we start in the game board (right or left) move down and to either 5 to the left or 5 to
     *      the right in the game board
     * 2) Place the scarecrow in the exact center and then plant potatoes in the surrounding perimeter
     */
    /* public ArrayList<DecisionPair> getDecisions(Manager manager) {
        ArrayList<DecisionPair> decisions = new ArrayList<>();

        //Scarecrow placement positions
        Position leftScarecrowPosition = new Position(2, 47);
        Position rightScarecrowPosition = new Position(27, 47);

        //Player local vars
        int currentX = manager.game_.getGameState().getMyPlayer().getPosition().getX();
        int currentY = manager.game_.getGameState().getMyPlayer().getPosition().getY();
        int moveDownBy = (20 - currentY) - 2;

        //We need to first buy seeds at the market


        //If we start on the top left of the field
        if (currentX == 0) {
            //Move to the left-most center position to place the scarecrow
            int distanceToLeftScarecrow = manager.utilities.getDistance(leftScarecrowPosition);

            if (distanceToLeftScarecrow > 20) {
                decisions.add(new DecisionPair(new MoveDecision(new Position(2, moveDownBy)), new DoNothingDecision()));
            } else {
                decisions.add(new DecisionPair(new MoveDecision(leftScarecrowPosition), new UseItemDecision()));
            }
        }

        //If we start on the top right of the field
        else if (currentX == 29) {
            //Move to the right-most center position to place the scarecrow
            int distanceToRightScarecrow = manager.utilities.getDistance(rightScarecrowPosition);

            if (distanceToRightScarecrow > 20) {
                decisions.add(new DecisionPair(new MoveDecision(new Position(27, moveDownBy)), new DoNothingDecision()));
            } else {
                decisions.add(new DecisionPair(new MoveDecision(rightScarecrowPosition), new UseItemDecision()));
            }
        }

        //We need to plant potatoes all around the Scarecrow here with somewhere less than 7 terms

        return decisions;
    }*/

    public ArrayList<DecisionPair> getDecisions(Manager manager) {
        ArrayList<DecisionPair> decisions = new ArrayList<>();

        //Player
        Position currentPosition = manager.game_.getGameState().getMyPlayer().getPosition();

        //Green Grocer
        ArrayList<Position> greenGrocerTiles = manager.game_.getGameState().getTileMap().getGreenGrocerTiles();
        Position closestGrocerTile = getClosestPosition(greenGrocerTiles, manager);

        //Scarecrows
        Position leftScarecrowPosition = new Position(2, 47);
        Position rightScarecrowPosition = new Position(27, 47);

        ArrayList<Position> scarecrowPositions = new ArrayList<>();
        scarecrowPositions.add(leftScarecrowPosition);
        scarecrowPositions.add(rightScarecrowPosition);

        Position closestScarecrow = getClosestPosition(scarecrowPositions, manager);

        //Move to nearest grocer
        //TODO: Not sure if I need to do (20 - blah blah) - blah blah to accommodate for the 20 steps we need
        int moveToGrocerX = closestGrocerTile.getX() - currentPosition.getX();
        int moveToGrocerY = closestGrocerTile.getY() - currentPosition.getY();

        //Buy 25 potatoes needed to grow them around the scarecrow
        List<String> potatoes = new ArrayList<>();
        potatoes.add(CropType.POTATO.toString());

        List<Integer> amount = new ArrayList<>();
        amount.add(25);

        decisions.add(new DecisionPair(new MoveDecision(new Position(moveToGrocerX, moveToGrocerY)),
                                                        new BuyDecision(potatoes, amount)));

        //Move to the nearest scarecrow and place the scarecrow
        int distanceToClosestScarecrow = manager.utilities.getDistance(closestScarecrow);

        int moveToScarecrowX = (20 - closestScarecrow.getX());
        int moveToScarecrowY = moveToScarecrowX - closestScarecrow.getY();

        if (distanceToClosestScarecrow > 20) {
            decisions.add(new DecisionPair(new MoveDecision(new Position(moveToScarecrowX, moveToScarecrowY)), new DoNothingDecision()));
        } else {
            decisions.add(new DecisionPair(new MoveDecision(closestScarecrow), new UseItemDecision()));
        }

        //TODO: Write method to plant the potatoes in a surrounding border, make a double for loop for the diff changes
        List<Position> changePositionsToPlant = getChangePositionToPlant();
        List<Position> potatoPositions = getPotatoPositionToPlant();

        for (int i = 0; i < changePositionsToPlant.size(); i++) {
            int tempCurrentPositionX = currentPosition.getX() + changePositionsToPlant.get(i).getX();
            int tempCurrentPositionY = currentPosition.getY() + changePositionsToPlant.get(i).getY();

            currentPosition = new Position(tempCurrentPositionX, tempCurrentPositionY);

            for (Position potatoPosition : potatoPositions) {
                List<Position> potatoPlantingPositions = new ArrayList<>();

                int positionPlantingXBasedOnCenter = currentPosition.getX() - potatoPosition.getX();
                int positionPlantingYBasedOnCenter = currentPosition.getY() - potatoPosition.getY();

                //TODO: Need to check this logic makes sense, not sure whether to use i or j
                potatoPlantingPositions.add(i, new Position(positionPlantingXBasedOnCenter, positionPlantingYBasedOnCenter));

                //Here we add
                decisions.add(new DecisionPair(new MoveDecision(currentPosition), new PlantDecision(potatoes, potatoPlantingPositions)));
            }
        }

        return decisions;
    }

    public Position getClosestPosition(ArrayList<Position> positions, Manager manager) {
        Position closestTilePosition = new Position(0, 0);

        //TODO: Need to figure out a better value for this
        double closestTileDistance = 100;

        for (Position tilePosition : positions) {
            int distance = manager.utilities.getDistance(new Position(tilePosition));

            if (distance < closestTileDistance) {
                closestTilePosition = tilePosition;
            }
        }

        return closestTilePosition;
    }

    public List<Position> getChangePositionToPlant() {
        //Positions where player should be at to plant
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
        //Specific coordinates for where each potato should be planted
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
