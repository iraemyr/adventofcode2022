package net.ddns.spellbank.day18;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay18 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day18/input1");
        assertEquals(4322, Day18.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day18/input1");
        assertEquals(2516, Day18.part2(lines));
    }
}