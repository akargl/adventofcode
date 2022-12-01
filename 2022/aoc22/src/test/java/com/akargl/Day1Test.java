package com.akargl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import com.akargl.utils.InputUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

  @Test
  void part1() throws IOException {
    assertEquals(24000, Day1.part1(InputUtils.getInput("inputs/d1_sample.txt")));
  }

  @Test
  void inputToSums() {
    assertEquals(Arrays.asList(3, 3, 9), Day1.inputToSums("1\n2\n\n3\n\n4\n5"));
  }

  @Test
  void part2() throws IOException {
    assertEquals(45000, Day1.part2(InputUtils.getInput("inputs/d1_sample.txt")));
  }
}