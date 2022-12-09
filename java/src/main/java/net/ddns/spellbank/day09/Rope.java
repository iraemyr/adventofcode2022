package net.ddns.spellbank.day09;

import java.util.HashSet;
import java.util.Set;

public class Rope implements IRope {
    public static record Point(int x, int y) {
    };

    Point head;
    Point tail;
    Set<Point> visited;

    public Rope(Point head, Point tail) {
        this.head = head;
        this.tail = tail;
        visited = new HashSet<Point>();
        visited.add(tail);
    }

    public void up(int d) {
        for (int i = 0; i < d; i++) {
            head = new Point(head.x, head.y - 1);
            updateTail();
        }
    }

    public void down(int d) {
        for (int i = 0; i < d; i++) {
            head = new Point(head.x, head.y + 1);
            updateTail();
        }
    }

    public void right(int d) {
        for (int i = 0; i < d; i++) {
            head = new Point(head.x + 1, head.y);
            updateTail();
        }
    }

    public void left(int d) {
        for (int i = 0; i < d; i++) {
            head = new Point(head.x - 1, head.y);
            updateTail();
        }
    }

    public long numVisited() {
        return visited.size();
    }

    private void updateTail() {
        int diffX = head.x - tail.x;
        int diffY = head.y - tail.y;
        if (Math.abs(diffX) > 1 || Math.abs(diffY) > 1) {
            int tail_x = tail.x + Integer.signum(diffX);
            int tail_y = tail.y + Integer.signum(diffY);
            tail = new Point(tail_x, tail_y);
            visited.add(tail);
        }
    }
}
