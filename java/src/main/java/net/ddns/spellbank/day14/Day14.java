package net.ddns.spellbank.day14;

import java.util.HashMap;
import java.util.Map;

import net.ddns.spellbank.utils.InputFile;

public class Day14 {
    private record Point(int col, int row) {
    };

    private record MapVals(int maxRow, HashMap<Point, Character> map) {
    };

    public static void main(String[] args) {
        String file = "day14/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 638
        System.out.println(part2(lines)); // 31722
    }

    public static long part1(String[] lines) {
        var mapVals = parseInput(lines);
        long sand = 0;
        while (!dropSand(mapVals.map, mapVals.maxRow)) {
            sand++;
        }
        return sand;
    }

    public static long part2(String[] lines) {
        var mapVals = parseInput(lines);
        var map = mapVals.map;
        for (int col = 0; col < 700; col++) {
            map.put(new Point(col, mapVals.maxRow + 2), '#');
        }
        long sand = 0;
        while (!dropSandFloor(mapVals.map, mapVals.maxRow)) {
            sand++;
        }
        return sand;
    }

    private static MapVals parseInput(String[] lines) {
        var map = new HashMap<Point, Character>();
        int maxR = 0;
        for (var line : lines) {
            Point start = null;
            String[] fields = line.split(" -> ");
            for (var field : fields) {
                var coords = field.split(",");
                var col = Integer.parseInt(coords[0]);
                var row = Integer.parseInt(coords[1]);
                if (start == null) {
                    start = new Point(col, row);
                } else {
                    if (col == start.col) {
                        var minRow = Math.min(row, start.row);
                        var maxRow = Math.max(row, start.row);
                        for (int y = minRow; y <= maxRow; y++) {
                            map.put(new Point(col, y), '#');
                            maxR = Math.max(maxR, y);
                        }

                    } else {
                        var minCol = Math.min(col, start.col);
                        var maxCol = Math.max(col, start.col);
                        for (int x = minCol; x <= maxCol; x++) {
                            map.put(new Point(x, row), '#');
                            maxR = Math.max(maxR, row);
                        }
                    }
                    start = new Point(col, row);
                }

            }
        }
        return new MapVals(maxR, map);
    }

    private static boolean dropSand(Map<Point, Character> map, int maxRow) {
        int curRow = 0;
        int curCol = 500;
        while (true) {
            if (curRow > maxRow)
                return true;
            if (map.containsKey(new Point(curCol, curRow + 1))) {
                if (map.containsKey(new Point(curCol - 1, curRow + 1))) {
                    if (map.containsKey(new Point(curCol + 1, curRow + 1))) {
                        map.put(new Point(curCol, curRow), 'o');
                        return false;
                    } else {
                        curCol++;
                        curRow++;
                    }
                } else {
                    curRow++;
                    curCol--;
                }
            } else {
                curRow++;
            }
        }
    }

    private static boolean dropSandFloor(Map<Point, Character> map, int maxRow) {
        int curRow = 0;
        int curCol = 500;
        while (true) {
            if (map.containsKey(new Point(500, 0)))
                return true;
            if (map.containsKey(new Point(curCol, curRow + 1))) {
                if (map.containsKey(new Point(curCol - 1, curRow + 1))) {
                    if (map.containsKey(new Point(curCol + 1, curRow + 1))) {
                        map.put(new Point(curCol, curRow), 'o');
                        return false;
                    } else {
                        curCol++;
                        curRow++;
                    }
                } else {
                    curRow++;
                    curCol--;
                }
            } else {
                curRow++;
            }
        }
    }

    @SuppressWarnings("unused")
    private static void printMap(Map<Point, Character> map) {
        for (int row = 0; row < 11; row++) {
            for (int col = 494; col < 503; col++) {
                System.out.print(map.getOrDefault(new Point(col, row), '.'));
            }
            System.out.println();
        }
    }
}