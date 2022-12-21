package net.ddns.spellbank.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay21 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day21/input1");
        assertEquals(168502451381566L, Day21.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day21/input1");
        assertEquals(3343167719435L, Day21.part2(lines, Long.MAX_VALUE / 100));
    }

    @Test
    void testpart1() {
        String[] lines = InputFile.getLines("day21/test1");
        assertEquals(152, Day21.part1(lines));
    }
}