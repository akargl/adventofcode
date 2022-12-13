package com.akargl;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.akargl.utils.Coordinate;
import com.akargl.utils.Grid;
import com.akargl.utils.InputUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class Day12 {
  @Data
  @AllArgsConstructor
  @RequiredArgsConstructor
  public static class GridNode extends Coordinate {
    @NonNull
    String value;
    boolean visited = false;
    int distance;
  }

  public static void main(String[] args) throws IOException {
    List<String> inputLines = InputUtils.getInputLines("inputs/d12_sample.txt");
    int minDistance = getMinDistance(inputLines);
    System.out.println("Part 1: " + minDistance);
  }

  public static int getMinDistance(List<String> lines) {
    Grid<GridNode> grid = new Grid<>(lines.stream()
        .map(l -> Arrays.stream(l.split(""))
            .map(GridNode::new)
            .toList())
        .toList());

    grid.forEach((node, coord) -> {
      node.setX(coord.getX());
      node.setY(coord.getY());
    });

    grid.getElementsWhere(e -> e.getValue().equals("S"));

    return -1;
  }
}
