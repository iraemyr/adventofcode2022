package net.ddns.spellbank.day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import net.ddns.spellbank.utils.InputFile;

public class Day15 {
    private record Point(long col, long row) {
        public long manhattan(Point p) {
            return Math.abs(this.col - p.col) + Math.abs(this.row - p.row);
        }
    };

    private record Points(Point sensor, Point beacon) {
    };

    public static void main(String[] args) {
        String file = "day15/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines, 2000000)); // 4907780
        System.out.println(part2(lines, 4000000)); // 13639962836448
    }

    public static long part1(String[] lines, long row) {
        var sb = parsePoints(lines);
        var ranges = getRanges(sb, row);
        long sum = 0;
        for (var p : ranges)
            sum += p.row - p.col + 1;
        var beacons = new HashSet<Point>();
        for (var v : sb) {
            if (v.beacon.row == row && !beacons.contains(v.beacon)) {
                sum--;
                beacons.add(v.beacon);
            }
        }
        return sum;
    }

    public static long part2(String[] lines, long max) {
        var sb = parsePoints(lines);
        for (int i = 0; i <= max; i++) {
            var ranges = getRanges(sb, i);
            ranges = limitRanges(ranges, max);
            if (ranges.size() == 2) {
                var range = ranges.get(0);
                long col = range.col == 0 ? range.row + 1 : range.col - 1;
                return 4000000 * col + i;
            }
        }
        return 0;
    }

    private static List<Points> parsePoints(String[] lines) {
        var points = new ArrayList<Points>();
        for (var line : lines) {
            var fields = line.split(": ");
            var sensorText = fields[0].split(", ");
            var sensorCol = Long.parseLong(sensorText[0].split("=")[1]);
            var sensorRow = Long.parseLong(sensorText[1].split("=")[1]);
            var beaconText = fields[1].split(",");
            var beaconCol = Long.parseLong(beaconText[0].split("=")[1]);
            var beaconRow = Long.parseLong(beaconText[1].split("=")[1]);
            points.add(new Points(new Point(sensorCol, sensorRow), new Point(beaconCol, beaconRow)));
        }
        return points;
    }

    private static List<Point> getRanges(List<Points> points, long row) {
        var ranges = new ArrayList<Point>();
        for (var sb : points) {
            var sensor = sb.sensor;
            var beacon = sb.beacon;
            var dist = sensor.manhattan(beacon);
            var d = Math.abs(sensor.row - row);
            if (d <= dist) {
                var len = dist - d;
                addReduceRanges(ranges, new Point(sensor.col - len, sensor.col + len));
            }
        }
        return ranges;
    }

    private static void addReduceRanges(List<Point> ranges, Point p) {
        if (ranges.size() == 0) {
            ranges.add(p);
            return;
        }
        var remove = new ArrayList<Integer>();
        var start = p.col;
        var end = p.row;
        for (int i = 0; i < ranges.size(); i++) {
            var range = ranges.get(i);
            if (start >= range.col && end <= range.row) {
                return; // contained
            }
            if (start > range.row || end < range.col) {
                if (start == range.row + 1 || end == range.col - 1) { // join
                    remove.add(i);
                    start = Math.min(start, range.col);
                    end = Math.max(end, range.row);
                } // otherwise no overlap
            } else if (start <= range.col && end >= range.row) { // contains {
                remove.add(i);
            } else if ((start >= range.col && start <= range.row) || (end >= range.col && end <= range.row)) { // combine
                start = Math.min(start, range.col);
                end = Math.max(end, range.row);
                remove.add(i);
            }
        }
        Collections.reverse(remove);
        for (int index : remove)
            ranges.remove(index);
        ranges.add(new Point(start, end));
    }

    private static List<Point> limitRanges(List<Point> ranges, long limit) {
        var limited = new ArrayList<Point>();
        for (var p : ranges) {
            if (p.row < 0 || p.col > limit)
                continue; // Out of range
            limited.add(new Point(Math.max(p.col, 0), Math.min(p.row, limit)));
        }
        return limited;
    }
}