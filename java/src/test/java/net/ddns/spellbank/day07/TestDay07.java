package net.ddns.spellbank.day07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay07 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day07/input1");
        assertEquals(1427048, Day07.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day07/input1");
        assertEquals(2940614, Day07.part2(lines));
    }

    @Test
    void part1Sample() {
        String[] lines = InputFile.getLines("day07/test1");
        assertEquals(95437, Day07.part1(lines));
    }

    @Test
    void part2Sample() {
        String[] lines = InputFile.getLines("day07/test1");
        assertEquals(24933642, Day07.part2(lines));
    }
}