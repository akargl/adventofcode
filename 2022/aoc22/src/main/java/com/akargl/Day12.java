package com.akargl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import com.akargl.utils.Coordinate;
import com.akargl.utils.InputUtils;
import com.akargl.utils.StringGrid;

public class Day12 {
  public static void main(String[] args) throws IOException {
    List<String> inputLines = InputUtils.getInputLines("inputs/d12_1.txt");
    int minDistance = part1(inputLines);
    System.out.println("Part 1: " + minDistance);
  }

  public static Integer part1(List<String> lines) {
    StringGrid grid = new StringGrid(lines);

    Coordinate start = grid.findFirstCoordinates("S");

    Integer minDistance = getMinDistance(start, grid, new HashSet<>());

    return minDistance;
  }

  public static Integer getMinDistance(Coordinate currentCoords, StringGrid grid, Set<Coordinate> visited) {
    visited.add(currentCoords);

    if ("E".equals(grid.getElement(currentCoords))) {
      return 0;
    }

    Integer minDistance = Stream.of(currentCoords.getTop(), currentCoords.getRight(), currentCoords.getBottom(), currentCoords.getLeft())
        .filter(c -> grid.getElement(c) != null)
        .filter(c -> isVisitable(currentCoords, c, grid))
        .filter(c -> !visited.contains(c))
        .map(c -> getMinDistance(c, grid, visited))
        .filter(Objects::nonNull)
        .map(d -> d + 1)
        .min(Integer::compare)
        .orElse(null);

    return minDistance;
  }

  public static boolean isVisitable(Coordinate current, Coordinate target, StringGrid grid) {
    String targetElement = grid.getElement(target);
    return current.isVerticalOrHorizontalNeighbor(target) &&
        targetElement != null &&
        (getElevation(targetElement) <= getElevation(grid.getElement(current)) +1);
  }

  protected static char getElevation(String node) {
    if ("S".equals(node)) {
      return 'a';
    }

    if ("E".equals(node)) {
      return 'z';
    }

    return node.toCharArray()[0];
  }
}
