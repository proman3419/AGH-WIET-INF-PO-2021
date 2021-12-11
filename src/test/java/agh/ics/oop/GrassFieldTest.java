package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    @Test
    void canMoveTo() {
        GrassField map = new GrassField(10);

        assertTrue(map.canMoveTo(new Vector2d(-1, 0)));
        assertTrue(map.canMoveTo(new Vector2d(0, -1)));
        assertTrue(map.canMoveTo(new Vector2d(1000, 0)));
        assertTrue(map.canMoveTo(new Vector2d(0, 1000)));

        Animal animal = new Animal(map);
        map.place(animal);

        assertFalse(map.canMoveTo(animal.getPosition()));
    }

    @Test
    void place() {
        GrassField map = new GrassField(10);

        assertTrue(map.place(new Animal(map)));
        assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map)));
        assertTrue(map.place(new Animal(map, new Vector2d(1000, 1000))));
    }

    @Test
    void isOccupied() {
        GrassField map = new GrassField(10);

        Animal animal = new Animal(map);

        assertFalse(map.isOccupied(new Vector2d(1000, 1000)));
        map.place(animal);
        assertTrue(map.isOccupied(animal.getPosition()));
    }

    @Test
    void objectAt() {
        GrassField map = new GrassField(10);
        Animal animal = new Animal(map);
        map.place(animal);

        assertEquals(animal, map.objectAt(animal.getPosition()));
        assertNull(map.objectAt(new Vector2d(1000, 1000)));
    }
}
