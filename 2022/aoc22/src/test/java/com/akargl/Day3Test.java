package com.akargl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import com.akargl.utils.InputUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {

  @Test
  void getPrioritySum() throws IOException {
    List<String> inputLines = InputUtils.getInputLines(Path.of("inputs/d3_sample.txt"), false);
    assertEquals(157, Day3.getPrioritySum(inputLines));
  }

  @Test
  void getBadgePrioritySum() throws IOException {
    List<String> inputLines = InputUtils.getInputLines(Path.of("inputs/d3_sample.txt"), false);
    assertEquals(70, Day3.getBadgePrioritySum(inputLines));
  }
}