package com.akargl.aoc24;

import com.akargl.aoc24.utils.Coordinate;
import com.akargl.aoc24.utils.InputUtils;
import com.akargl.aoc24.utils.StringGrid;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {
    static StringGrid grid;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        grid = new StringGrid(InputUtils.getInputLines("inputs/d6_sample.txt"));
    }

    @Test
    void gridParsing() {
        assertEquals(10, grid.getWidth());
        assertEquals(10, grid.getHeight());
    }

    @Test
    void getGuardStartPosition() {
        Day6.GuardPosition guardStartPosition = Day6.getGuardStartPosition(grid);
        assertEquals(new Day6.GuardPosition(new Coordinate(4, 6), Day6.Direction.UP), guardStartPosition);
    }

    @Test
    void part1() {
        Day6.GuardPosition guardStartPosition = Day6.getGuardStartPosition(grid);
        assertEquals(41, Day6.part1(guardStartPosition, grid));
    }

    @Test
    void part2() throws IOException {
        Day6.GuardPosition guardStartPosition = Day6.getGuardStartPosition(grid);
        assertEquals(6, Day6.part2(guardStartPosition, grid, InputUtils.getInputLines("inputs/d6_sample.txt")));
    }
}