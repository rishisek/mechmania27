package mech.mania.engine.model.decisions;

import mech.mania.engine.model.Position;

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
            builder.append(String.format("%s ", coord.toString()));
        }

        return String.format("%s", builder);
    }
}
