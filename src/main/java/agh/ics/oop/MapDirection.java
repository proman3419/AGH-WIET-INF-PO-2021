package agh.ics.oop;

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public String toString() {
        return switch (this) {
            case NORTH -> "North";
            case EAST -> "East";
            case SOUTH -> "South";
            case WEST -> "West";
        };
    }

    public MapDirection next() {
        MapDirection[] values = MapDirection.values();
        return values[(this.ordinal() + 1) % values.length];
    }

    public MapDirection previous() {
        MapDirection[] values = MapDirection.values();
        return values[(this.ordinal() - 1 + values.length) % values.length];
    }

    public Vector2d toUnitVector() {
        return switch (this) {
            case NORTH -> new Vector2d(0, 1);
            case SOUTH -> new Vector2d(0, -1);
            case WEST -> new Vector2d(-1, 0);
            case EAST -> new Vector2d(1, 0);
        };
    }
}
