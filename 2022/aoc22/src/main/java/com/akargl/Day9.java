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

    public Coordinate(Coordinate orig) {
      x = orig.getX();
      y = orig.getY();
    }
  }

  @Data
  @AllArgsConstructor
  protected static class RopeParts {
    private List<Coordinate> knots;

    public RopeParts(int numKnots) {
      this.knots = new ArrayList<>();
      for (int i = 0; i < numKnots; i++) {
        knots.add(new Coordinate());
      }
    }

    protected static boolean areTouching(Coordinate c1, Coordinate c2) {
      return Math.abs(c1.getX() - c2.getX()) <= 1 && Math.abs(c1.getY() - c2.y) <= 1;
    }

    protected void move(String direction) {
      moveHead(direction);

      for (int i = 0; i < knots.size()-1; i++) {
        if (!areTouching(knots.get(i), knots.get(i+1))) {
          moveKnot(knots.get(i), knots.get(i+1));
        }
      }
    }

    private Coordinate moveHead(String direction) {
      if ("U".equals(direction)) {
        knots.get(0).y++;
      } else if ("R".equals(direction)) {
        knots.get(0).x++;
      } else if ("D".equals(direction)) {
        knots.get(0).y--;
      } else if ("L".equals(direction)) {
        knots.get(0).x--;
      }

      return knots.get(0);
    }

    protected static Coordinate moveKnot(Coordinate leader, Coordinate follower) {
      follower.y += Integer.signum(leader.y - follower.y);
      follower.x += Integer.signum(leader.x - follower.x);

      return follower;
    }
  }

  public static void main(String[] args) throws IOException {
    List<String> inputLines = InputUtils.getInputLines("inputs/d9_1.txt");
    int numVisitedPlaces = moveRope(inputLines, 2);
    System.out.println("Part 1: " + numVisitedPlaces);

    int numVisitedPlacesP2 = moveRope(inputLines, 10);
    System.out.println("Part 2: " + numVisitedPlacesP2);
  }

  protected static int moveRope(List<String> lines, int numKnots) {
    RopeParts ropeParts = new RopeParts(numKnots);

    Set<Coordinate> visitedPlaces = new HashSet<>();
    visitedPlaces.add(new Coordinate(ropeParts.getKnots().get(numKnots-1)));

    for (String line : lines) {
      String[] commandParts = line.split(" ");
      for (int i = 0; i < Integer.parseInt(commandParts[1]); i++) {
        ropeParts.move(commandParts[0]);
        visitedPlaces.add(new Coordinate(ropeParts.getKnots().get(numKnots-1)));
      }
    }

    return visitedPlaces.size();
  }
}
