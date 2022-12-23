package net.ddns.spellbank.day23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.ddns.spellbank.utils.InputFile;

public class Day23 {

    public record Point(long row, long col) {
        public List<Point> neighbors(int direction) {
            var neighbors = new ArrayList<Point>();
            for (long i = -1; i <= 1; i++) {
                switch (direction) {
                    case 0: // North
                        neighbors.add(new Point(row - 1, col + i));
                        break;
                    case 1: // South
                        neighbors.add(new Point(row + 1, col + i));
                        break;
                    case 2: // West
                        neighbors.add(new Point(row + i, col - 1));
                        break;
                    case 3: // East
                        neighbors.add(new Point(row + i, col + 1));
                        break;
                }
            }
            return neighbors;
        }

        public Point neighbor(int direction) {
            switch (direction) {
                case 0:
                    return new Point(row - 1, col);
                case 1:
                    return new Point(row + 1, col);
                case 2:
                    return new Point(row, col - 1);
                case 3:
                    return new Point(row, col + 1);
                default:
                    return null;
            }
        }
    }

    public static void main(String[] args) {
        String file = "day23/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 3925
        System.out.println(part2(lines)); // 903
    }

    public static long part1(String[] lines) {
        var elves = parseInput(lines);
        return doRounds(elves, 10);
    }

    public static long part2(String[] lines) {
        var elves = parseInput(lines);
        return doRounds(elves, 10_000);
    }

    private static long doRounds(Set<Point> elves, int rounds) {
        int direction = 0;
        boolean moved = true;
        long count = 0;
        for (int i = 0; i < rounds && moved; i++) {
            count++;
            moved = doRound(elves, direction);
            direction++;
            if (direction == 4)
                direction = 0;
        }

        if (rounds == 10_000)
            return count;
        return emptyGround(elves);
    }

    private static boolean doRound(Set<Point> elves, int firstDirection) {
        var proposed = new HashMap<Point, List<Point>>();
        for (var elf : elves) {
            var directions = new boolean[4];
            for (int i = 0; i < 4; i++) {
                for (var n : elf.neighbors(i)) {
                    if (elves.contains(n)) {
                        directions[i] = false;
                        break;
                    }
                    directions[i] = true;
                }
            }

            if (directions[0] && directions[1] && directions[2] && directions[3])
                continue;

            for (int i = 0; i < 4; i++) {
                var curDir = (firstDirection + i) % 4;
                if (directions[curDir]) {
                    var list = proposed.getOrDefault(elf.neighbor(curDir), new ArrayList<Point>());
                    list.add(elf);
                    proposed.put(elf.neighbor(curDir), list);
                    break;
                }
            }
        }

        boolean moved = false;
        for (var p : proposed.keySet()) {
            var li = proposed.get(p);
            if (li.size() == 1) {
                elves.remove(li.get(0));
                elves.add(p);
                moved = true;
            }
        }
        return moved;
    }

    private static long emptyGround(Set<Point> elves) {
        long minRow = Long.MAX_VALUE;
        long maxRow = Long.MIN_VALUE;
        long minCol = Long.MAX_VALUE;
        long maxCol = Long.MIN_VALUE;
        for (var elf : elves) {
            minRow = Math.min(minRow, elf.row);
            maxRow = Math.max(maxRow, elf.row);
            minCol = Math.min(minCol, elf.col);
            maxCol = Math.max(maxCol, elf.col);
        }

        return (maxRow - minRow + 1) * (maxCol - minCol + 1) - elves.size();
    }

    private static Set<Point> parseInput(String[] lines) {
        var elves = new HashSet<Point>();
        for (int row = 0; row < lines.length; row++) {
            var line = lines[row];
            for (int col = 0; col < line.length(); col++) {
                if (line.charAt(col) == '#')
                    elves.add(new Point(row, col));
            }
        }
        return elves;
    }

    @SuppressWarnings("unused")
    private static void printMap(Set<Point> map) {
        for (long row = -11; row < 12; row++) {
            for (long col = -11; col < 12; col++) {
                System.out.print(map.contains(new Point(row, col)) ? "#" : ".");
            }
            System.out.println();
        }
        System.out.println();
    }

}