package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap {
    protected final List<AbstractWorldMapElement> mapElements = new ArrayList<>();
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected Vector2d upperRight = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.mapElements.add(animal);
            return true;
        }

        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (AbstractWorldMapElement mapElement : this.mapElements)
            if (mapElement.getPosition().equals(position))
                return true;

        return false;
    }

    @Override
    public AbstractWorldMapElement objectAt(Vector2d position) {
        for (AbstractWorldMapElement mapElement : this.mapElements)
            if (mapElement.getPosition().equals(position))
                return mapElement;

        return null;
    }

    @Override
    public String toString() {
        return this.mapVisualizer.draw(this.lowerLeft, this.upperRight);
    }
}
