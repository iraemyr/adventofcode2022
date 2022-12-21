package net.ddns.spellbank.day21;

import java.util.HashMap;
import java.util.Map;

import net.ddns.spellbank.utils.InputFile;

public class Day21 {

    public static void main(String[] args) {
        String file = "day21/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 168502451381566
        System.out.println(part2(lines, Long.MAX_VALUE / 100)); // 3343167719435
    }

    public static long part1(String[] lines) {
        var monkeys = parseInput(lines);
        return monkeys.get("root").getNumber();
    }

    public static long part2(String[] lines, long high) {
        long low = 0;
        // long high = Long.MAX_VALUE / 100;
        long found = 0;

        // binary search for the correct value
        while (true) {
            long mid = ((high - low) / 2) + low;
            var monkeys = parseInput(lines);
            var root = monkeys.get("root");
            root.setOp('=');
            var me = monkeys.get("humn");
            me.setNumber(mid);
            var diff = root.getNumber();
            if (diff < 0) {
                high = mid;
            } else if (diff > 0) {
                low = mid;
            } else {
                found = mid;
                break;
            }
        }

        // A range of numbers will return as equal, we want the lowest
        for (long i = -7; i < 1; i++) {
            var monkeys = parseInput(lines);
            var root = monkeys.get("root");
            root.setOp('=');
            var me = monkeys.get("humn");
            me.setNumber(found + i);
            if (root.getNumber() == 0)
                return found + i;
        }
        return found;
    }

    private static Map<String, Monkey> parseInput(String[] lines) {
        var map = new HashMap<String, Monkey>();
        for (var line : lines) {
            var fields = line.split(": ");
            var name = fields[0];
            var ops = fields[1].split(" ");
            var m = map.getOrDefault(name, new Monkey(name));
            if (ops.length == 1) {
                m.setNumber(Long.parseLong(ops[0]));
            } else {
                var monkey1Name = (ops[0]);
                var monkey1 = map.getOrDefault(monkey1Name, new Monkey(monkey1Name));
                map.put(monkey1Name, monkey1);
                m.setMonkey1(monkey1);
                m.setOp(ops[1].charAt(0));
                var monkey2Name = (ops[2]);
                var monkey2 = map.getOrDefault(monkey2Name, new Monkey(monkey2Name));
                map.put(monkey2Name, monkey2);
                m.setMonkey2(monkey2);
            }
            map.put(name, m);
        }
        return map;
    }

}