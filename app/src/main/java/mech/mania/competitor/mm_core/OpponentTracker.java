package mech.mania.competitor.mm_core;

import mech.mania.competitor.Game;
import mech.mania.competitor.api.GameUtil;
import mech.mania.competitor.mm_utils.Utilities;
import mech.mania.competitor.model.GameState;
import mech.mania.competitor.model.ItemType;
import mech.mania.competitor.model.Player;
import mech.mania.competitor.model.UpgradeType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OpponentTracker {
    public static double PAST_DISTANCE_TURNS = 10;
    public static double WEIGHT_STALKING = 1;
    public static double WEIGHT_NOT_STALKING = 1.5;
    public static double STALKING_CONFIDENCE_THRESHOLD = 0.7;

    public double STALKING_DISTANCE_THRESHOLD = 15;

    public Utilities utilities_;

    // Forever
    public boolean hasOpponentSpentEver = false;
    public ItemType item;
    public UpgradeType upgrade;

    // This round
    public boolean isStalking = false;
    public boolean hasOpponentSpent = false;
    public int opponentSpending;
    public int currentDistance;

    public double stalkingConfidence;
    public double averageDistance;

    // Just for calculations
    public ArrayList<Integer> pastDistances;
    public int pastDistanceTurns = 0;

    public int previousAmountSpent = 0;

    public OpponentTracker() {
        pastDistances =  new ArrayList<>();
    }

    public void update(Game game, Utilities utilities) {
        utilities_ = utilities;

        GameState gameState = game.getGameState();
        Player opponent = gameState.getOpponentPlayer();

        item = Utilities.getItem(opponent.getItem());
        upgrade = Utilities.getUpgrade(opponent.getUpgrade());

        calculateDistance(opponent);
        calculateAmountSpent(opponent);
    }

    private void calculateAmountSpent(Player opponent) {
        int amountSpent = opponent.getAmountSpent();

        hasOpponentSpent = amountSpent > previousAmountSpent;
        hasOpponentSpentEver = hasOpponentSpentEver || hasOpponentSpent;

        opponentSpending = amountSpent - previousAmountSpent;
        previousAmountSpent = amountSpent;
    }

    private void calculateDistance(Player opponent) {
        currentDistance = utilities_.getDistance(utilities_.getRelativePosition(opponent.getPosition()));

        pastDistances.add(currentDistance);
        if (pastDistanceTurns >= PAST_DISTANCE_TURNS) {
            pastDistances.remove(0);
        } else {
            ++pastDistanceTurns;
        }

        double totalPastDistance = pastDistances.stream().reduce(0, Integer::sum);
        averageDistance = totalPastDistance  / pastDistanceTurns;

        stalkingConfidence = pastDistances.stream()
                .mapToDouble(distance ->
                        (1 / PAST_DISTANCE_TURNS) *
                                (distance <= STALKING_DISTANCE_THRESHOLD ? WEIGHT_STALKING : WEIGHT_NOT_STALKING))
                .reduce(0.0, Double::sum);

        isStalking = stalkingConfidence >= STALKING_CONFIDENCE_THRESHOLD;
    }
}
