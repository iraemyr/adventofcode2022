package net.ddns.spellbank.day11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay11 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day11/input1");
        assertEquals(100345, Day11.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day11/input1");
        assertEquals(28537348205L, Day11.part2(lines));
    }

    @Test
    void testpart1() {
        String[] lines = InputFile.getLines("day11/test1");
        assertEquals(10605, Day11.part1(lines));
    }

    @Test
    void testpart2() {
        String[] lines = InputFile.getLines("day11/test1");
        assertEquals(2713310158L, Day11.part2(lines));
    }
}