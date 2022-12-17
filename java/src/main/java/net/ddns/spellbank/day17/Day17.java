package net.ddns.spellbank.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import net.ddns.spellbank.day17.SetMap.Point;
import net.ddns.spellbank.utils.InputFile;

public class Day17 {
    record State(long height, long fallen, boolean cycle) {
    }

    public static void main(String[] args) {
        String file = "day17/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 3153
        System.out.println(part2(lines)); // 1553665689155
    }

    public static long part1(String[] lines) {
        return dropRocksSingleStep(lines[0], 2022, s -> false).height;
    }

    public static long part2(String[] lines) {
        long numRocks = 1000000000000L;
        var cycleStart = dropRocksSingleStep(lines[0], numRocks, s -> s.cycle && s.fallen != 0);
        var nextCycle = dropRocksSingleStep(lines[0], numRocks, s -> s.cycle && s.fallen > cycleStart.fallen);
        long rocks = cycleStart.fallen;
        long height = cycleStart.height;
        while (rocks < numRocks) {
            rocks += nextCycle.fallen - cycleStart.fallen;
            height += nextCycle.height - cycleStart.height;
        }
        long overshoot = rocks - numRocks;
        State atOvershoot = dropRocksSingleStep(lines[0], numRocks, s -> s.fallen == cycleStart.fallen - overshoot);
        return height - (cycleStart.height - atOvershoot.height);
    }

    private static State dropRocksSingleStep(String line, long steps, Predicate<State> stop) {
        var jet = line.toCharArray();
        var map = new SetMap();
        var shapes = getShapes();
        int jetIndex = 0;
        int shapeIndex = 0;
        long fallen = 0;
        var shape = shapes.get(shapeIndex);
        var height = map.getMaxHeight() + 3 + shapes.get(shapeIndex).length;
        var horizontal = 3;
        while (fallen < steps) {
            if (jetIndex == jet.length)
                jetIndex = 0;

            var st = new State(map.getMaxHeight(), fallen, jetIndex == 0);
            if (stop.test(st))
                return st;

            char dir = jet[jetIndex++];
            if (dir == '<') {
                if (okLeft(map, shape, height, horizontal))
                    horizontal--;
            } else if (okRight(map, shape, height, horizontal))
                horizontal++;

            if (okDown(map, shape, height, horizontal))
                height--;
            else {
                for (int row = 0; row < shape.length; row++) {
                    for (int col = 0; col < shape[0].length; col++) {
                        if (shape[row][col])
                            map.put(new Point(height - row, horizontal + col));
                    }
                }
                fallen++;
                if (++shapeIndex == shapes.size())
                    shapeIndex = 0;
                shape = shapes.get(shapeIndex);
                height = map.getMaxHeight() + 3 + shape.length;
                horizontal = 3;
            }
        }
        return new State(map.getMaxHeight(), steps, false);
    }

    private static boolean okLeft(SetMap map, boolean[][] shape, long height, int col) {
        if (col == 1) {
            return false;
        }
        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape[0].length; x++) {
                if (shape[y][x] && map.contains(new Point(height - y, col + x - 1))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean okRight(SetMap map, boolean[][] shape, long height, int col) {
        if (col + shape[0].length - 1 == 7) {
            return false;
        }
        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape[0].length; x++) {
                if (shape[y][x] && map.contains(new Point(height - y, col + x + 1))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean okDown(SetMap map, boolean[][] shape, long height, int col) {
        if (height - shape.length == 0) {
            return false;
        }
        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape[0].length; x++) {
                if (shape[y][x] && map.contains(new Point(height - y - 1, col + x))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static List<boolean[][]> getShapes() {
        var shapes = new ArrayList<boolean[][]>();
        var shape = new boolean[1][4];
        for (int i = 0; i < 4; i++) {
            shape[0][i] = true;
        }
        shapes.add(shape);

        shape = new boolean[3][3];
        shape[0][1] = true;
        shape[1][0] = true;
        shape[1][1] = true;
        shape[1][2] = true;
        shape[2][1] = true;
        shapes.add(shape);

        shape = new boolean[3][3];
        shape[0][2] = true;
        shape[1][2] = true;
        shape[2][0] = true;
        shape[2][1] = true;
        shape[2][2] = true;
        shapes.add(shape);

        shape = new boolean[4][1];
        shape[0][0] = true;
        shape[1][0] = true;
        shape[2][0] = true;
        shape[3][0] = true;
        shapes.add(shape);

        shape = new boolean[2][2];
        shape[0][0] = true;
        shape[0][1] = true;
        shape[1][0] = true;
        shape[1][1] = true;
        shapes.add(shape);

        return shapes;
    }

    @SuppressWarnings("unused")
    private static void printShape(boolean[][] shape) {
        for (var row : shape) {
            for (var col : row) {
                System.out.print(col ? '#' : '.');
            }
            System.out.println();
        }
        System.out.println();
    }

    @SuppressWarnings("unused")
    private static void printMap(SetMap map) {
        for (long row = map.getMaxHeight(); row > 0; row--) {
            for (int col = 1; col <= 7; col++) {
                System.out.print(map.contains(new Point(row, col)) ? '#' : '.');
            }
            System.out.println();
        }
        System.out.println();
    }
}