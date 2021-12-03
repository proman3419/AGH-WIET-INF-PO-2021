package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapBoundaryTest {

    @Test
    void addElement() {
        AbstractWorldMap map = new RectangularMap(5, 10);
        MapBoundary mapBoundary = new MapBoundary(map);

        mapBoundary.addElement(new Animal(map, new Vector2d(3, 7)));
        assertEquals(1, mapBoundary.getMapElementsXSize());
        assertEquals(1, mapBoundary.getMapElementsYSize());

        mapBoundary.addElement(new Animal(map, new Vector2d(5, 2)));
        assertEquals(2, mapBoundary.getMapElementsXSize());
        assertEquals(2, mapBoundary.getMapElementsYSize());
    }

    @Test
    void removeElement() {
        AbstractWorldMap map = new RectangularMap(5, 10);
        MapBoundary mapBoundary = new MapBoundary(map);

        AbstractWorldMapElement element1 = new Animal(map, new Vector2d(3, 7));
        mapBoundary.addElement(element1);
        AbstractWorldMapElement element2 = new Animal(map, new Vector2d(5, 2));
        mapBoundary.addElement(element2);

        mapBoundary.removeElement(element1);
        assertEquals(1, mapBoundary.getMapElementsXSize());
        assertEquals(1, mapBoundary.getMapElementsYSize());

        mapBoundary.removeElement(element2);
        assertEquals(0, mapBoundary.getMapElementsXSize());
        assertEquals(0, mapBoundary.getMapElementsYSize());
    }

    @Test
    void getLowerLeft() {
        AbstractWorldMap map = new RectangularMap(5, 10);
        MapBoundary mapBoundary = new MapBoundary(map);

        mapBoundary.addElement(new Animal(map, new Vector2d(3, 7)));
        mapBoundary.addElement(new Animal(map, new Vector2d(5, 2)));

        assertEquals(new Vector2d(3, 2), mapBoundary.getLowerLeft());
    }

    @Test
    void getUpperRight() {
        AbstractWorldMap map = new RectangularMap(5, 10);
        MapBoundary mapBoundary = new MapBoundary(map);

        mapBoundary.addElement(new Animal(map, new Vector2d(3, 7)));
        mapBoundary.addElement(new Animal(map, new Vector2d(5, 2)));

        assertEquals(new Vector2d(5, 7), mapBoundary.getUpperRight());
    }
}