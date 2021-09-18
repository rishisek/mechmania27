package mech.mania.engine.model.decisions;

import mech.mania.engine.model.Position;

import java.util.ArrayList;

public class PlantDecision extends ActionDecision {
    protected ArrayList<String> cropTypes;
    protected ArrayList<Position> coords;

    public PlantDecision(ArrayList<String> cropTypes, ArrayList<Position> coords) {
        this.cropTypes = cropTypes;
        this.coords = coords;
        assert(cropTypes.size() == coords.size());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("plant ");
        for (int i = 0; i < cropTypes.size(); i++) {
            builder.append(cropTypes.get(i));
            builder.append(" ");
            builder.append(coords.get(i).toString());
        }

        return builder.toString();
    }
}
