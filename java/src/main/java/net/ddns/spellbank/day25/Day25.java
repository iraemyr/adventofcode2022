package net.ddns.spellbank.day25;

import net.ddns.spellbank.utils.InputFile;

public class Day25 {

    public static void main(String[] args) {
        String file = "day25/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 2=20---01==222=0=0-2
    }

    public static String part1(String[] lines) {
        long sum = 0;
        for (var line : lines) {
            sum += parseNumber(line);
        }
        return toSnafu2(sum);
    }

    private static long parseNumber(String line) {
        var arr = line.toCharArray();
        long sum = 0;
        long mul = 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            switch (arr[i]) {
                case '-':
                    sum -= 1 * mul;
                    break;
                case '=':
                    sum -= 2 * mul;
                    break;
                case '1':
                    sum += mul;
                    break;
                case '2':
                    sum += 2 * mul;
                    break;
                case '0':
                    break;
                default:
                    System.out.println("Invalid character: " + arr[i]);
            }
            mul *= 5;
        }
        return sum;
    }

    private static String toSnafu2(long sum) {
        StringBuilder sb = new StringBuilder();
        while (sum != 0) {
            int remainder = (int) (sum % 5);
            switch (remainder) {
                case 4:
                    sb.append('-');
                    sum += 5;
                    break;
                case 3:
                    sb.append('=');
                    sum += 5;
                    break;
                default:
                    sb.append(remainder);
            }
            sum /= 5;
        }
        return sb.reverse().toString();
    }

}