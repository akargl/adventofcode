package com.akargl.aoc24;

import com.akargl.aoc24.utils.InputUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {

    static String input;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        input = InputUtils.getInput("inputs/d3_sample.txt");
    }

    @Test
    void parseInputToMultiplications() {
    }

    @Test
    void p1CalcResult() {
        List<List<Integer>> multiplications = Day3.parseInputToMultiplications(input);
        int p1CalcResult = Day3.p1CalcResult(multiplications);
        assertEquals(161, p1CalcResult);
    }

    @Test
    void p2CalcResult() {
        int p2CalcResult = Day3.p2CalcResult("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))");
        assertEquals(48, p2CalcResult);
    }
}