package net.ddns.spellbank.day13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay13 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day13/input1");
        assertEquals(5529, Day13.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day13/input1");
        assertEquals(27690, Day13.part2(lines));
    }

    @Test
    void testpart1() {
        String[] lines = InputFile.getLines("day13/test1");
        assertEquals(13, Day13.part1(lines));
    }

    @Test
    void testpart2() {
        String[] lines = InputFile.getLines("day13/test1");
        assertEquals(140, Day13.part2(lines));
    }
}