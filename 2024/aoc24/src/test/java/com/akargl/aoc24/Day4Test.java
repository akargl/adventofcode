package com.akargl.aoc24;

import com.akargl.aoc24.utils.Coordinate;
import com.akargl.aoc24.utils.InputUtils;
import com.akargl.aoc24.utils.StringGrid;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    static StringGrid grid;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        grid = Day4.inputToGrid("inputs/d4_sample.txt");
    }

    @Test
    void inputToGrid() throws IOException {
        assertEquals(10, grid.getWidth());
        assertEquals(10, grid.getHeight());
    }


    @Test
    void findInDirection() {
        assertTrue(Day4.findInDirection(grid, new Coordinate(5, 0), new ArrayList<>(List.of("X", "M", "A", "S")), 1, 0));
        assertTrue(Day4.findInDirection(grid, new Coordinate(5, 9), new ArrayList<>(List.of("X", "M", "A", "S")), 1, -1));

        assertFalse(Day4.findInDirection(grid, new Coordinate(1, 9), new ArrayList<>(List.of("X", "M", "A", "S")), 1, 1));
    }

    @Test
    void findXmas() {
        assertEquals(9, Day4.findXmas(grid));
    }

    @Test
    void findInGrid() {
        assertEquals(18, Day4.findInGrid(grid, "XMAS"));
    }
}