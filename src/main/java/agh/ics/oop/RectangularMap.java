package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap {
    public RectangularMap(int width, int height) {
        width = width > 0 ? width : 5;
        height = height > 0 ? height : 5;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width-1, height-1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (this.lowerLeft.precedes(position))
            if (this.upperRight.follows(position))
                return super.canMoveTo(position);

        return false;
    }
}
