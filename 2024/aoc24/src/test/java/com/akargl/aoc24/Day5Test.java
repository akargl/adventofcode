package com.akargl.aoc24;

import com.akargl.aoc24.utils.InputUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {

    static List<List<Integer>> pageOrderingRules;
    static List<List<Integer>> updatePageNumbers;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        pageOrderingRules = Day5.getPageorderingRules(InputUtils.getInputLines("inputs/d5_sample.txt", true));
        updatePageNumbers = Day5.getUpdatePageNumbers(InputUtils.getInputLines("inputs/d5_sample.txt", true));
    }

    @Test
    void getPageorderingRules() {
        assertEquals(21, pageOrderingRules.size());
        assertEquals(2, pageOrderingRules.getFirst().size());
    }

    @Test
    void getUpdatePageNumbers() {
        assertEquals(6, updatePageNumbers.size());
        assertEquals(5, updatePageNumbers.getFirst().size());
    }

    @Test
    void checkPageOrder() {
        assertTrue(Day5.checkPageOrder(47, 53, pageOrderingRules));
        assertTrue(Day5.checkPageOrder(75, 47, pageOrderingRules));

        assertFalse(Day5.checkPageOrder(75, 9999, pageOrderingRules));
        assertFalse(Day5.checkPageOrder(9999, 9999, pageOrderingRules));
        assertFalse(Day5.checkPageOrder(13, 53, pageOrderingRules));
        assertFalse(Day5.checkPageOrder(75, 97, pageOrderingRules));
    }

    @Test
    void part1() {
        assertEquals(143, Day5.part1(pageOrderingRules, updatePageNumbers));
    }
}