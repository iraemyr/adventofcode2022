package net.ddns.spellbank.day13;

import java.util.ArrayList;
import java.util.Collections;

import net.ddns.spellbank.utils.InputFile;

public class Day13 {

    public static void main(String[] args) {
        String file = "day13/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 5529
        System.out.println(part2(lines)); // 27690
    }

    public static long part1(String[] lines) {
        int index = 0;
        int pair = 1;
        long sum = 0;
        while (index < lines.length) {
            var first = new Packet(lines[index++]);
            var second = new Packet(lines[index++]);
            if (first.compareTo(second) == -1)
                sum += pair;
            index++;
            pair++;
        }
        return sum;
    }

    public static long part2(String[] lines) {
        var packets = new ArrayList<Packet>();
        for (var line : lines) {
            if (line.trim().isEmpty())
                continue;
            packets.add(new Packet(line));
        }
        packets.add(new Packet("[[2]]"));
        packets.add(new Packet("[[6]]"));
        Collections.sort(packets);
        int code1 = -1;
        int code2 = -1;
        for (int i = 0; i < packets.size(); i++) {
            if (packets.get(i).toString().equals("[[2]]"))
                code1 = i + 1;
            else if (packets.get(i).toString().equals("[[6]]"))
                code2 = i + 1;
            if (code1 != -1 && code2 != -1)
                break;
        }
        return code1 * code2;
    }
}