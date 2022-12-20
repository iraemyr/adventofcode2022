package net.ddns.spellbank.day20;

import java.util.ArrayList;

import net.ddns.spellbank.utils.InputFile;

public class Day20 {
    record NumAndPos(long num, int pos) {
    }

    public static void main(String[] args) {
        String file = "day20/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 8721
        System.out.println(part2(lines)); // 831878881825
    }

    public static long part1(String[] lines) {
        var orig = new NumAndPos[lines.length];
        var nums = new ArrayList<NumAndPos>();
        NumAndPos zero = null;
        for (int i = 0; i < lines.length; i++) {
            orig[i] = new NumAndPos(Long.parseLong(lines[i]), i);
            nums.add(orig[i]);
            if (orig[i].num == 0)
                zero = orig[i];
        }

        for (var num : orig) {
            if (num.num == 0)
                continue;
            var index = nums.indexOf(num);
            var n = nums.remove(index);
            int newIndex = (int) euclideanMod(index + num.num, nums.size());

            if (newIndex == 0)
                nums.add(n);
            else
                nums.add(newIndex, n);
        }
        long sum = 0;
        var index = nums.indexOf(zero);
        for (int i = 0; i < 3; i++) {
            index = (index + 1000) % nums.size();
            sum += nums.get(index).num;
        }
        return sum;
    }

    public static long part2(String[] lines) {
        var orig = new NumAndPos[lines.length];
        var nums = new ArrayList<NumAndPos>();
        long key = 811589153;
        NumAndPos zero = null;
        for (int i = 0; i < lines.length; i++) {
            orig[i] = new NumAndPos(Integer.parseInt(lines[i]) * key, i);
            nums.add(orig[i]);
            if (orig[i].num == 0)
                zero = orig[i];
        }

        for (int i = 0; i < 10; i++) {
            for (var num : orig) {
                if (num.num == 0)
                    continue;
                var index = nums.indexOf(num);
                var n = nums.remove(index);
                var newIndex = (int) euclideanMod(index + num.num, nums.size());
//            }

                if (newIndex == 0)
                    nums.add(n);
                else
                    nums.add(newIndex, n);
            }
        }

        long sum = 0;
        var index = nums.indexOf(zero);
        for (int i = 0; i < 3; i++) {
            index = (index + 1000) % nums.size();
            sum += nums.get(index).num;
        }
        return sum;
    }

    static long euclideanMod(long a, long n) {
        return n < 0 ? euclideanMod(a, -n) : mod(a, n);
    }

    static long mod(long a, long n) {
        return a < 0 ? (a % n + n) % n : a % n;
    }

}