package agh.ics.oop;

import java.util.ArrayList;

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
                case "f":
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case "b":
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case "r":
                    System.out.println("Zwierzak skręca w prawo");
                    break;
                case "l":
                    System.out.println("Zwierzak skręca w lewo");
                    break;
            }
        }
    }

    public static void run(ArrayList<Direction> directions) {
        for (Direction direction : directions) {
            switch (direction) {
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak skręca w prawo");
                    break;
                case LEFT:
                    System.out.println("Zwierzak skręca w lewo");
                    break;
                case NONE:
                default:
                    break;
            }
        }
    }

    public static Direction convertStringToDirection(String string) {
        switch (string) {
            case "f":
                return Direction.FORWARD;
            case "b":
                return Direction.BACKWARD;
            case "r":
                return Direction.RIGHT;
            case "l":
                return Direction.LEFT;
            default:
                return Direction.NONE;
        }
    }

    public static ArrayList<Direction> convertStringToDirection(String[] strings) {
        ArrayList<Direction> directions = new ArrayList<>();

        for (String string : strings)
            directions.add(convertStringToDirection(string));

        return directions;
    }
    
    public static void main(String[] args) {
        System.out.println("Start");

//        System.out.println("Do 14. punktu");
//        run1(args);
//        System.out.println();

//        System.out.println("Do 16. punktu");
//        run2(args);
//        System.out.println();

        ArrayList<Direction> directions = convertStringToDirection(args);
        run(directions);

        System.out.println("Stop");
    }
}
