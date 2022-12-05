package net.ddns.spellbank.day05;

import java.util.ArrayList;
import java.util.Stack;

import net.ddns.spellbank.utils.InputFile;

public class Day05 {

    public static void main(String[] args) {
        String file = "day05/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // TBVFVDZPN
        System.out.println(part2(lines)); // VLCWHTDSZ
    }

    public static String part1(String[] lines) {
        int i = 0;
        while (!lines[i].startsWith(" "))
            i++;
        String line = lines[i].trim();
        int numStacks = line.trim().charAt(line.length() - 1) - '0';
        var stacks = new ArrayList<Stack<Character>>();
        for (int j = 0; j < numStacks; j++)
            stacks.add(new Stack<Character>());
        for (int j = i - 1; j >= 0; j--) {
            int index = 1;
            for (int k = 0; k < numStacks; k++) {
                char c = lines[j].charAt(index);
                if (c != ' ')
                    stacks.get(k).push(lines[j].charAt(index));
                index += 4;
            }
        }

        for (int j = i + 2; j < lines.length; j++) {
            var fields = lines[j].trim().split(" ");
            var num = Integer.parseInt(fields[1]);
            var src = Integer.parseInt(fields[3]) - 1;
            var dest = Integer.parseInt(fields[5]) - 1;
            for (int k = 0; k < num; k++) {
                var source = stacks.get(src);
                stacks.get(dest).push(source.pop());
            }
        }
        String result = "";
        for (var stack : stacks)
            result += stack.peek();
        return result;
    }

    public static String part2(String[] lines) {
        int i = 0;
        while (!lines[i].startsWith(" "))
            i++;
        String line = lines[i].trim();
        int numStacks = line.trim().charAt(line.length() - 1) - '0';
        var stacks = new ArrayList<Stack<Character>>();
        for (int j = 0; j < numStacks; j++)
            stacks.add(new Stack<Character>());
        for (int j = i - 1; j >= 0; j--) {
            int index = 1;
            for (int k = 0; k < numStacks; k++) {
                char c = lines[j].charAt(index);
                if (c != ' ')
                    stacks.get(k).push(lines[j].charAt(index));
                index += 4;
            }
        }

        var tmp = new Stack<Character>();
        for (int j = i + 2; j < lines.length; j++) {
            var fields = lines[j].trim().split(" ");
            var num = Integer.parseInt(fields[1]);
            var src = Integer.parseInt(fields[3]) - 1;
            var dest = Integer.parseInt(fields[5]) - 1;
            for (int k = 0; k < num; k++) {
                var source = stacks.get(src);
                tmp.push(source.pop());
            }
            while (!tmp.isEmpty())
                stacks.get(dest).push(tmp.pop());
        }
        String result = "";
        for (var stack : stacks)
            result += stack.peek();
        return result;
    }

}