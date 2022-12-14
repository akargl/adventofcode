package com.akargl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Stream;
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

    public char getElevation() {
      if ("S".equals(value)) {
        return 'a';
      }

      if ("E".equals(value)) {
        return 'z';
      }

      return value.toCharArray()[0];
    }
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

    GridNode start = grid.getElementsWhere(e -> e.getValue().equals("S")).get(0);

    List<GridNode> shortestPath = findShortestPath(start, grid);

    return -1;
  }

  public static List<GridNode> findShortestPath(GridNode start, Grid<GridNode> grid) {
    Queue<GridNode> queue = new LinkedList<>();
    List<GridNode> path = new ArrayList<>();

    start.setVisited(true);
    queue.add(start);

    Map<GridNode, GridNode> parentMap = new HashMap<>();

    GridNode node = start;
    parentMap.put(start, null);
    while (!queue.isEmpty()) {
      node = queue.remove();

      if ("E".equals(node.getValue())) {
        break;
      }

      List<GridNode> neighbours = getNeighbours(node, grid);
      for (GridNode neighbour : neighbours) {
        if (isVisitable(node, neighbour) && !neighbour.isVisited()) {
          queue.add(neighbour);
          parentMap.put(neighbour, node);
          neighbour.setVisited(true);
        }
      }
    }

    while (node != null) {
      path.add(0, node);
      for (Map.Entry<GridNode, GridNode> entry : parentMap.entrySet()) {
        if (entry.getKey().equals(node)) {
           node = entry.getValue();
           break;
        }
      }
      //node = parentMap.get(node);
    }

    return path;
  }

  public static boolean isVisitable(GridNode current, GridNode target) {
    return current.isVerticalOrHorizontalNeighbor(target) &&
        target != null &&
        (target.getElevation() <= current.getElevation() +1);
  }

  public static List<GridNode> getNeighbours(GridNode node, Grid<GridNode> grid) {
    return Stream.of(node.getTop(), node.getBottom(), node.getLeft(), node.getRight())
        .map(grid::getElement)
        .filter(Objects::nonNull)
        .toList();
  }
}
