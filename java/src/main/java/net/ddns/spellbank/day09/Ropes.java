package net.ddns.spellbank.day09;

import java.util.HashSet;
import java.util.Set;

public class Ropes implements IRope {
    public static record Point(int x, int y) {
    };

    Point[] rope = new Point[10];
    Set<Point> visited;

    public Ropes() {
        for (int i = 0; i < 10; i++)
            rope[i] = new Point(0, 0);
        visited = new HashSet<Point>();
        visited.add(rope[9]);
    }

    public void up(int d) {
        for (int i = 0; i < d; i++) {
            rope[0] = new Point(rope[0].x, rope[0].y - 1);
            updateTail();
        }
    }

    public void down(int d) {
        for (int i = 0; i < d; i++) {
            rope[0] = new Point(rope[0].x, rope[0].y + 1);
            updateTail();
        }
    }

    public void right(int d) {
        for (int i = 0; i < d; i++) {
            rope[0] = new Point(rope[0].x + 1, rope[0].y);
            updateTail();
        }
    }

    public void left(int d) {
        for (int i = 0; i < d; i++) {
            rope[0] = new Point(rope[0].x - 1, rope[0].y);
            updateTail();
        }
    }

    public long numVisited() {
        return visited.size();
    }

    private void updateTail() {
        for (int i = 1; i < rope.length; i++) {
            int head_x = rope[i - 1].x;
            int head_y = rope[i - 1].y;
            int tail_x = rope[i].x;
            int tail_y = rope[i].y;
            int diffX = head_x - tail_x;
            int diffY = head_y - tail_y;
            if (Math.abs(diffX) <= 1 && Math.abs(diffY) <= 1) {
                break;
            }

            tail_x += Integer.signum(diffX);
            tail_y += Integer.signum(diffY);
            rope[i] = new Point(tail_x, tail_y);
        }
        visited.add(rope[9]);
    }

    @SuppressWarnings("unused")
    private void print() {
        for (Point p : rope)
            System.out.println(p);
        System.out.println();
    }
}
