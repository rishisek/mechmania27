package mech.mania.engine.model.decisions;

import mech.mania.engine.model.Position;

public class MoveDecision {

    Position position;

    public MoveDecision(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("move %s", position);
    }
}
