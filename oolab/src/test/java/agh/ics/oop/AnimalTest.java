package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    @Test
    void moveTest() {
        OptionsParser optionsParser = new OptionsParser();

        assertEquals(new Vector2d(2, 2), new Animal().getPosition());
        assertEquals(new Vector2d(2, 3), new Animal().move(optionsParser.parse(new String[]{"f"})).getPosition());
        assertEquals(new Vector2d(2, 1), new Animal().move(optionsParser.parse(new String[]{"b"})).getPosition());

        assertEquals(MapDirection.NORTH, new Animal().getOrientation());
        assertEquals(MapDirection.EAST, new Animal().move(optionsParser.parse(new String[]{"r"})).getOrientation());
        assertEquals(MapDirection.SOUTH, new Animal().move(optionsParser.parse(new String[]{"r", "r"})).getOrientation());
        assertEquals(MapDirection.WEST, new Animal().move(optionsParser.parse(new String[]{"l"})).getOrientation());
        assertEquals(MapDirection.SOUTH, new Animal().move(optionsParser.parse(new String[]{"l", "l"})).getOrientation());

        assertEquals(new Vector2d(2, 4), new Animal().move(optionsParser.parse(new String[]{"f", "f", "f"})).getPosition());
        assertEquals(new Vector2d(4, 2), new Animal().move(optionsParser.parse(new String[]{"r", "f", "f", "f"})).getPosition());
        assertEquals(new Vector2d(2, 0), new Animal().move(optionsParser.parse(new String[]{"b", "b", "b"})).getPosition());
        assertEquals(new Vector2d(0, 2), new Animal().move(optionsParser.parse(new String[]{"r", "b", "b", "b"})).getPosition());
    }
}
