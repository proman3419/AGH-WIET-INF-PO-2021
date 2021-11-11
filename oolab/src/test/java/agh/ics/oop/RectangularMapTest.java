package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    final int width = 5;
    final int height = 5;

    @Test
    void canMoveToTest() {
        RectangularMap map = new RectangularMap(this.width, this.height);

        assertTrue(map.canMoveTo(new Vector2d(this.width-1, this.height-1)));
        assertFalse(map.canMoveTo(new Vector2d(-1, 0)));
        assertFalse(map.canMoveTo(new Vector2d(0, -1)));
        assertFalse(map.canMoveTo(new Vector2d(this.width, 0)));
        assertFalse(map.canMoveTo(new Vector2d(0, this.height)));

        Animal animal = new Animal(map);
        map.place(animal);

        assertFalse(map.canMoveTo(animal.getPosition()));
    }

    @Test
    void placeTest() {
        RectangularMap map = new RectangularMap(this.width, this.height);

        assertTrue(map.place(new Animal(map)));
        assertFalse(map.place(new Animal(map)));
        assertTrue(map.place(new Animal(map, new Vector2d(this.width-1, this.height-1))));
    }

    @Test
    void isOccupiedTest() {
        RectangularMap map = new RectangularMap(this.width, this.height);
        Animal animal = new Animal(map);

        assertFalse(map.isOccupied(new Vector2d(this.width-1, this.height-1)));
        map.place(animal);
        assertTrue(map.isOccupied(animal.getPosition()));
    }

    @Test
    void objectAtTest() {
        RectangularMap map = new RectangularMap(this.width, this.height);
        Animal animal = new Animal(map);
        map.place(animal);

        assertEquals(animal, map.objectAt(animal.getPosition()));
        assertNull(map.objectAt(new Vector2d(this.width, this.height)));
    }
}
