package net.ddns.spellbank.day24;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay24 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day24/input1");
        assertEquals(314, Day24.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day24/input1");
        assertEquals(896, Day24.part2(lines));
    }

    @Test
    void testpart1() {
        String[] lines = InputFile.getLines("day24/test1");
        assertEquals(18, Day24.part1(lines));
    }

    @Test
    void testpart2() {
        String[] lines = InputFile.getLines("day24/test1");
        assertEquals(54, Day24.part2(lines));
    }
}