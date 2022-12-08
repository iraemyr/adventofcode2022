package net.ddns.spellbank.day08;

import net.ddns.spellbank.utils.InputFile;

public class Day08 {

    public static void main(String[] args) {
        String file = "day08/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 1801
        System.out.println(part2(lines)); // 209880
    }

    public static long part1(String[] lines) {
        var height = lines.length;
        var width = lines[0].length();
        var trees = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                trees[y][x] = lines[y].charAt(x) - '0';
            }
        }
        long seen = 0;
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                if (x == 0 || y == 0 || x == width - 1 || y == height - 1 || checkUp(trees, x, y)
                        || checkDown(trees, x, y) || checkLeft(trees, x, y) || checkRight(trees, x, y))
                    seen++;
            }
        }
        return seen;
    }

    public static long part2(String[] lines) {
        var height = lines.length;
        var width = lines[0].length();
        var trees = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                trees[y][x] = lines[y].charAt(x) - '0';
            }
        }
        long seen = Long.MIN_VALUE;
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                seen = Math.max(seen, treesUp(trees, x, y) * treesDown(trees, x, y) * treesLeft(trees, x, y)
                        * treesRight(trees, x, y));
            }
        }
        return seen;
    }

    private static boolean checkUp(int[][] trees, int x, int y) {
        var height = trees[y][x];
        for (int col = y - 1; col >= 0; col--) {
            if (trees[col][x] >= height)
                return false;
        }
        return true;
    }

    private static boolean checkDown(int[][] trees, int x, int y) {
        var height = trees[y][x];
        for (int col = y + 1; col < trees.length; col++) {
            if (trees[col][x] >= height)
                return false;
        }
        return true;
    }

    private static boolean checkLeft(int[][] trees, int x, int y) {
        var height = trees[y][x];
        for (int row = x - 1; row >= 0; row--) {
            if (trees[y][row] >= height)
                return false;
        }
        return true;
    }

    private static boolean checkRight(int[][] trees, int x, int y) {
        var height = trees[y][x];
        for (int row = x + 1; row < trees[0].length; row++) {
            if (trees[y][row] >= height)
                return false;
        }
        return true;
    }

    private static long treesUp(int[][] trees, int x, int y) {
        var height = trees[y][x];
        long count = 0;
        for (int col = y - 1; col >= 0; col--) {
            count++;
            if (trees[col][x] >= height)
                break;
        }
        return count;
    }

    private static long treesDown(int[][] trees, int x, int y) {
        var height = trees[y][x];
        long count = 0;
        for (int col = y + 1; col < trees.length; col++) {
            count++;
            if (trees[col][x] >= height)
                break;
        }
        return count;
    }

    private static long treesLeft(int[][] trees, int x, int y) {
        var height = trees[y][x];
        long count = 0;
        for (int row = x - 1; row >= 0; row--) {
            count++;
            if (trees[y][row] >= height)
                break;
        }
        return count;
    }

    private static long treesRight(int[][] trees, int x, int y) {
        var height = trees[y][x];
        long count = 0;
        for (int row = x + 1; row < trees[0].length; row++) {
            count++;
            if (trees[y][row] >= height)
                break;
        }
        return count;
    }
}