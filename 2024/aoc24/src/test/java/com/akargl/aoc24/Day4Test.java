package com.akargl.aoc24;

import com.akargl.aoc24.utils.StringGrid;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    @Test
    void inputToGrid() throws IOException {
        StringGrid grid = Day4.inputToGrid("inputs/d4_sample.txt");
        assertEquals(10, grid.getWidth());
        assertEquals(10, grid.getHeight());
    }

    @Test
    void countSubstringOccurence() {
        assertEquals(3, Day4.countSubstringOccurence("SDSDXMASXMASsdsdsdXMAS", "XMAS"));
        assertEquals(0, Day4.countSubstringOccurence("", "XMAS"));
        assertEquals(0, Day4.countSubstringOccurence("SDSDXMASXMASsdsdsdXMAS", "NOT"));
    }
}