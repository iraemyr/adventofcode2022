package net.ddns.spellbank.day20;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay20 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day20/input1");
        assertEquals(8721, Day20.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day20/input1");
        assertEquals(831878881825L, Day20.part2(lines));
    }

    @Test
    void testpart1() {
        String[] lines = InputFile.getLines("day20/test1");
        assertEquals(3, Day20.part1(lines));
    }

    @Test
    void testpart2() {
        String[] lines = InputFile.getLines("day20/test1");
        assertEquals(1623178306L, Day20.part2(lines));
    }
}