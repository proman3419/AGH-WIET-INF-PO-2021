package agh.ics.oop;

public class AbstractWorldMapElement implements IMapElement {
    protected Vector2d position = new Vector2d(0,0); // Default position, the only one that is guaranteed to be present on any map

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public String getRepresentationImagePath() {
        return "src/main/resources/dirt.png";
    }

    @Override
    public String toLabelString() {
        return "";
    }
}
