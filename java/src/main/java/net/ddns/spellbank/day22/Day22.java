package net.ddns.spellbank.day22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.ddns.spellbank.utils.InputFile;

public class Day22 {
    record Point(int row, int col) {
    }

    record PosAndHeading(Point pos, int heading) {
    }

    record IndexMap(int index, Map<Point, Boolean> map) {
    }

    record Instruction(int num, char dir, boolean isDirection) {
    }

    public static void main(String[] args) {
        String file = "day22/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 20494
        System.out.println(part2(lines)); // 55343
    }

    public static long part1(String[] lines) {
        var im = parseInput(lines);
        var map = im.map;
        var instructions = parseInstructions(lines[im.index]);
        return followInstructions(map, instructions);
    }

    public static long part2(String[] lines) {
        var im = parseInput(lines);
        var map = im.map;
        var instructions = parseInstructions(lines[im.index]);
        return followInstructionsCube(map, instructions);
    }

    private static IndexMap parseInput(String[] lines) {
        int index = 0;
        var map = new HashMap<Point, Boolean>();
        while (!lines[index].equals("")) {
            var line = lines[index];
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '.')
                    map.put(new Point(index + 1, i + 1), false);
                else if (line.charAt(i) == '#')
                    map.put(new Point(index + 1, i + 1), true);
            }
            index++;
        }
        return new IndexMap(index + 1, map);
    }

    private static List<Instruction> parseInstructions(String line) {
        var instructions = new ArrayList<Instruction>();
        int num = 0;
        for (int i = 0; i < line.length(); i++) {
            if (!Character.isDigit(line.charAt(i))) {
                instructions.add(new Instruction(0, line.charAt(i), true));
                continue;
            }
            int stop = i;
            while (stop < line.length() && Character.isDigit(line.charAt(stop)))
                stop++;
            if (stop == line.length())
                num = Integer.parseInt(line.substring(i));
            else
                num = Integer.parseInt(line.substring(i, stop));
            instructions.add(new Instruction(num, ' ', false));
            i = stop - 1;
        }
        return instructions;
    }

    private static long followInstructions(Map<Point, Boolean> map, List<Instruction> instructions) {
        var portals = new HashMap<Point, Point>();
        var startCol = 1;
        while (!map.containsKey(new Point(1, startCol)))
            startCol++;
        var pos = new Point(1, startCol);
        int facing = 0;
        for (var instruction : instructions) {
            if (instruction.isDirection) {
                if (instruction.dir == 'R') {
                    facing++;
                    if (facing == 4)
                        facing = 0;
                } else {
                    facing--;
                    if (facing == -1)
                        facing = 3;
                }
            } else {
                pos = move(pos, facing, instruction.num, map, portals);
            }
        }
        return pos.row * 1000 + pos.col * 4 + facing;
    }

    private static Point move(Point pos, int facing, int dist, Map<Point, Boolean> map, Map<Point, Point> portals) {
        switch (facing) {
            case 0:
                pos = doMove(pos, new Point(0, 1), dist, map, portals);
                break;
            case 1:
                pos = doMove(pos, new Point(1, 0), dist, map, portals);
                break;
            case 2:
                pos = doMove(pos, new Point(0, -1), dist, map, portals);
                break;
            case 3:
                pos = doMove(pos, new Point(-1, 0), dist, map, portals);
                break;
            default:
                System.out.println("Invalid facing");
        }
        return pos;
    }

    private static Point doMove(Point pos, Point heading, int dist, Map<Point, Boolean> map,
            Map<Point, Point> portals) {
        int row = pos.row;
        int col = pos.col;
        for (int i = 0; i < dist; i++) {
            var p = new Point(row + heading.row, col + heading.col);
            if (map.containsKey(p)) {
                if (map.get(p))
                    break;
                else {
                    row = p.row;
                    col = p.col;
                }
            } else {
                if (portals.containsKey(p)) {
                    var dest = portals.get(p);
                    if (map.get(dest))
                        break;
                    row = dest.row;
                    col = dest.col;
                } else {
                    int r = row;
                    int c = col;
                    var dir = new Point(-heading.row, -heading.col);
                    while (map.containsKey(new Point(r + dir.row, c + dir.col))) {
                        r += dir.row;
                        c += dir.col;
                    }
                    var p2 = new Point(r, c);
                    portals.put(p, p2);
                    if (map.get(p2))
                        break;
                    row = p2.row;
                    col = p2.col;
                }
            }
        }
        return new Point(row, col);
    }

    private static long followInstructionsCube(Map<Point, Boolean> map, List<Instruction> instructions) {
        var startCol = 1;
        while (!map.containsKey(new Point(1, startCol)))
            startCol++;
        var pos = new Point(1, startCol);
        int facing = 0;

        for (var instruction : instructions) {
            if (instruction.isDirection) {
                if (instruction.dir == 'R') {
                    facing++;
                    if (facing == 4)
                        facing = 0;
                } else {

                    facing--;
                    if (facing == -1)
                        facing = 3;
                }
            } else {
                var posAndHeading = moveCube(pos, facing, instruction.num, map);
                pos = posAndHeading.pos;
                facing = posAndHeading.heading;
            }
        }
        return pos.row * 1000 + pos.col * 4 + facing;
    }

    private static PosAndHeading moveCube(Point pos, int facing, int dist, Map<Point, Boolean> map) {
        PosAndHeading pah = null;
        switch (facing) {
            case 0:
                pah = doMoveCube(pos, new Point(0, 1), facing, dist, map);
                break;
            case 1:
                pah = doMoveCube(pos, new Point(1, 0), facing, dist, map);
                break;
            case 2:
                pah = doMoveCube(pos, new Point(0, -1), facing, dist, map);
                break;
            case 3:
                pah = doMoveCube(pos, new Point(-1, 0), facing, dist, map);
                break;
            default:
                System.out.println("Invalid facing");
        }
        return pah;
    }

    private static Point getHeading(int facing) {
        switch (facing) {
            case 0:
                return new Point(0, 1);
            case 1:
                return new Point(1, 0);
            case 2:
                return new Point(0, -1);
            case 3:
                return new Point(-1, 0);
            default:
                System.out.println("Invalid facing");
        }
        return null;
    }

    private static PosAndHeading doMoveCube(Point pos, Point heading, int facing, int dist, Map<Point, Boolean> map) {
        int row = pos.row;
        int col = pos.col;
        for (int i = 0; i < dist; i++) {
            var p = new Point(row + heading.row, col + heading.col);
            if (map.containsKey(p)) {
                if (map.get(p))
                    break;
                else {
                    row = p.row;
                    col = p.col;
                }
            } else {
                var pah = cubeMapping(new Point(row, col), facing);
                if (map.get(pah.pos))
                    break;
                row = pah.pos.row;
                col = pah.pos.col;
                facing = pah.heading;
                heading = getHeading(facing);
            }
        }
        return new PosAndHeading(new Point(row, col), facing);
    }

    public static PosAndHeading cubeMapping(Point p, int dir) {
        int face = getFace(p);
        switch (face) {
            case 1:
                if (dir == 0) {
                    return new PosAndHeading(new Point(151 - p.row, 150), 2);
                }
                return new PosAndHeading(new Point(p.col + 100, 50), 2);
            case 2:
                if (dir == 2) {
                    return new PosAndHeading(new Point(151 - p.row, 51), 0);
                }
                return new PosAndHeading(new Point(p.col + 50, 51), 0);
            case 3:
                if (dir == 1) {
                    return new PosAndHeading(new Point(1, p.col + 100), 1);
                }
                if (dir == 0) {
                    return new PosAndHeading(new Point(150, p.row - 100), 3);
                }
                return new PosAndHeading(new Point(1, p.row - 100), 1);
            case 4:
                if (dir == 0) {
                    return new PosAndHeading(new Point(50, p.row + 50), 3);
                }
                return new PosAndHeading(new Point(101, p.row - 50), 1);
            case 5:
                if (dir == 2) {
                    return new PosAndHeading(new Point(151 - p.row, 1), 0);
                }
                return new PosAndHeading(new Point(p.col + 100, 1), 0);
            case 6:
                if (dir == 3) {
                    return new PosAndHeading(new Point(200, p.col - 100), 3);
                }
                if (dir == 0) {
                    return new PosAndHeading(new Point(151 - p.row, 100), 2);
                }
                return new PosAndHeading(new Point(p.col - 50, 100), 2);
            default:
                System.out.println("Invalid coordinate");
        }
        return null;
    }

    private static int getFace(Point p) {
        if (p.row > 150)
            return 3;
        if (p.col > 100)
            return 6;
        if (p.row < 51)
            return 5;
        if (p.row < 101)
            return 4;
        if (p.col < 51)
            return 2;
        return 1;
    }

}