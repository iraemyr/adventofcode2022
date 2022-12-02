package net.ddns.spellbank.day02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay02 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day02/input1");
        assertEquals(12679, Day02.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day02/input1");
        assertEquals(14470, Day02.part2(lines));
    }
}