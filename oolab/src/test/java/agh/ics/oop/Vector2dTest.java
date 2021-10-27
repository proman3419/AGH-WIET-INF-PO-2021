package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    void equalsTest() {
        assertEquals(new Vector2d(0, 0), new Vector2d(0, 0));
        assertNotEquals(new Vector2d(0, 0), new Vector2d(1, 0));
    }

    @Test
    void toStringTest() {
        assertEquals("(3,5)", new Vector2d(3, 5).toString());
        assertNotEquals("(5,3)", new Vector2d(3, 5).toString());
    }

    @Test
    void precedesTest() {
        Vector2d v1 = new Vector2d(2, 1);
        Vector2d v2 = new Vector2d(3, 3);
        Vector2d v3 = new Vector2d(3, 4);

        assertTrue(v1.precedes(v2));
        assertTrue(v1.precedes(v3));
        assertTrue(v2.precedes(v3));
        assertTrue(v3.precedes(v3));
    }

    @Test
    void followsTest() {
        Vector2d v1 = new Vector2d(2, 1);
        Vector2d v2 = new Vector2d(3, 3);
        Vector2d v3 = new Vector2d(3, 4);

        assertTrue(v3.follows(v2));
        assertTrue(v3.follows(v1));
        assertTrue(v2.follows(v1));
        assertTrue(v1.follows(v1));
    }

    @Test
    void lowerLeftTest() {
        assertEquals(new Vector2d(1, 1), new Vector2d(2, 1).lowerLeft(new Vector2d(1, 2)));
    }

    @Test
    void upperRightTest() {
        assertEquals(new Vector2d(2, 2), new Vector2d(2, 1).upperRight(new Vector2d(1, 2)));
    }

    @Test
    void addTest() {
        assertEquals(new Vector2d(4, 7), new Vector2d(3, 5).add(new Vector2d(1, 2)));
    }

    @Test
    void subtractTest() {
        assertEquals(new Vector2d(3, 5), new Vector2d(4, 7).subtract(new Vector2d(1, 2)));
    }

    @Test
    void oppositeTest() {
        assertEquals(new Vector2d(-1, -2), new Vector2d(1, 2).opposite());
    }
}
