package com.akargl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.akargl.utils.InputUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

public class Day9 {
  @Data
  @AllArgsConstructor
  @RequiredArgsConstructor
  protected static class Coordinate {
    private int x = 0;
    private int y = 0;

    public Coordinate clone() {
      return new Coordinate(x, y);
    }
  }

  @Data
  @AllArgsConstructor
  @RequiredArgsConstructor
  protected static class RopeParts {
    private Coordinate head = new Coordinate();
    private Coordinate tail = new Coordinate();

    protected boolean areTouching() {
      return Math.abs(head.getX() - tail.getX()) <= 1 && Math.abs(head.getY() - tail.y) <= 1;
    }

    protected void move(String direction) {
      moveHead(direction);

      if (!areTouching()) {
        moveTail();
      }
    }

    private Coordinate moveHead(String direction) {
      if ("U".equals(direction)) {
        head.y++;
      } else if ("R".equals(direction)) {
        head.x++;
      } else if ("D".equals(direction)) {
        head.y--;
      } else if ("L".equals(direction)) {
        head.x--;
      }

      return head;
    }

    protected Coordinate moveTail() {
      tail.y += Integer.signum(head.y - tail.y);
      tail.x += Integer.signum(head.x - tail.x);

      return tail;
    }
  }

  public static void main(String[] args) throws IOException {
    List<String> inputLines = InputUtils.getInputLines("inputs/d9_1.txt");
    int numVisitedPlaces = moveRope(inputLines);
    System.out.println("Part 1: " + numVisitedPlaces);
  }

  protected static int moveRope(List<String> lines) {
    RopeParts ropeParts = new RopeParts();

    Set<Coordinate> visitedPlaces = new HashSet<>();
    visitedPlaces.add(ropeParts.getTail().clone());

    for (String line : lines) {
      String[] commandParts = line.split(" ");
      for (int i = 0; i < Integer.parseInt(commandParts[1]); i++) {
        ropeParts.move(commandParts[0]);
        visitedPlaces.add(ropeParts.getTail().clone());
      }
    }

    //visualizeTailPositions(visitedPlaces);

    return visitedPlaces.size();
  }

  private static void visualizeTailPositions(Set<Coordinate> tailPositions) {
    Integer maxX = Collections.max(tailPositions.stream().map(c -> c.getX()).toList());
    Integer maxY = Collections.max(tailPositions.stream().map(c -> c.getY()).toList());

    List<List<String>> grid = Collections.nCopies(maxY, Collections.nCopies(maxX, "."));

    for (Coordinate tailPosition : tailPositions) {
      grid.get(tailPosition.getY()).set(tailPosition.getX(), "#");
    }

    Collections.reverse(grid);

    for (List<String> row : grid) {
      System.out.println(String.join("", row));
    }
  }
}
