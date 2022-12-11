package com.akargl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.akargl.utils.InputUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

  @BeforeEach
  void setUp() {
  }

  @Test
  void getRegisterXValuesPerCycle() {
    List<Integer> xValuesPerCycle = Day10.getRegisterXValuesAfterCycle(Arrays.asList("noop", "addx 3", "addx -5"));
    assertEquals(1, xValuesPerCycle.get(0));
    assertEquals(-1, xValuesPerCycle.get(4));
  }

  @Test
  void part1() throws IOException {
    List<String> inputLines = InputUtils.getInputLines("inputs/d10_sample.txt");
    assertEquals(13140, Day10.part1(inputLines));
  }
}