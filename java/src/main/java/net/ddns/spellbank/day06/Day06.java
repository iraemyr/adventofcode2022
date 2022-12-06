package net.ddns.spellbank.day06;

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
            var result = allDif(line.substring(i, i + 4));
            if (result == -1)
                return i + 4;
            else
                i += result;
        }
        return 0;
    }

    public static long part2(String[] lines) {
        var line = lines[0];
        for (int i = 0; i < line.length() - 14; i++) {
            var result = allDif(line.substring(i, i + 14));
            if (result == -1)
                return i + 14;
            else
                i += result;
        }
        return 0;
    }

    private static int allDif(String s) {
        for (int x = s.length() - 1; x > 0; x--) {
            for (int y = x - 1; y >= 0; y--) {
                if (s.charAt(x) == s.charAt(y))
                    return y;
            }
        }
        return -1;
    }
}