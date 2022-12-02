package net.ddns.spellbank.day02;

import net.ddns.spellbank.utils.InputFile;

public class Day02 {

    public static void main(String[] args) {
        String file = "day02/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 12679
        System.out.println(part2(lines)); // 14470
    }

    public static long part1(String[] lines) {
        long score = 0;
        for (var line : lines) {
            var fields = line.split(" ");
            score += switch (fields[0]) {
                case "A" -> switch (fields[1]) {
                    case "X" -> 3 + 1;
                    case "Y" -> 6 + 2;
                    default -> 3;
                };
                case "B" -> switch (fields[1]) {
                    case "X" -> 1;
                    case "Y" -> 3 + 2;
                    default -> 6 + 3;
                };
                default -> switch (fields[1]) {
                    case "X" -> 6 + 1;
                    case "Y" -> 2;
                    default -> 3 + 3;
                };
            };
        }
        return score;
    }

    public static long part2(String[] lines) {
        long score = 0;
        for (var line : lines) {
            var fields = line.split(" ");
            score += switch (fields[0]) {
                case "A" -> switch (fields[1]) {
                    case "X" -> 3;
                    case "Y" -> 3 + 1;
                    default -> 6 + 2;
                };
                case "B" -> switch (fields[1]) {
                    case "X" -> 1;
                    case "Y" -> 3 + 2;
                    default -> 6 + 3;
                };
                default -> switch (fields[1]) {
                    case "X" -> 2;
                    case "Y" -> 3 + 3;
                    default -> 6 + 1;
                };
            };
        }
        return score;
    }
}