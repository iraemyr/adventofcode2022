package net.ddns.spellbank.day19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay19 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day19/input1");
        assertEquals(790, Day19.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day19/input1");
        assertEquals(7350, Day19.part2(lines));
    }
}