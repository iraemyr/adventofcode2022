package net.ddns.spellbank.day16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay16 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day16/input1");
        assertEquals(0, Day16.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day16/input1");
        assertEquals(0, Day16.part2(lines));
    }
}