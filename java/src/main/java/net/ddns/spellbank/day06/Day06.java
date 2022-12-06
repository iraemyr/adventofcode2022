package net.ddns.spellbank.day06;

import java.util.HashSet;

import net.ddns.spellbank.utils.InputFile;

public class Day06 {

    public static void main(String[] args) {
        String file = "day06/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 1480
        System.out.println(part2(lines)); // 2746
    }

    public static long part1(String[] lines) {
        var line = lines[0];
        for (int i = 0; i < line.length() - 4; i++) {
            if (allDif(line.substring(i, i + 4)))
                return i + 4;
        }
        return 0;
    }

    public static long part2(String[] lines) {
        var line = lines[0];
        for (int i = 0; i < line.length() - 14; i++) {
            if (allDif(line.substring(i, i + 14)))
                return i + 14;
        }
        return 0;
    }

    private static boolean allDif(String s) {
        var code = new HashSet<Character>();
        for (char c : s.toCharArray())
            code.add(c);
        return code.size() == s.length();
    }
}