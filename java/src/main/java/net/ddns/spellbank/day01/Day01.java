package net.ddns.spellbank.day01;

import java.util.ArrayList;
import java.util.Collections;

import net.ddns.spellbank.utils.InputFile;

public class Day01 {

    public static void main(String[] args) {
        String file = "day01/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 72017
        System.out.println(part2(lines)); // 212520
    }

    public static long part1(String[] lines) {
        long max = Long.MIN_VALUE;
        long sum = 0;

        for (var line : lines) {
            if (line.trim().isEmpty()) {
                max = Math.max(max, sum);
                sum = 0;
            } else {
                sum += Long.parseLong(line.trim());
            }
        }
        max = Math.max(max, sum);
        return max;
    }

    public static long part2(String[] lines) {
        var calories = new ArrayList<Long>();
        long sum = 0;

        for (var line : lines) {
            if (line.trim().isEmpty()) {
                calories.add(sum);
                sum = 0;
            } else {
                sum += Long.parseLong(line.trim());
            }
        }
        calories.add(sum);
        Collections.sort(calories, Collections.reverseOrder());
        return calories.stream().limit(3).mapToLong(a -> a).sum();
    }

}