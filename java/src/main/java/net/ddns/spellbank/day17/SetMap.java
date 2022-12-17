package net.ddns.spellbank.day17;

import java.util.HashSet;
import java.util.Set;

public class SetMap {
    public record Point(long row, int col) {
    };

    private Set<Point> map;
    private long maxHeight;

    public SetMap() {
        map = new HashSet<Point>();
        maxHeight = 0;
    }

    public void put(Point p) {
        map.add(p);
        maxHeight = Math.max(maxHeight, p.row);
    }

    public boolean contains(Point p) {
        return map.contains(p);
    }

    public long getMaxHeight() {
        return maxHeight;
    }
}
