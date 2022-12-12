package com.akargl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import com.akargl.utils.Grid;
import com.akargl.utils.InputUtils;
import com.akargl.utils.IntegerGrid;

public class Day8 {

  public static void main(String[] args) throws IOException {
    IntegerGrid grid = new IntegerGrid(InputUtils.getInputLines("inputs/d8_1.txt"));
    int numberVisibleTrees = findNumberVisibleTrees(grid);

    System.out.println("Part 1: " + numberVisibleTrees);

    Long highestScenicViewScore = findHighestScenicViewScore(grid);
    System.out.println("Part 2: " + highestScenicViewScore);
  }

  protected static int findNumberVisibleTrees(IntegerGrid grid) {
    int numVisible = 0;

    for (int x = 0; x < grid.getWidth(); x++) {
      for (int y = 0; y < grid.getHeight(); y++) {
        if (isVisible(grid, x, y)) {
          numVisible += 1;
        }
      }
    }

    return numVisible;
  }

  protected static boolean isVisible(IntegerGrid grid, int x, int y) {
    //border elements are always visible
    if (x == 0 || x == grid.getWidth() -1 || y == 0 || y == grid.getHeight() -1) {
      return true;
    }

   return Stream.of(grid.getTopColumn(x, y), grid.getBottomColumn(x, y), grid.getLeftRow(x, y), grid.getRightRow(x, y))
       .map(Collections::max)
       .anyMatch(max -> max < grid.getElement(x, y));
  }

  protected static Long findHighestScenicViewScore(IntegerGrid grid) {
    Long maxScenicViewScore = 0L;

    for (int x = 0; x < grid.getWidth(); x++) {
      for (int y = 0; y < grid.getHeight(); y++) {
        maxScenicViewScore = Math.max(maxScenicViewScore, getScenicViewScore(grid, x, y));
      }
    }

    return maxScenicViewScore;
  }

  protected static Long getScenicViewScore(IntegerGrid grid, int x, int y) {
    List<Integer> topColumn = new ArrayList<>(grid.getTopColumn(x, y));
    Collections.reverse(topColumn);
    List<Integer> leftRow = new ArrayList<>(grid.getLeftRow(x, y));
    Collections.reverse(leftRow);

   return Stream.of(topColumn, grid.getBottomColumn(x, y), leftRow, grid.getRightRow(x, y))
        .map(l -> {
          long count = l.stream().takeWhile(tree -> tree < grid.getElement(x, y)).count();
          if (count == l.size()) {
            return count;
          }
          return count+1;
        })
       .reduce(1L, (p, c) -> p * c);
  }
}
