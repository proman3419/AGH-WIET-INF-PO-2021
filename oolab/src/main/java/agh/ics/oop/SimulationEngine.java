package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine {
    private final List<MoveDirection> directions;
    private final IWorldMap map;
    private final List<Animal> animals;
    private final SimulationVisualizer simulationVisualizer;
    private final int frameDelaySeconds = 1;

    public List<Vector2d> getAnimalsPositions() {
        List<Vector2d> animalsPositions = new ArrayList<>();

        for (Animal animal : this.animals)
            animalsPositions.add(animal.getPosition());

        return animalsPositions;
    }

    public SimulationEngine(List<MoveDirection> directions, IWorldMap map, List<Vector2d> positions) {
        this.directions = directions;
        this.map = map;
        this.animals = new ArrayList<>();
        this.simulationVisualizer = new SimulationVisualizer((RectangularMap) this.map);

        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
            this.map.place(animal);
            this.animals.add(animal);
        }
    }

    @Override
    public void run() throws InterruptedException {
        System.out.println(this.map);
        this.simulationVisualizer.update();

        int animalsCount = this.animals.size();
        for (int i = 0; i < this.directions.size(); i++) {
            this.animals.get(i % animalsCount).move(this.directions.get(i));
            System.out.println(this.map);
            this.simulationVisualizer.update();
            TimeUnit.SECONDS.sleep(this.frameDelaySeconds);
        }
    }
}
