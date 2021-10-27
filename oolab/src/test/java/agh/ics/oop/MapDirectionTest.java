package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static agh.ics.oop.MapDirection.*;

public class MapDirectionTest {
    @Test
    void nextTest() {
        assertEquals(EAST, NORTH.next());
        assertEquals(WEST, SOUTH.next());
        assertEquals(NORTH, WEST.next());
        assertEquals(SOUTH, EAST.next());
    }

    @Test
    void previousTest() {
        assertEquals(WEST, NORTH.previous());
        assertEquals(EAST, SOUTH.previous());
        assertEquals(SOUTH, WEST.previous());
        assertEquals(NORTH, EAST.previous());
    }
}
