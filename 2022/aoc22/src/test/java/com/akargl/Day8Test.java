package com.akargl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import com.akargl.utils.InputUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {

  @Test
  void parseInput() throws IOException {
    Day8.Grid grid = new Day8.Grid(InputUtils.getInputLines("inputs/d8_sample.txt"));

    assertEquals(5, grid.getWidth());
    assertEquals(5, grid.getHeight());

    assertEquals(Arrays.asList(2, 5, 5, 1, 2), grid.getRow(1));
    assertEquals(Arrays.asList(3, 5, 3, 5, 3), grid.getColumn(2));

    assertEquals(Arrays.asList(3, 0, 3), grid.getSubRow(0, 0,3));
    assertEquals(Arrays.asList(0, 3), grid.getSubRow(0, 1,3));
    assertEquals(Arrays.asList(3), grid.getSubRow(0, 4,5));
    assertEquals(Arrays.asList(2), grid.getSubRow(1, 4,5));

    assertEquals(Arrays.asList(0, 5), grid.getSubColumn(1, 0,2));
    assertEquals(Arrays.asList(), grid.getSubColumn(1, 0,0));
    assertEquals(Arrays.asList(), grid.getSubColumn(1, 5, 5));

    assertEquals(2, grid.getElement(0,1));
  }

  @Test
  void isVisible() throws IOException {
    Day8.Grid grid = new Day8.Grid(InputUtils.getInputLines("inputs/d8_sample.txt"));

    assertEquals(true, Day8.isVisible(grid, 0, 0));
    assertEquals(true, Day8.isVisible(grid, 2, 4));
    assertEquals(true, Day8.isVisible(grid, 1, 1));
    assertEquals(false, Day8.isVisible(grid, 3, 1));
  }

  @Test
  void findNumberVisibleTrees() throws IOException {
    Day8.Grid grid = new Day8.Grid(InputUtils.getInputLines("inputs/d8_sample.txt"));
    assertEquals(21, Day8.findNumberVisibleTrees(grid));
  }

  @Test
  void findHighestScenicViewScore() throws IOException {
    Day8.Grid grid = new Day8.Grid(InputUtils.getInputLines("inputs/d8_sample.txt"));
    assertEquals(8, Day8.findHighestScenicViewScore(grid));
  }

  @Test
  void getScenicViewScore() throws IOException {
    Day8.Grid grid = new Day8.Grid(InputUtils.getInputLines("inputs/d8_sample.txt"));
    assertEquals(8, Day8.getScenicViewScore(grid, 2, 3));
    assertEquals(4, Day8.getScenicViewScore(grid, 2, 1));
  }
}