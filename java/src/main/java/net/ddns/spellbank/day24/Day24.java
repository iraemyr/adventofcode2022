package net.ddns.spellbank.day24;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.ddns.spellbank.utils.InputFile;

public class Day24 {

    public record Point(int row, int col) {
        public Point nextPos(char c, int height, int width) {
            switch (c) {
                case '>':
                    if (col == width - 2)
                        return new Point(row, 1);
                    return new Point(row, col + 1);
                case '<':
                    if (col == 1)
                        return new Point(row, width - 2);
                    return new Point(row, col - 1);
                case '^':
                    if (row == 1)
                        return new Point(height - 2, col);
                    return new Point(row - 1, col);
                case 'v':
                    if (row == height - 2)
                        return new Point(1, col);
                    return new Point(row + 1, col);
                default:
                    System.out.println("Invalid element");
                    return new Point(-1, -1);
            }
        }

        public List<Point> neighbors(Map<Point, List<Character>> map, int height, int width) {
            var neighbors = new ArrayList<Point>();
            var p = new Point(row - 1, col);
            if (row != 0 && !map.containsKey(p))
                neighbors.add(p);
            p = new Point(row + 1, col);
            if (row != height - 1 && !map.containsKey(p))
                neighbors.add(p);
            p = new Point(row, col - 1);
            if (col != 0 && !map.containsKey(p))
                neighbors.add(p);
            p = new Point(row, col + 1);
            if (col != width - 1 && !map.containsKey(p))
                neighbors.add(p);
            if (!map.containsKey(this))
                neighbors.add(this);
            return neighbors;
        }
    }

    public record State(long minutes, Point pos) {
    }

    public record IntermediateState(long minutes, Map<Point, List<Point>> map) {
    }

    public static void main(String[] args) {
        String file = "day24/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 314
        System.out.println(part2(lines)); // 896
    }

    public static long part1(String[] lines) {
        var map = parseMap(lines);
        int height = lines.length;
        int width = lines[0].length();
        var mapStates = new HashMap<Long, Map<Point, List<Character>>>();
        mapStates.put(0L, map);
        return bfs(new Point(0, 1), new Point(height - 1, width - 2), 0, mapStates, height, width);
    }

    public static long part2(String[] lines) {
        var map = parseMap(lines);
        int height = lines.length;
        int width = lines[0].length();
        var mapStates = new HashMap<Long, Map<Point, List<Character>>>();
        mapStates.put(0L, map);
        long minutes = 0;
        minutes = bfs(new Point(0, 1), new Point(height - 1, width - 2), minutes, mapStates, height, width);
        minutes = bfs(new Point(height - 1, width - 2), new Point(0, 1), minutes, mapStates, height, width);
        return bfs(new Point(0, 1), new Point(height - 1, width - 2), minutes, mapStates, height, width);
    }

    private static Map<Point, List<Character>> parseMap(String[] lines) {
        var map = new HashMap<Point, List<Character>>();
        for (int row = 0; row < lines.length; row++) {
            var line = lines[row];
            for (int col = 0; col < line.length(); col++) {
                char c = line.charAt(col);
                var p = new Point(row, col);
                switch (c) {
                    case '.':
                        break;
                    default:
                        var li = map.getOrDefault(p, new ArrayList<Character>());
                        li.add(c);
                        map.put(p, li);
                }
            }
        }
        return map;
    }

    private static void updateMap(long round, int height, int width, Map<Long, Map<Point, List<Character>>> mapStates) {
        if (mapStates.containsKey(round))
            return;
        var prev = mapStates.get(round - 1);
        var map = new HashMap<Point, List<Character>>();
        for (var p : prev.keySet()) {
            var elements = prev.get(p);
            if (elements.size() == 1 && elements.get(0) == '#')
                map.put(p, elements);
            else {
                for (var element : elements) {
                    var pos = p.nextPos(element, height, width);
                    var li = map.getOrDefault(pos, new ArrayList<Character>());
                    li.add(element);
                    map.put(pos, li);
                }
            }
        }
        mapStates.put(round, map);
    }

    private static long bfs(Point startPos, Point end, long round, Map<Long, Map<Point, List<Character>>> mapStates,
            int height, int width) {
        var visited = new HashSet<State>();
        var q = new ArrayDeque<State>();
        var start = new State(round, startPos);
        q.add(start);
        visited.add(start);
        while (!q.isEmpty()) {
            var state = q.remove();
            if (state.pos.equals(end)) {
                return state.minutes;
            }
            updateMap(state.minutes + 1, height, width, mapStates);
            for (var neighbor : state.pos.neighbors(mapStates.get(state.minutes + 1), height, width)) {
                var newState = new State(state.minutes + 1, neighbor);
                if (!visited.contains(newState)) {
                    q.add(newState);
                    visited.add(newState);
                }
            }
        }
        return -1;
    }
}