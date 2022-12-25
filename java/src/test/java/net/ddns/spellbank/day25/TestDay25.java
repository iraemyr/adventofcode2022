package net.ddns.spellbank.day25;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay25 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day25/input1");
        assertEquals("2=20---01==222=0=0-2", Day25.part1(lines));
    }

    @Test
    void testpart1() {
        String[] lines = InputFile.getLines("day25/test1");
        assertEquals("2=-1=0", Day25.part1(lines));
    }
}