package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationEngineTest {
    void listsEqualHelper(List<?> l1, List<?> l2) {
        if (l1 == l2) return;
        assertEquals(l1.size(), l2.size());

        for (int i = 0; i < l1.size(); i++) {
            assertEquals(l1.get(i).getClass(), l2.get(i).getClass());
            assertEquals(l1.get(i), l2.get(i));
        }
    }

    @Test
    void runTest() {
        List<MoveDirection> directions = new OptionsParser().parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r",
                "f", "f", "f", "f", "f", "f", "f", "f"});
        AbstractWorldMap map = new RectangularMap(10, 5);
        List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4)));
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        List<Vector2d> expectedPositions = new ArrayList<>(Arrays.asList(new Vector2d(2, 0), new Vector2d(3, 4)));

        listsEqualHelper(expectedPositions, engine.getAnimalsPositions());
    }

    @Test
    void runTest2() {
        List<MoveDirection> directions = new OptionsParser().parse(new String[]{"r", "f", "l", "l", "l", "f", "f", "r",
                "b", "l", "f", "f", "f", "r", "l", "b"});
        AbstractWorldMap map = new RectangularMap(10, 17);
        List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(1, 3), new Vector2d(3, 7)));
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        List<Vector2d> expectedPositions = new ArrayList<>(Arrays.asList(new Vector2d(0, 3), new Vector2d(1, 7)));

        listsEqualHelper(expectedPositions, engine.getAnimalsPositions());
    }

    @Test
    void runTest3() {
        List<MoveDirection> directions = new OptionsParser().parse(new String[]{"b", "f", "r", "f", "f", "f", "f", "r",
                "b", "r", "l", "b", "f", "r", "l", "f", "f", "f", "r", "f", "b", "f", "l", "r", "f", "f", "f", "f", "f",
                "f", "l", "l", "r", "r", "b", "b", "f", "l", "l", "r", "r", "b", "b", "f", "f", "f", "f", "r", "f", "l",
                "r", "b", "b", "f", "r", "f", "f", "r", "l", "f", "f", "r", "b", "l", "r", "r", "b", "l", "f", "r", "r",
                "f", "l", "r", "r", "l", "b", "b", "f", "f", "l", "r", "r", "b", "b", "f", "b", "f", "b", "r", "f", "l",});
        AbstractWorldMap map = new RectangularMap(10, 15);
        List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(4, 2), new Vector2d(0, 7),
                new Vector2d(3, 12), new Vector2d(6, 3), new Vector2d(9, 14)));
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        List<Vector2d> expectedPositions = new ArrayList<>(Arrays.asList(new Vector2d(2, 7), new Vector2d(0, 10),
                new Vector2d(3, 10), new Vector2d(4, 4), new Vector2d(9, 13)));

        listsEqualHelper(expectedPositions, engine.getAnimalsPositions());
    }
}
