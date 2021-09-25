package mech.mania.competitor.mm_strategies;

import mech.mania.competitor.Game;
import mech.mania.competitor.mm_core.Manager;
import mech.mania.competitor.mm_models.DecisionPair;
import mech.mania.competitor.mm_strategies.Strategy;
import mech.mania.competitor.model.CropType;
import mech.mania.competitor.model.GameState;
import mech.mania.competitor.model.Position;
import mech.mania.competitor.model.decisions.*;

import java.util.ArrayList;
import java.util.List;

public class Strategy_Two extends Strategy {

    @Override
    public ArrayList<DecisionPair> getDecisions(Manager manager) {
        ArrayList<DecisionPair> pairs = new ArrayList<>();
        int numberOfPeanutPatches = 7;

        // TODO:: Move from start to buy house
        /*
           findClosestPath(currentPosition, futurePosition);
         */

        // TODO:: Buy peanuts
        List<String> peanuts = new ArrayList<>();
        peanuts.add(CropType.PEANUT.toString());

        double moneyAmount = manager.game_.getGameState().getMyPlayer().getMoney();
        double numberOfPeanuts = (moneyAmount / 2) / CropType.PEANUT.getSeedBuyPrice();
        List<Integer> amount = new ArrayList<>();
        amount.add((int)numberOfPeanuts);

        manager.utilities.moveToPosition(manager.game_.getGameState().getMyPlayer().getPosition(),
                new Position(2, 5), new BuyDecision(peanuts, amount)); // coords of crops

        //pairs.add(new DecisionPair(new MoveDecision(), new BuyDecision(peanuts, amount))); // TODO:: add to array

        // BIG ISSUE^^ I CANT DO JUST AN ACTION AND NOT MOVE, so i made the moveToPosition take in an action

        //manager.game_.sendActionDecision(new BuyDecision(peanuts, amount));

        // TODO:: Move in the correct pattern and plant the peanuts
        int xPos = 5;
        int yPos = 2;

        int xIncrement = 3;
        int yIncrement = 5;

        int rows = 30;
        int columns = 50;

        manager.utilities.moveToPosition(manager.game_.getGameState().getMyPlayer().getPosition(),
                new Position(xPos, yPos), new DoNothingDecision()); // should I plant here instead of do nothing

        for (int i = 0; i < numberOfPeanutPatches; i++) {
            if (xPos + xIncrement < rows) {
                yPos += xIncrement;
            }
            if (xPos + yIncrement < columns / 3) {
                yPos += yIncrement;
            } else {
                yPos -= yIncrement;
            }

            Position currentPosition = new Position(xPos, yPos);

            List<Position> positions = new ArrayList<>();
            positions.add(0, new Position(xPos - 1, yPos - 1));
            positions.add(1, new Position(xPos + 1, yPos + 1));
            positions.add(2, new Position(xPos - 1, yPos + 1));
            positions.add(3, new Position(xPos + 1, yPos - 1));

            pairs.add(new DecisionPair(new MoveDecision(currentPosition), new PlantDecision(peanuts, positions)));
        }

        return pairs;
    }
}
