package net.ddns.spellbank.day17;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay17 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day17/input1");
        assertEquals(3153, Day17.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day17/input1");
        assertEquals(1553665689155L, Day17.part2(lines));
    }

    @Test
    void testpart1() {
        String[] lines = InputFile.getLines("day17/test1");
        assertEquals(3068, Day17.part1(lines));
    }
}