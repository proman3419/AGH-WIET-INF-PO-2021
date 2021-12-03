package agh.ics.oop;

import java.util.LinkedHashMap;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected final LinkedHashMap<Vector2d, AbstractWorldMapElement> mapElements = new LinkedHashMap<>();
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
            this.mapElements.put(animal.getPosition(), animal);
            return true;
        }

        throw new IllegalArgumentException(animal.getPosition() + " is occupied by another animal therefore the new animal can't be placed there");
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.mapElements.get(position) != null;
    }

    @Override
    public AbstractWorldMapElement objectAt(Vector2d position) {
        return this.mapElements.get(position);
    }

    @Override
    public String toString() {
        return this.mapVisualizer.draw(this.lowerLeft, this.upperRight);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement mapElement = this.mapElements.remove(oldPosition);
        this.mapElements.put(newPosition, mapElement);
    }
}
