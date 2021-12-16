package agh.ics.oop;

public class Grass extends AbstractWorldMapElement {
    public Grass(Vector2d position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public String getRepresentationImagePath() {
        return "src/main/resources/grass.png";
    }

    @Override
    public String toLabelString() {
        return "Trawa";
    }
}
