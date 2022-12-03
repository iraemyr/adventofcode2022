package net.ddns.spellbank.day03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay03 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day03/input1");
        assertEquals(8493, Day03.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day03/input1");
        assertEquals(2552, Day03.part2(lines));
    }
}