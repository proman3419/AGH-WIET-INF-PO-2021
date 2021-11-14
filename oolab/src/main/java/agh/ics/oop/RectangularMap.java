package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {
    public final int width;
    public final int height;
    private final List<Animal> animals = new ArrayList<>();
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    public RectangularMap(int width, int height) {
        this.width = width > 0 ? width : 5;
        this.height = height > 0 ? height : 5;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (new Vector2d(0, 0).precedes(position))
            if (new Vector2d(this.width-1, this.height-1).follows(position))
                return !isOccupied(position);

        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())) {
            this.animals.add(animal);
            return true;
        }

        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : this.animals)
            if (animal.getPosition().equals(position))
                return true;

        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : this.animals)
            if (animal.getPosition().equals(position))
                return animal;

        return null;
    }

    @Override
    public String toString() {
        return this.mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(this.width-1, this.height-1));
    }
}
