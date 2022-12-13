package net.ddns.spellbank.day12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.ddns.spellbank.utils.InputFile;

public class Day12 {
    record Point(int row, int col) {
        public List<Point> neighbors(char[][] map) {
            var neighbors = new ArrayList<Point>();
            if (row != 0 && map[row - 1][col] - map[row][col] <= 1) {
                neighbors.add(new Point(row - 1, col));
            }
            if (row < map.length - 1 && map[row + 1][col] - map[row][col] <= 1) {
                neighbors.add(new Point(row + 1, col));
            }
            if (col != 0 && map[row][col - 1] - map[row][col] <= 1) {
                neighbors.add(new Point(row, col - 1));
            }
            if (col < map[0].length - 1 && map[row][col + 1] - map[row][col] <= 1) {
                neighbors.add(new Point(row, col + 1));
            }
            return neighbors;
        }

        public List<Point> neighbors2(char[][] map) {
            var neighbors = new ArrayList<Point>();
            if (row != 0 && map[row][col] - map[row - 1][col] <= 1) {
                neighbors.add(new Point(row - 1, col));
            }
            if (row < map.length - 1 && map[row][col] - map[row + 1][col] <= 1) {
                neighbors.add(new Point(row + 1, col));
            }
            if (col != 0 && map[row][col] - map[row][col - 1] <= 1) {
                neighbors.add(new Point(row, col - 1));
            }
            if (col < map[0].length - 1 && map[row][col] - map[row][col + 1] <= 1) {
                neighbors.add(new Point(row, col + 1));
            }
            return neighbors;
        }
    };

    record State(Point p, int cost) {
    };

    public static void main(String[] args) {
        String file = "day12/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 330
        System.out.println(part2(lines)); // 321
    }

    public static long part1(String[] lines) {
        Point start = new Point(0, 0);
        Point end = new Point(0, 0);
        var map = new char[lines.length][lines[0].length()];
        for (int row = 0; row < lines.length; row++) {
            for (int col = 0; col < lines[0].length(); col++) {
                var c = lines[row].charAt(col);
                if (c == 'S') {
                    start = new Point(row, col);
                    map[row][col] = 'a';
                } else if (c == 'E') {
                    end = new Point(row, col);
                    map[row][col] = 'z';
                } else
                    map[row][col] = c;
            }
        }

        var visited = new HashSet<Point>();
        visited.add(start);
        var q = new HashSet<Point>();
        var q2 = new HashSet<Point>();
        q.add(start);
        int depth = -1;
        while (!q.isEmpty()) {
            depth++;
            for (var p : q) {
                visited.add(p);
                if (p.equals(end))
                    return depth;
                for (var neighbor : p.neighbors(map)) {
                    if (!visited.contains(neighbor)) {
                        q2.add(neighbor);
                    }
                }
            }
            var tmp = q;
            q = q2;
            q2 = tmp;
            q2.clear();
        }
        return -1;
    }

    public static long part2(String[] lines) {
        Point end = new Point(0, 0);
        var map = new char[lines.length][lines[0].length()];
        for (int row = 0; row < lines.length; row++) {
            for (int col = 0; col < lines[0].length(); col++) {
                var c = lines[row].charAt(col);
                if (c == 'S') {
                    map[row][col] = 'a';
                } else if (c == 'E') {
                    end = new Point(row, col);
                    map[row][col] = 'z';
                } else
                    map[row][col] = c;
            }
        }

        var visited = new HashSet<Point>();
        visited.add(end);
        var q = new HashSet<Point>();
        var q2 = new HashSet<Point>();
        q.add(end);
        int depth = -1;
        while (!q.isEmpty()) {
            depth++;

            for (var p : q) {
                visited.add(p);
                if (map[p.row][p.col] == 'a')
                    return depth;
                for (var neighbor : p.neighbors2(map))
                    if (!visited.contains(neighbor))
                        q2.add(neighbor);
            }
            var tmp = q;
            q = q2;
            q2 = tmp;
            q2.clear();
        }
        return -1;
    }

    @SuppressWarnings("unused")
    private static void printMap(char[][] map) {
        for (var l : map) {
            for (var c : l)
                System.out.print(c);
            System.out.println();
        }
    }
}