package com.akargl.aoc24;

import java.util.List;
import com.akargl.aoc24.utils.InputUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

  static List<List<Integer>> lists;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    lists = Day1.parseInputLists(InputUtils.getInputLines("inputs/d1_sample.txt"));
  }

  @Test
  void parseInputLists() {
    assertEquals(2, lists.size());
    assertEquals(6, lists.get(0).size());
    assertEquals(6, lists.get(1).size());
  }

  @Test
  void p1GetTotalDistance() {
    int totalDistance = Day1.p1GetTotalDistance(lists.get(0), lists.get(1));
    assertEquals(11, totalDistance);
  }

  @Test
  void p2GetSimilarityScore() {
    int similarityScore = Day1.p2GetSimilarityScore(lists.get(0), lists.get(1));
    assertEquals(31, similarityScore);
  }
}