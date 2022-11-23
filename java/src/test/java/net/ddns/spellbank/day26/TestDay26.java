package net.ddns.spellbank.day26;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.day26.Day26;
import net.ddns.spellbank.utils.InputFile;

class TestDay26 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day01/input1");
        assertEquals(0, Day26.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day01/input1");
        assertEquals(0, Day26.part2(lines));
    }
}