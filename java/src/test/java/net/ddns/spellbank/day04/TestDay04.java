package net.ddns.spellbank.day04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay04 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day04/input1");
        assertEquals(448, Day04.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day04/input1");
        assertEquals(794, Day04.part2(lines));
    }
}