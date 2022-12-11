package com.akargl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import com.akargl.utils.InputUtils;

import lombok.Data;

public class Day8 {
  @Data
  protected static class Grid {
    private List<List<Integer>> grid;

    protected Grid(List<String> lines) {
      grid = lines.stream()
          .map(l -> Arrays.stream(l.split(""))
              .map(Integer::parseInt)
              .toList())
          .toList();
    }

    protected int getWidth() {
      return grid.get(0).size();
    }

    protected int getHeight() {
      return grid.size();
    }

    protected List<Integer> getRow(int rowNumber) {
      return grid.get(rowNumber);
    }

    protected List<Integer> getColumn(int columnNumber) {
      return grid.stream().map(row -> row.get(columnNumber)).toList();
    }

    protected List<Integer> getSubRow(int rowNumber, int from, int to) {
      return getRow(rowNumber).subList(from, to);
    }

    protected List<Integer> getSubColumn(int columnNumber, int from, int to) {
      return getColumn(columnNumber).subList(from, to);
    }

    protected Integer getElement(int x, int y) {
      return grid.get(y).get(x);
    }

    protected List<Integer> getTopColumn(int x, int y) {
      return getSubColumn(x, 0, y);
    }

    protected List<Integer> getBottomColumn(int x, int y) {
      return getSubColumn(x, y+1, getHeight());
    }

    protected List<Integer> getLeftRow(int x, int y) {
      return getSubRow(y, 0, x);
    }

    protected List<Integer> getRightRow(int x, int y) {
      return getSubRow(y, x+1, getWidth());
    }
  }

  public static void main(String[] args) throws IOException {
    Grid grid = new Grid(InputUtils.getInputLines("inputs/d8_1.txt"));
    int numberVisibleTrees = findNumberVisibleTrees(grid);

    System.out.println("Part 1: " + numberVisibleTrees);

    Long highestScenicViewScore = findHighestScenicViewScore(grid);
    System.out.println("Part 2: " + highestScenicViewScore);
  }

  protected static int findNumberVisibleTrees(Grid grid) {
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

  protected static boolean isVisible(Grid grid, int x, int y) {
    //border elements are always visible
    if (x == 0 || x == grid.getWidth() -1 || y == 0 || y == grid.getHeight() -1) {
      return true;
    }

   return Stream.of(grid.getTopColumn(x, y), grid.getBottomColumn(x, y), grid.getLeftRow(x, y), grid.getRightRow(x, y))
       .map(Collections::max)
       .anyMatch(max -> max < grid.getElement(x, y));
  }

  protected static Long findHighestScenicViewScore(Grid grid) {
    Long maxScenicViewScore = 0L;

    for (int x = 0; x < grid.getWidth(); x++) {
      for (int y = 0; y < grid.getHeight(); y++) {
        maxScenicViewScore = Math.max(maxScenicViewScore, getScenicViewScore(grid, x, y));
      }
    }

    return maxScenicViewScore;
  }

  protected static Long getScenicViewScore(Grid grid, int x, int y) {
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
