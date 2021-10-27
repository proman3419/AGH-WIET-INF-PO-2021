package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        Animal animal = new Animal();

        OptionsParser optionsParser = new OptionsParser();
        animal.move(optionsParser.parse(args));
        System.out.println(animal);
    }
}
