package com.akargl.aoc24;

import com.akargl.aoc24.utils.InputUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    static List<List<Integer>> reports;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        reports = Day2.parseInput(InputUtils.getInputLines("inputs/d2_sample.txt"));
    }

    @Test
    void parseInput() {
        assertEquals(6, reports.size());
        assertEquals(5, reports.getFirst().size());
    }

    @Test
    void isSafeReport() {
        assertTrue(Day2.isSafeReport(List.of(7, 6, 4, 2, 1)));
        assertFalse(Day2.isSafeReport(List.of(1, 2, 7, 8, 9)));
        assertFalse(Day2.isSafeReport(List.of(9, 7, 6, 2, 1)));
        assertFalse(Day2.isSafeReport(List.of(1, 3, 2, 4, 5)));
        assertFalse(Day2.isSafeReport(List.of(8, 6, 4, 4, 1)));
        assertTrue(Day2.isSafeReport(List.of(1, 3, 6, 7, 9)));
    }

    @Test
    void isSafeReportP2() {
        assertTrue(Day2.isSafeReportP2(List.of(7, 6, 4, 2, 1)));
        assertFalse(Day2.isSafeReportP2(List.of(1, 2, 7, 8, 9)));
        assertFalse(Day2.isSafeReportP2(List.of(9, 7, 6, 2, 1)));
        assertTrue(Day2.isSafeReportP2(List.of(1, 3, 2, 4, 5)));
        assertTrue(Day2.isSafeReportP2(List.of(8, 6, 4, 4, 1)));
        assertTrue(Day2.isSafeReportP2(List.of(1, 3, 6, 7, 9)));
    }

    @Test
    void getSafeReportCount() {
        assertEquals(2, Day2.getSafeReportCount(reports, Day2::isSafeReport));
        assertEquals(4, Day2.getSafeReportCount(reports, Day2::isSafeReportP2));
    }


}