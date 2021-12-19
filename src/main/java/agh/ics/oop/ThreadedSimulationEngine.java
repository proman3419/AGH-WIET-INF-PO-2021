package agh.ics.oop;

import java.util.List;

public class ThreadedSimulationEngine extends SimulationEngine {
    public ThreadedSimulationEngine(AbstractWorldMap map, List<Vector2d> positions) {
        super(map, positions);
    }

    public void setDirections(List<MoveDirection> directions) {
        this.directions = directions;
    }
}
