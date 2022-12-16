package net.ddns.spellbank.day15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay15 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day15/input1");
        assertEquals(4907780, Day15.part1(lines, 2000000));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day15/input1");
        assertEquals(13639962836448L, Day15.part2parallel(lines, 4000000));
    }

    @Test
    void testpart1() {
        String[] lines = InputFile.getLines("day15/test1");
        assertEquals(26, Day15.part1(lines, 10));
    }

    @Test
    void testpart2() {
        String[] lines = InputFile.getLines("day15/test1");
        assertEquals(56000011, Day15.part2parallel(lines, 20));
    }
}