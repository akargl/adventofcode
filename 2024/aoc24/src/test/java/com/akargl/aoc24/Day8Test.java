package com.akargl.aoc24;

import java.util.List;
import java.util.Set;
import com.akargl.aoc24.utils.Coordinate;
import com.akargl.aoc24.utils.InputUtils;
import com.akargl.aoc24.utils.StringGrid;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {
  static StringGrid grid;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    grid = new StringGrid(InputUtils.getInputLines("inputs/d8_sample.txt"));
  }

  @Test
  void getAntinodes() {
    Set<Coordinate> antinodes = Day8.getAntinodes(List.of(new Coordinate(5, 4), new Coordinate(6, 6)), false, grid);
    assertEquals(2, antinodes.size());
    assertEquals(Set.of(new Coordinate(4, 2), new Coordinate(7, 8)), antinodes);
  }
}