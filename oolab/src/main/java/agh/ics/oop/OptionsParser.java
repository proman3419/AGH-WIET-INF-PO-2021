package agh.ics.oop;

import java.util.ArrayList;

public class OptionsParser {
    public ArrayList<MoveDirection> parse(String[] strings) {
        ArrayList<MoveDirection> directionsList = new ArrayList<>();

        for (String string : strings) {
            switch (string) {
                case "f", "forward" -> directionsList.add(MoveDirection.FORWARD);
                case "b", "backward" -> directionsList.add(MoveDirection.BACKWARD);
                case "r", "right" -> directionsList.add(MoveDirection.RIGHT);
                case "l", "left" -> directionsList.add(MoveDirection.LEFT);
                default -> {}
            }
        }

        return directionsList;
    }
}
