package agh.ics.oop;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GrassField extends AbstractWorldMap {
    public GrassField(int grassFieldsCount) {
        int maxCoord = (int) Math.ceil(Math.sqrt(grassFieldsCount*10));

        List<Integer> xs = IntStream.rangeClosed(0, maxCoord).boxed().collect(Collectors.toList());
        List<Integer> ys = IntStream.rangeClosed(0, maxCoord).boxed().collect(Collectors.toList());
        Collections.shuffle(xs);
        Collections.shuffle(ys);

        for (int i = 0; i < grassFieldsCount; i++)
            this.mapElements.add(new Grass(new Vector2d(xs.get(i), ys.get(i))));
    }

    @Override
    public AbstractWorldMapElement objectAt(Vector2d position) {
        AbstractWorldMapElement mapElementAt = null;
        for (AbstractWorldMapElement mapElement : this.mapElements) {
            if (mapElement.getPosition().equals(position)) {
                if (mapElement instanceof Animal)
                    return mapElement;
                else
                    mapElementAt = mapElement;
            }
        }

        return mapElementAt;
    }

    @Override
    public String toString() {
        for (AbstractWorldMapElement mapElement : this.mapElements) {
            this.upperRight = this.upperRight.upperRight(mapElement.getPosition());
            this.lowerLeft = this.lowerLeft.lowerLeft(mapElement.getPosition());
        }

        return super.toString();
    }
}
