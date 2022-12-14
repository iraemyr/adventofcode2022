package net.ddns.spellbank.day14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay14 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day14/input1");
        assertEquals(638, Day14.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day14/input1");
        assertEquals(31722, Day14.part2(lines));
    }

    @Test
    void testpart1() {
        String[] lines = InputFile.getLines("day14/test1");
        assertEquals(24, Day14.part1(lines));
    }

    @Test
    void testpart2() {
        String[] lines = InputFile.getLines("day14/test1");
        assertEquals(93, Day14.part2(lines));
    }
}