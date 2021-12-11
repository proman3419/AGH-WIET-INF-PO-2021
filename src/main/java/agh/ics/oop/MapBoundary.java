package agh.ics.oop;

import java.util.SortedSet;
import java.util.TreeSet;

import static agh.ics.oop.Comparators.comparatorX;
import static agh.ics.oop.Comparators.comparatorY;

public class MapBoundary implements IPositionChangeObserver {
    private final SortedSet<AbstractWorldMapElement> mapElementsX = new TreeSet<>(comparatorX);
    private final SortedSet<AbstractWorldMapElement> mapElementsY = new TreeSet<>(comparatorY);
    private final AbstractWorldMap map;

    public MapBoundary(AbstractWorldMap map) {
        this.map = map;
    }

    protected int getMapElementsXSize() {
        return this.mapElementsX.size();
    }

    protected int getMapElementsYSize() {
        return this.mapElementsY.size();
    }

    public void addElement(AbstractWorldMapElement element) {
        this.mapElementsX.add(element);
        this.mapElementsY.add(element);
    }

    public void removeElement(AbstractWorldMapElement element) {
        this.mapElementsX.remove(element);
        this.mapElementsY.remove(element);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement objectAtOldPosition = this.map.objectAt(oldPosition);
        removeElement(objectAtOldPosition);

        AbstractWorldMapElement objectAtNewPosition = this.map.objectAt(newPosition);
        addElement(objectAtNewPosition);
    }

    public Vector2d getLowerLeft() {
        if (this.mapElementsX.size() == 0)
            return new Vector2d(0, 0);

        return this.mapElementsX.first().getPosition().lowerLeft(this.mapElementsY.first().getPosition());
    }

    public Vector2d getUpperRight() {
        if (this.mapElementsX.size() == 0)
            return new Vector2d(0, 0);

        return this.mapElementsX.last().getPosition().upperRight(this.mapElementsY.last().getPosition());
    }
}
