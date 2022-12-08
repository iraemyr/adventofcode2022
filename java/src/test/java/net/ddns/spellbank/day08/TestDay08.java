package net.ddns.spellbank.day08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay08 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day08/input1");
        assertEquals(1801, Day08.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day08/input1");
        assertEquals(209880, Day08.part2(lines));
    }

    @Test
    void testpart1() {
        String[] lines = InputFile.getLines("day08/test1");
        assertEquals(21, Day08.part1(lines));
    }

    @Test
    void testpart2() {
        String[] lines = InputFile.getLines("day08/test1");
        assertEquals(8, Day08.part2(lines));
    }
}