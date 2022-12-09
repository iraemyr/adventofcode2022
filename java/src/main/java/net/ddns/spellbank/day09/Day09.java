package net.ddns.spellbank.day09;

import net.ddns.spellbank.utils.InputFile;

public class Day09 {

    public static void main(String[] args) {
        String file = "day09/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 6384
        System.out.println(part2(lines)); // 2734
    }

    public static long part1(String[] lines) {
        var head = new Rope.Point(0, 0);
        var tail = new Rope.Point(0, 0);
        var rope = new Rope(head, tail);
        rope.doMoves(lines);
        return rope.numVisited();
    }

    public static long part2(String[] lines) {
        var rope = new Ropes();
        rope.doMoves(lines);
        return rope.numVisited();
    }

}