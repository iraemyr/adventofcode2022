package net.ddns.spellbank.day10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay10 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day10/input1");
        assertEquals(14860, Day10.part1(lines));
    }

    @Test
    void testpart1() {
        String[] lines = InputFile.getLines("day10/test1");
        assertEquals(13140, Day10.part1(lines));
    }
}