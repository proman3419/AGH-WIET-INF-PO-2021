package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {
    private final List<MoveDirection> directions;
    private final AbstractWorldMap map;
    private final List<Animal> animals;

    public SimulationEngine(List<MoveDirection> directions, AbstractWorldMap map, List<Vector2d> positions) {
        this.directions = directions;
        this.map = map;
        this.animals = new ArrayList<>();

        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
            this.map.place(animal);
            this.animals.add(animal);
        }
    }

    public List<Vector2d> getAnimalsPositions() {
        List<Vector2d> animalsPositions = new ArrayList<>();

        for (Animal animal : this.animals)
            animalsPositions.add(animal.getPosition());

        return animalsPositions;
    }

    @Override
    public void run() {
        System.out.println(this.map);

        int animalsCount = this.animals.size();
        for (int i = 0; i < this.directions.size(); i++) {
            this.animals.get(i % animalsCount).move(this.directions.get(i));
            System.out.println(this.map);
        }
    }
}
