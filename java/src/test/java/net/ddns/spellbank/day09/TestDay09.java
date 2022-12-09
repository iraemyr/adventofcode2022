package net.ddns.spellbank.day09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay09 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day09/input1");
        assertEquals(6384, Day09.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day09/input1");
        assertEquals(2734, Day09.part2(lines));
    }

    @Test
    void testPart1() {
        String[] lines = InputFile.getLines("day09/test1");
        assertEquals(13, Day09.part1(lines));
    }

    @Test
    void testPart2() {
        String[] lines = InputFile.getLines("day09/test2");
        assertEquals(36, Day09.part2(lines));
    }
}