package agh.ics.oop;

public class GrassField extends AbstractWorldMap {
    private final int maxCoord;
    private final MapBoundary mapBoundary;

    public GrassField(int grassFieldsCount) {
        this.maxCoord = (int) Math.ceil(Math.sqrt(grassFieldsCount * 10));
        this.mapBoundary = new MapBoundary(this);

        for (int i = 0; i < grassFieldsCount; i++)
            spawnGrass();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
       if (super.canMoveTo(position)) {
           if (objectAt(position) instanceof Grass) {
               this.mapBoundary.removeElement(objectAt(position));
               this.mapElements.remove(position);
           }
           return true;
       }

       return false;
    }

    @Override
    public boolean place(Animal animal) {
        super.place(animal);
        this.mapBoundary.addElement(animal);

        return true;
    }

    @Override
    public String toString() {
        this.upperRight = this.mapBoundary.getUpperRight();
        this.lowerLeft = this.mapBoundary.getLowerLeft();
        System.out.println(this.upperRight);
        System.out.println(this.lowerLeft);

        return super.toString();
    }

    public void spawnGrass() {
        Vector2d position;
        do {
            int x = (int) (Math.random() * this.maxCoord);
            int y = (int) (Math.random() * this.maxCoord);
            position = new Vector2d(x, y);
        } while (objectAt(position) != null);

        Grass newGrass = new Grass(position);
        this.mapElements.put(position, newGrass);
        this.mapBoundary.addElement(newGrass);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement mapElement = this.mapElements.get(oldPosition);
        this.mapElements.put(newPosition, mapElement);
        this.mapBoundary.positionChanged(oldPosition, newPosition);
        this.mapElements.remove(oldPosition);
    }
}