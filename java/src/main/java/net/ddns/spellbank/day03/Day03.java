package net.ddns.spellbank.day03;

import java.util.HashSet;
import java.util.Set;

import net.ddns.spellbank.utils.InputFile;

public class Day03 {

    public static void main(String[] args) {
        String file = "day03/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 8493
        System.out.println(part2(lines)); // 2552
    }

    public static long part1(String[] lines) {
        var items = new HashSet<Character>();
        long priorities = 0;
        for (var line : lines) {
            items.clear();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (i < line.length() / 2) {
                    items.add(c);
                } else if (items.contains(c)) {
                    priorities += getPriority(c);
                    break;
                }
            }
        }
        return priorities;
    }

    public static long part2(String[] lines) {
        int i = 0;
        long priorities = 0;
        Set<Character> packages1 = new HashSet<Character>();
        Set<Character> packages2 = new HashSet<Character>();
        while (i < lines.length) {
            packages1.clear();
            packages2.clear();
            for (char c : lines[i++].toCharArray())
                packages1.add(c);
            for (char c : lines[i++].toCharArray())
                packages2.add(c);
            packages1.retainAll(packages2);
            packages2.clear();
            for (char c : lines[i++].toCharArray())
                packages2.add(c);
            packages1.retainAll(packages2);
            var v = packages1.iterator().next();
            priorities += getPriority(v);
        }
        return priorities;
    }

    private static long getPriority(char c) {
        return Character.isLowerCase(c) ? c - 'a' + 1 : c - 'A' + 27;
    }
}