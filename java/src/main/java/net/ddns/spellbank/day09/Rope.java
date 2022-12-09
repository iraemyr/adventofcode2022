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
        if (Math.abs(head.x - tail.x) <= 1 && Math.abs(head.y - tail.y) <= 1) {
            return;
        }
        int tail_x = tail.x;
        int tail_y = tail.y;
        if (tail.x < head.x && head.x - tail.x > 1) {
            tail_x++;
            tail_y = head.y;
        } else if (tail.x > head.x && tail.x - head.x > 1) {
            tail_x--;
            tail_y = head.y;
        }
        if (tail.y < head.y && head.y - tail.y > 1) {
            tail_y++;
            tail_x = head.x;
        } else if (tail.y > head.y && tail.y - head.y > 1) {
            tail_y--;
            tail_x = head.x;
        }
        tail = new Point(tail_x, tail_y);
        visited.add(tail);
    }
}
