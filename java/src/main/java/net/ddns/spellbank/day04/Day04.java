package net.ddns.spellbank.day04;

import net.ddns.spellbank.utils.InputFile;

public class Day04 {

    public static void main(String[] args) {
        String file = "day04/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 448
        System.out.println(part2(lines)); // 794
    }

    public static long part1(String[] lines) {
        long contained = 0;
        for (var line : lines) {
            var fields = line.split("[,-]");
            var start1 = Integer.parseInt(fields[0]);
            var end1 = Integer.parseInt(fields[1]);
            var start2 = Integer.parseInt(fields[2]);
            var end2 = Integer.parseInt(fields[3]);
            if ((start1 <= start2 && end1 >= end2) || (start2 <= start1 && end2 >= end1))
                contained++;
        }
        return contained;
    }

    public static long part2(String[] lines) {
        long overlap = 0;
        for (var line : lines) {
            var fields = line.split("[,-]");
            var start1 = Integer.parseInt(fields[0]);
            var end1 = Integer.parseInt(fields[1]);
            var start2 = Integer.parseInt(fields[2]);
            var end2 = Integer.parseInt(fields[3]);
            if (end1 >= start2 && end2 >= start1)
                overlap++;
        }
        return overlap;
    }
}