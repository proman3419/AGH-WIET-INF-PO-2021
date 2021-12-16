package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable {
    private final List<MoveDirection> directions;
    private final AbstractWorldMap map;
    private final List<Animal> animals;
    private final List<IAnimalMoveObserver> animalMoveObservers = new LinkedList<>();
    private final int moveDelay = 300;

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

    private void update() {
        for (IAnimalMoveObserver animalMoveObserver : this.animalMoveObservers)
            animalMoveObserver.animalMove();

        System.out.println(this.map);

        try {
            Thread.sleep(this.moveDelay);
        } catch (InterruptedException e) {
            System.out.println("The simulation has stopped");
        }
    }

    @Override
    public void run() {
        System.out.println(this.map);
        update();

        int animalsCount = this.animals.size();
        for (int i = 0; i < this.directions.size(); i++) {
            this.animals.get(i % animalsCount).move(this.directions.get(i));
            update();
        }
    }

    public void addAnimalMoveObserver(IAnimalMoveObserver animalMoveObserver) {
        this.animalMoveObservers.add(animalMoveObserver);
    }
}
