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

        // Buy peanuts
        List<String> peanuts = new ArrayList<>();
        peanuts.add(CropType.PEANUT.toString());

        double moneyAmount = manager.game_.getGameState().getMyPlayer().getMoney();
        double numberOfPeanuts = (moneyAmount / 2) / CropType.PEANUT.getSeedBuyPrice();
        List<Integer> amount = new ArrayList<>();
        amount.add((int)numberOfPeanuts);

        pairs.addAll(manager.utilities.moveToPosition(manager.utilities.getNearestGrocer(),
                new BuyDecision(peanuts, amount)));

        // Move in the correct pattern and plant the peanuts
        int xPos = 11;
        int yPos = 5;

        int xIncrement = 4;
        int yIncrement = 4;

        ArrayList<DecisionPair> temp = manager.utilities.moveToPosition(new Position(xPos, yPos),
                new DoNothingDecision()); // should I plant here instead of do nothing

        temp.remove(temp.size() - 1);
        pairs.addAll(temp);

        for (int i = 3; i > 0; i--) {
            for (int j = i; j > 0; j--) {
                Position currentPosition = new Position(xPos, yPos);

                List<Position> positions = new ArrayList<>();
                positions.add(0, new Position(xPos - 1, yPos - 1));
                positions.add(1, new Position(xPos + 1, yPos + 1));
                positions.add(2, new Position(xPos - 1, yPos + 1));
                positions.add(3, new Position(xPos + 1, yPos - 1));

                pairs.add(new DecisionPair(new MoveDecision(currentPosition), new PlantDecision(peanuts, positions)));
                xPos += xIncrement;
            }
            yPos += yIncrement;
        }

        return pairs;
    }
}
