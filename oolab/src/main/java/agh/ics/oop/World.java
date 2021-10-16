package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {
    // do 14. punktu
    public static void run1(String[] directions) {
        System.out.println("Zwierzak idzie do przodu");
        for (int i = 0; i < directions.length; i++) {
            if (i < directions.length - 1)
                System.out.printf("%s, ", directions[i]);
            else
                System.out.println(directions[i]);
        }
    }

    // do 16. punktu
    public static void run2(String[] directions) {
        for (String direction : directions) {
            switch (direction) {
                case "f" -> System.out.println("Zwierzak idzie do przodu");
                case "b" -> System.out.println("Zwierzak idzie do tyłu");
                case "r" -> System.out.println("Zwierzak skręca w prawo");
                case "l" -> System.out.println("Zwierzak skręca w lewo");
            }
        }
    }

    // ==============================================================================================================

    public static Direction convertStringToDirection(String string) {
        switch (string) {
            case "f" -> { return Direction.FORWARD; }
            case "b" -> { return Direction.BACKWARD; }
            case "r" -> { return Direction.RIGHT; }
            case "l" -> { return Direction.LEFT; }
            default -> { return Direction.NONE; }
        }
    }

    public static ArrayList<Direction> convertStringToDirection(String[] strings) {
        ArrayList<Direction> directions = new ArrayList<>();

        for (String string : strings) {
            Direction direction = convertStringToDirection(string);
            if (direction != Direction.NONE)
                directions.add(direction);
        }

        return directions;
    }

    public static void run(Direction direction) {
        switch (direction) {
            case FORWARD -> System.out.println("Zwierzak idzie do przodu");
            case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
            case RIGHT -> System.out.println("Zwierzak skręca w prawo");
            case LEFT -> System.out.println("Zwierzak skręca w lewo");
        }
    }

    public static void run(ArrayList<Direction> directions) {
        for (Direction direction : directions) {
            run(direction);
        }
    }

    // rozwiązanie w oparciu o stream
    public static void streamSolution(String[] strings) {
        List<String> stringsList = Arrays.asList(strings);

        stringsList
                .stream()
                .map(World::convertStringToDirection)
                .forEach(World::run);
    }

    public static void main(String[] args) {
        System.out.println("Start");

//        run1(args);

//        run2(args);

//        ArrayList<Direction> directions = convertStringToDirection(args);
//        run(directions);

        streamSolution(args);

        System.out.println("Stop");
    }
}
