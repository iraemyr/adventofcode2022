package net.ddns.spellbank.day23;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay23 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day23/input1");
        assertEquals(3925, Day23.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day23/input1");
        assertEquals(903, Day23.part2(lines));
    }

    @Test
    void testpart1() {
        String[] lines = InputFile.getLines("day23/test1");
        assertEquals(110, Day23.part1(lines));
    }

    @Test
    void testpart2() {
        String[] lines = InputFile.getLines("day23/test1");
        assertEquals(20, Day23.part2(lines));
    }
}