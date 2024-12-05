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

    @Test
    void correctPageNumbers() {
        assertEquals(List.of(75,47,61,53,29), Day5.correctPageNumbers(pageOrderingRules, List.of(75,47,61,53,29)));

        assertEquals(List.of(97,75,47,61,53), Day5.correctPageNumbers(pageOrderingRules, List.of(75,97,47,61,53)));
        assertEquals(List.of(61, 29, 13), Day5.correctPageNumbers(pageOrderingRules, List.of(61,13,29)));
        assertEquals(List.of(97, 75, 47, 29, 13), Day5.correctPageNumbers(pageOrderingRules, List.of(97, 13, 75, 29, 47)));
    }

    @Test
    void part2() {
        assertEquals(123, Day5.part2(pageOrderingRules, updatePageNumbers));
    }
}