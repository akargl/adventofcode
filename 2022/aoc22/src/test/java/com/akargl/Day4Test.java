package com.akargl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import com.akargl.utils.InputUtils;
import org.junit.jupiter.api.Test;

import static com.akargl.Day4.getAllSectionBoundaries;
import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

  @Test
  void parseSectionBoundaries() {
    assertEquals(Arrays.asList(2, 4, 6, 8), Day4.parseSectionBoundaries("2-4,6-8"));
  }

  @Test
  void getNumCompletelyOverlappingSections() throws IOException {
    List<List<Integer>> sectionBoundaries = getAllSectionBoundaries(InputUtils.getInputLines(Path.of("inputs/d4_sample.txt"), false));
    assertEquals(2, Day4.getNumCompletelyOverlappingSections(sectionBoundaries));
  }

  @Test
  void getNumOverlappingSections() throws IOException {
    List<List<Integer>> sectionBoundaries = getAllSectionBoundaries(InputUtils.getInputLines(Path.of("inputs/d4_sample.txt"), false));
    assertEquals(4, Day4.getNumOverlappingSections(sectionBoundaries));
  }
}