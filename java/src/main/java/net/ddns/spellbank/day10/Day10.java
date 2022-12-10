package net.ddns.spellbank.day10;

import net.ddns.spellbank.utils.InputFile;

public class Day10 {

    public static void main(String[] args) {
        String file = "day10/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 14860
        printScreen(part2(lines)); // RGZEHURK
    }

    public static long part1(String[] lines) {
        int x = 1;
        int cycle = 0;
        long sum = 0;
        for (var line : lines) {
            if (line.charAt(0) == 'n')
                sum += cycle(x, ++cycle);
            else {
                var fields = line.split(" ");
                var offset = Integer.parseInt(fields[1]);
                sum += cycle(x, ++cycle);
                sum += cycle(x, ++cycle);
                x += offset;
            }
        }
        return sum;
    }

    public static char[][] part2(String[] lines) {
        int x = 1;
        int row = 0;
        int col = -1;
        char[][] screen = new char[6][40];
        for (var line : lines) {
            if (line.charAt(0) == 'n') {
                if (++col == 40) {
                    row++;
                    col = 0;
                }
                updateScreen(screen, x, row, col);
            } else {
                var fields = line.split(" ");
                var offset = Integer.parseInt(fields[1]);
                if (++col == 40) {
                    row++;
                    col = 0;
                }
                updateScreen(screen, x, row, col);
                if (++col == 40) {
                    row++;
                    col = 0;
                }
                updateScreen(screen, x, row, col);
                x += offset;
            }
        }
        return screen;
    }

    private static long cycle(int x, int cycle) {
        if (cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220) {
            return cycle * x;
        }
        return 0;
    }

    private static void updateScreen(char[][] screen, int x, int row, int col) {
        if (Math.abs(col - x) <= 1) {
            screen[row][col] = '#';
        } else
            screen[row][col] = '.';
    }

    private static void printScreen(char[][] screen) {
        for (var row : screen) {
            for (var col : row)
                System.out.print(col);
            System.out.println();
        }
    }
}