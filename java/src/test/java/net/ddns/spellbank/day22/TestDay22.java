package net.ddns.spellbank.day22;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay22 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day22/input1");
        assertEquals(20494, Day22.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day22/input1");
        assertEquals(55343, Day22.part2(lines));
    }

    @Test
    void testPart1() {
        String[] lines = InputFile.getLines("day22/test1");
        assertEquals(6032, Day22.part1(lines));
    }

    @Test
    void cubeMappingTest() {

        // cube 1 to 3
        assertEquals(new Day22.PosAndHeading(new Day22.Point(50, 150), 2),
                Day22.cubeMapping(new Day22.Point(101, 100), 0));
        assertEquals(new Day22.PosAndHeading(new Day22.Point(1, 150), 2),
                Day22.cubeMapping(new Day22.Point(150, 100), 0));

        // cube 1 to 6
        assertEquals(new Day22.PosAndHeading(new Day22.Point(151, 50), 2),
                Day22.cubeMapping(new Day22.Point(150, 51), 1));
        assertEquals(new Day22.PosAndHeading(new Day22.Point(200, 50), 2),
                Day22.cubeMapping(new Day22.Point(150, 100), 1));

        // cube 2 to 4
        assertEquals(new Day22.PosAndHeading(new Day22.Point(51, 51), 0),
                Day22.cubeMapping(new Day22.Point(101, 1), 3));
        assertEquals(new Day22.PosAndHeading(new Day22.Point(100, 51), 0),
                Day22.cubeMapping(new Day22.Point(101, 50), 3));

        // cube 2 to 5
        assertEquals(new Day22.PosAndHeading(new Day22.Point(50, 51), 0),
                Day22.cubeMapping(new Day22.Point(101, 1), 2));
        assertEquals(new Day22.PosAndHeading(new Day22.Point(1, 51), 0), Day22.cubeMapping(new Day22.Point(150, 1), 2));

        // cube 3 to 1
        assertEquals(new Day22.PosAndHeading(new Day22.Point(150, 51), 3),
                Day22.cubeMapping(new Day22.Point(151, 50), 0));
        assertEquals(new Day22.PosAndHeading(new Day22.Point(150, 100), 3),
                Day22.cubeMapping(new Day22.Point(200, 50), 0));

        // cube 3 to 5
        assertEquals(new Day22.PosAndHeading(new Day22.Point(1, 51), 1), Day22.cubeMapping(new Day22.Point(151, 1), 2));
        assertEquals(new Day22.PosAndHeading(new Day22.Point(1, 100), 1),
                Day22.cubeMapping(new Day22.Point(200, 1), 2));

        // cube 3 to 6
        assertEquals(new Day22.PosAndHeading(new Day22.Point(1, 101), 1),
                Day22.cubeMapping(new Day22.Point(200, 1), 1));
        assertEquals(new Day22.PosAndHeading(new Day22.Point(1, 150), 1),
                Day22.cubeMapping(new Day22.Point(200, 50), 1));

        // cube 4 to 2
        assertEquals(new Day22.PosAndHeading(new Day22.Point(101, 1), 1),
                Day22.cubeMapping(new Day22.Point(51, 51), 2));
        assertEquals(new Day22.PosAndHeading(new Day22.Point(101, 50), 1),
                Day22.cubeMapping(new Day22.Point(100, 51), 2));

        // cube 4 to 6
        assertEquals(new Day22.PosAndHeading(new Day22.Point(50, 101), 3),
                Day22.cubeMapping(new Day22.Point(51, 100), 0));
        assertEquals(new Day22.PosAndHeading(new Day22.Point(50, 150), 3),
                Day22.cubeMapping(new Day22.Point(100, 100), 0));

        // cube 5 to 2
        assertEquals(new Day22.PosAndHeading(new Day22.Point(150, 1), 0), Day22.cubeMapping(new Day22.Point(1, 51), 2));
        assertEquals(new Day22.PosAndHeading(new Day22.Point(101, 1), 0),
                Day22.cubeMapping(new Day22.Point(50, 51), 2));

        // cube 5 to 3
        assertEquals(new Day22.PosAndHeading(new Day22.Point(151, 1), 0), Day22.cubeMapping(new Day22.Point(1, 51), 3));
        assertEquals(new Day22.PosAndHeading(new Day22.Point(200, 1), 0),
                Day22.cubeMapping(new Day22.Point(1, 100), 3));

        // cube 6 to 1
        assertEquals(new Day22.PosAndHeading(new Day22.Point(150, 100), 2),
                Day22.cubeMapping(new Day22.Point(1, 150), 0));
        assertEquals(new Day22.PosAndHeading(new Day22.Point(101, 100), 2),
                Day22.cubeMapping(new Day22.Point(50, 150), 0));

        // cube 6 to 3
        assertEquals(new Day22.PosAndHeading(new Day22.Point(200, 1), 3),
                Day22.cubeMapping(new Day22.Point(1, 101), 3));
        assertEquals(new Day22.PosAndHeading(new Day22.Point(200, 50), 3),
                Day22.cubeMapping(new Day22.Point(1, 150), 3));

        // cube 6 to 4
        assertEquals(new Day22.PosAndHeading(new Day22.Point(51, 100), 2),
                Day22.cubeMapping(new Day22.Point(50, 101), 1));
        assertEquals(new Day22.PosAndHeading(new Day22.Point(100, 100), 2),
                Day22.cubeMapping(new Day22.Point(50, 150), 1));
    }
}