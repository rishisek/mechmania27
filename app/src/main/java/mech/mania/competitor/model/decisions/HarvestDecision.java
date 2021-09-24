package mech.mania.competitor.model.decisions;

import mech.mania.competitor.model.Position;

import java.util.ArrayList;

public class HarvestDecision extends ActionDecision {
    protected ArrayList<Position> coords;

    public HarvestDecision(ArrayList<Position> coords) {
        this.coords = coords;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Position coord : coords) {
            builder.append(String.format("%s ", coord.getEngineReadableString()));
        }

        return String.format("%s", builder);
    }
}
