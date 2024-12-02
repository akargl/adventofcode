package com.akargl.aoc24;

import com.akargl.aoc24.utils.InputUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    static List<List<Integer>> reports;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        reports = Day2.parseInput(InputUtils.getInputLines("inputs/d2_sample.txt"));
    }

    @Test
    void parseInput() throws IOException {
        assertEquals(6, reports.size());
        assertEquals(5, reports.getFirst().size());
    }

    @Test
    void p1GetSafeReportCount() {
        long safeReportCount = Day2.p1GetSafeReportCount(reports);
        assertEquals(2, safeReportCount);
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
}