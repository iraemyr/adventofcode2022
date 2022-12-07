package net.ddns.spellbank.day07;

import net.ddns.spellbank.utils.InputFile;

public class Day07 {

    public static void main(String[] args) {
        String file = "day07/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 1427048
        System.out.println(part2(lines)); // 2940614
    }

    public static long part1(String[] lines) {
        var filesystem = FileSystem.parseFileSystem(lines);
        return sumSize(filesystem);
    }

    public static long part2(String[] lines) {
        var filesystem = FileSystem.parseFileSystem(lines);
        var used = 70_000_000 - filesystem.getSize();
        return minDelete(filesystem, Long.MAX_VALUE, 30_000_000 - used);
    }

    private static long sumSize(FileSystem n) {
        if (n.isFile)
            return 0;
        long result = 0;
        for (var child : n.children) {
            result += sumSize(child);
        }
        if (n.getSize() <= 100_000)
            result += n.getSize();
        return result;
    }

    private static long minDelete(FileSystem n, long min, long required) {
        if (n.isFile)
            return min;
        for (var child : n.children) {
            min = Math.min(min, minDelete(child, min, required));
        }
        if (n.getSize() >= required) {
            return Math.min(min, n.getSize());
        }
        return min;
    }
}