package net.ddns.spellbank.day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.ddns.spellbank.utils.InputFile;

public class Day11 {

    public static void main(String[] args) {
        String file = "day11/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 100345
        System.out.println(part2(lines)); // 28537348205
    }

    public static long part1(String[] lines) {
        var monkeys = parseMonkeys(lines);
        Monkey.setMonkeys(monkeys);

        for (int i = 0; i < 20; i++) {
            for (var monkey : monkeys) {
                monkey.doOp();
            }
        }

        var inspections = new ArrayList<Long>();
        for (var monkey : monkeys) {
            inspections.add(monkey.inspected);
        }
        Collections.sort(inspections, Collections.reverseOrder());
        return inspections.get(0) * inspections.get(1);
    }

    public static long part2(String[] lines) {
        var monkeys = parseMonkeys(lines);
        Monkey.setMonkeys(monkeys);

        for (int i = 0; i < 10000; i++) {
            for (var monkey : monkeys) {
                monkey.doOpNoReduction();
            }
        }

        var inspections = new ArrayList<Long>();
        for (var monkey : monkeys) {
            inspections.add(monkey.inspected);
        }
        Collections.sort(inspections, Collections.reverseOrder());
        return inspections.get(0) * inspections.get(1);
    }

    private static List<Monkey> parseMonkeys(String[] lines) {
        int index = 1;
        var monkeys = new ArrayList<Monkey>();
        while (index < lines.length) {
            var monkey = new Monkey();
            var fields = lines[index++].split(": ");
            var items = fields[1].split(", ");
            for (var item : items)
                monkey.addItem(Long.parseLong(item));
            fields = lines[index++].split(" = ");
            items = fields[1].split(" ");
            monkey.setMul(items[1].equals("*"));
            if (items[2].equals("old"))
                monkey.setOpVal(-1);
            else
                monkey.setOpVal(Integer.parseInt(items[2]));
            fields = lines[index++].split("by ");
            monkey.setDiv(Integer.parseInt(fields[1]));
            fields = lines[index++].split("monkey ");
            monkey.setTrueDest(Integer.parseInt(fields[1]));
            fields = lines[index++].split("monkey ");
            monkey.setFalseDest(Integer.parseInt(fields[1]));
            monkeys.add(monkey);
            index += 2;
        }
        return monkeys;
    }
}