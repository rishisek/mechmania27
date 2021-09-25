package mech.mania.competitor.mm_strategies;

import mech.mania.competitor.api.GameUtil;
import mech.mania.competitor.mm_core.Manager;
import mech.mania.competitor.mm_models.DecisionPair;
import mech.mania.competitor.model.CropType;
import mech.mania.competitor.model.GameState;
import mech.mania.competitor.model.Position;
import mech.mania.competitor.model.Tile;
import mech.mania.competitor.model.decisions.DoNothingDecision;
import mech.mania.competitor.model.decisions.HarvestDecision;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class HarvestCrop {
    public Position position_;
    public Tile tile_;

    public int turnsToReach_;
    public int oppTurnsToReach_;
    public int growTurns_;

    public HarvestCrop(Position position, Tile tile, int turnsToReach, int oppTurnsToReach) {
        position_ = position;
        tile_ = tile;
        turnsToReach_ = turnsToReach;
        oppTurnsToReach_ = oppTurnsToReach;
        growTurns_ = tile_.getCrop().getGrowthTimer();
    }

    int getWorth() {
        boolean canSteal = (turnsToReach_ < oppTurnsToReach_) && (growTurns_ < oppTurnsToReach_);

        return 100 * (canSteal ? 1 : 0)
                + 10 * (9 - Math.min(9, Math.max(0, growTurns_ - turnsToReach_)))
                + 9 - Math.min(9, turnsToReach_);
    }
}

class HarvestTarget {
    public Position position_;
    public List<HarvestCrop> crops_;

    HarvestTarget(Position position, List<HarvestCrop> crops) {
        position_ = position;
        crops_ = crops;
    }

    int getNetWorth() {
        return crops_.stream().map(HarvestCrop::getWorth).reduce(0, Integer::sum);
    }

    double getNetCropValue() {
        return crops_.stream().map((HarvestCrop crop) -> crop.tile_.getCrop().getValue())
                .reduce(0.0, Double::sum);
    }
}

public class Strategy_Harvester extends Strategy {

    @Override
    public ArrayList<DecisionPair> getDecisions(Manager manager) {
        GameState gameState = manager.game_.getGameState();

        ArrayList<ArrayList<Tile>> tiles = gameState.getTileMap().getTiles();

        ArrayList<HarvestTarget> harvestTargets = new ArrayList<>();

        for (int i = 0; i < tiles.size(); i++) {
            ArrayList<Tile> row = tiles.get(i);
            for (int j = 0; j < row.size(); j++) {
                Position tilePosition = new Position(i, j);

                int turnsToReach = manager.utilities.turnToReach(tilePosition);

                List<HarvestCrop> crops = getPositionsInHarvestRange(tilePosition, gameState.getMyPlayer().getHarvestRadius()).stream()
                        .filter((Position position) -> {
                            Tile tile = tiles.get(position.getX()).get(position.getY());

                            return tile.getCrop().getType() != CropType.NONE && !tile.hasScarecrowEffect();
                        }).map((Position position) -> new HarvestCrop(
                                position,
                                tiles.get(position.getX()).get(position.getY()),
                                turnsToReach,
                                manager.utilities.turnToReach(manager.game_.getGameState().getOpponentPlayer(), position)
                        )).collect(Collectors.toList());

                harvestTargets.add(new HarvestTarget(tilePosition, crops));
            }
        }

        Optional<HarvestTarget> maybeBestTarget = harvestTargets.stream()
                .filter((HarvestTarget harvestTarget) -> harvestTarget.crops_.size() > 0)
                .max(Comparator.comparing(HarvestTarget::getNetWorth)
                        .thenComparing(HarvestTarget::getNetCropValue));

        if (!maybeBestTarget.isPresent()) {
            return manager.utilities.moveToPosition(manager.utilities.getNearestGrocer(), new DoNothingDecision());
        }

        HarvestTarget bestTarget = maybeBestTarget.get();

        ArrayList<Position> harvestPositions = bestTarget.crops_.stream()
                .map((HarvestCrop crop) -> crop.position_).collect(Collectors.toCollection(ArrayList::new));

        return manager.utilities.moveToPosition(bestTarget.position_, new HarvestDecision(harvestPositions));
    }

    private ArrayList<Position> getPositionsInHarvestRange(Position position, int radius) {
        ArrayList<Position> positions = new ArrayList<>();

        for (int i = position.getY() - radius; i < position.getY() + radius; i++) {
            for (int j = position.getX() - radius; j < position.getX() + radius; j++) {
                Position newPos = new Position(j, i);
                if (GameUtil.distance(newPos, position) <= radius && GameUtil.validPosition(newPos)) {
                    positions.add(newPos);
                }
            }
        }

        return positions;
    }
}
