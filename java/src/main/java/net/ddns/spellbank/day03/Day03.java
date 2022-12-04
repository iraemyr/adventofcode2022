package net.ddns.spellbank.day03;

import java.util.BitSet;

import net.ddns.spellbank.utils.InputFile;

public class Day03 {

    public static void main(String[] args) {
        String file = "day03/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 8493
        System.out.println(part2(lines)); // 2552
    }

    public static long part1(String[] lines) {
        var items = new BitSet(52);
        long priorities = 0;
        for (var line : lines) {
            items.clear();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                var priority = getPriority(c);
                if (i < line.length() / 2) {
                    items.set(priority);
                } else if (items.get(priority)) {
                    priorities += priority;
                    break;
                }
            }
        }
        return priorities;
    }

    public static long part2(String[] lines) {
        int i = 0;
        long priorities = 0;
        var packages1 = new BitSet(52);
        var packages2 = new BitSet(52);
        while (i < lines.length) {
            packages1.clear();
            packages2.clear();
            for (char c : lines[i++].toCharArray())
                packages1.set(getPriority(c));
            for (char c : lines[i++].toCharArray())
                packages2.set(getPriority(c));
            packages1.and(packages2);
            packages2.clear();
            for (char c : lines[i++].toCharArray())
                packages2.set(getPriority(c));
            packages1.and(packages2);
            priorities += packages1.nextSetBit(0);
        }
        return priorities;
    }

    private static int getPriority(char c) {
        return Character.isLowerCase(c) ? c - 'a' + 1 : c - 'A' + 27;
    }
}