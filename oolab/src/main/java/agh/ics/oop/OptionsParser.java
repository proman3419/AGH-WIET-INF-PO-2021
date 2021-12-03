package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public List<MoveDirection> parse(String[] strings) {
        List<MoveDirection> directionsList = new ArrayList<>();

        for (String string : strings) {
            switch (string) {
                case "f", "forward" -> directionsList.add(MoveDirection.FORWARD);
                case "b", "backward" -> directionsList.add(MoveDirection.BACKWARD);
                case "r", "right" -> directionsList.add(MoveDirection.RIGHT);
                case "l", "left" -> directionsList.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(string + " is not legal move specification");
            }
        }

        return directionsList;
    }
}
