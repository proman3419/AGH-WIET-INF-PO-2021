package agh.ics.oop;

import java.util.Comparator;

public class Comparators {
    static Comparator<AbstractWorldMapElement> comparatorX = new Comparator<AbstractWorldMapElement>() {
        @Override
        public int compare(AbstractWorldMapElement o1, AbstractWorldMapElement o2) {
            Vector2d pos1 = o1.getPosition();
            Vector2d pos2 = o2.getPosition();

            if (pos1.x < pos2.x) return -1;
            else if (pos1.x > pos2.x) return 1;
            else {
                if (pos1.y < pos2.y) return -1;
                else if (pos1.y > pos2.y) return 1;
            }

            return 0;
        }
    };

    static Comparator<AbstractWorldMapElement> comparatorY = new Comparator<AbstractWorldMapElement>() {
        @Override
        public int compare(AbstractWorldMapElement o1, AbstractWorldMapElement o2) {
            Vector2d pos1 = o1.getPosition();
            Vector2d pos2 = o2.getPosition();

            if (pos1.y < pos2.y) return -1;
            else if (pos1.y > pos2.y) return 1;
            else {
                if (pos1.x < pos2.x) return -1;
                else if (pos1.x > pos2.x) return 1;
            }

            return 0;
        }
    };
}
