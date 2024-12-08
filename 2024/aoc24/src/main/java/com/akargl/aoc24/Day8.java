package com.akargl.aoc24;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import com.akargl.aoc24.utils.Coordinate;
import com.akargl.aoc24.utils.InputUtils;
import com.akargl.aoc24.utils.StringGrid;

public class Day8 {

  private static final String EMPTY_FIELD = ".";

  public static void main(String[] args) throws IOException {
    List<String> inputLines = InputUtils.getInputLines("inputs/d8_1.txt");

    StringGrid grid = new StringGrid(inputLines);

    Map<String, List<Coordinate>> antennaPositions = grid.getCoordinatesWhere(x -> !EMPTY_FIELD.equals(x)).stream()
        .collect(Collectors.groupingBy(grid::getElement));

    Set<Coordinate> antinodesCoordinates = new HashSet<>();

    antennaPositions.forEach((antenna, coords) -> {
      Set<Coordinate> antinodes = getAntinodes(coords, false, grid);
      antinodesCoordinates.addAll(antinodes);
    });

    System.out.println("Part1: " + antinodesCoordinates.size());

    Set<Coordinate> antinodesCoordinates2 = new HashSet<>();

    antennaPositions.forEach((antenna, coords) -> {
      Set<Coordinate> antinodes = getAntinodes(coords, true, grid);
      antinodesCoordinates2.addAll(antinodes);
    });

    System.out.println("Part2: " + antinodesCoordinates2.size());
  }

  private static boolean isInGrid(Coordinate an, StringGrid grid) {
    return an.getX() >= 0 && an.getX() < grid.getWidth()
        && an.getY() >= 0 && an.getY() < grid.getHeight();
  }

  protected static Set<Coordinate> getAntinodes(List<Coordinate> antennaCoords, boolean repeat, StringGrid grid) {
    Set<Coordinate> antinodesCoordinates = new HashSet<>();

    for (int i = 0; i < antennaCoords.size(); i++) {
      for (int j = 0; j < antennaCoords.size(); j++) {
        if (i == j) {
          continue;
        }

        Coordinate a = antennaCoords.get(i);
        Coordinate b = antennaCoords.get(j);

        int xDiff = a.getX() - b.getX();
        int yDiff = a.getY() - b.getY();

        antinodesCoordinates.addAll(generateAntinodesForOneAntenna(a, xDiff, yDiff, grid, repeat));
        antinodesCoordinates.addAll(generateAntinodesForOneAntenna(b, -xDiff, -yDiff, grid, repeat));
      }
    }

    return antinodesCoordinates;
  }

  protected static Set<Coordinate> generateAntinodesForOneAntenna(Coordinate antenna, int xDiff, int yDiff, StringGrid grid, boolean repeat) {
    Set<Coordinate> antinodesCoordinates = new HashSet<>();

    if (!repeat) {
      Coordinate antinode = new Coordinate(antenna.getX() + xDiff, antenna.getY() + yDiff);
      if (isInGrid(antinode, grid)) {
        antinodesCoordinates.add(antinode);
      }
    } else {
      for (int i = 0;; i++) {
        Coordinate antinode = new Coordinate(antenna.getX() + xDiff *i, antenna.getY() + yDiff *i);

        if (!isInGrid(antinode, grid)) {
          break;
        }
        antinodesCoordinates.add(antinode);
      }
    }

    return antinodesCoordinates;
  }
}
