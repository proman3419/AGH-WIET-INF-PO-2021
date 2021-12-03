package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            List<MoveDirection> directions = new OptionsParser().parse(args);
            AbstractWorldMap map = new GrassField(10);
            List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(2, 10),
                    new Vector2d(-2, -5)));
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
