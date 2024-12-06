package com.akargl.aoc24;

import com.akargl.aoc24.utils.InputUtils;
import com.akargl.aoc24.utils.StringGrid;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

}