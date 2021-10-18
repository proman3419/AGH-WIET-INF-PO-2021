package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {
    public static Direction convertStringToDirection(String string) {
        switch (string) {
            case "f" -> { return Direction.FORWARD; }
            case "b" -> { return Direction.BACKWARD; }
            case "r" -> { return Direction.RIGHT; }
            case "l" -> { return Direction.LEFT; }
            default -> { return Direction.NONE; }
        }
    }

    public static void run(Direction direction) {
        switch (direction) {
            case FORWARD -> System.out.println("Zwierzak idzie do przodu");
            case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
            case RIGHT -> System.out.println("Zwierzak skręca w prawo");
            case LEFT -> System.out.println("Zwierzak skręca w lewo");
        }
    }

    public static void run(String[] strings) {
        List<String> stringsList = Arrays.asList(strings);

        stringsList
                .stream()
                .map(World::convertStringToDirection)
                .forEach(World::run);
    }

    public static void main(String[] args) {
        System.out.println("Start");

        run(args);

        System.out.println("Stop");
    }
}
