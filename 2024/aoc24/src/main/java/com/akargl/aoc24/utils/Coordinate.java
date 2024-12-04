package com.akargl.aoc24.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Coordinate {
  public int x = 0;
  public int y = 0;

  public Coordinate(Coordinate orig) {
    x = orig.getX();
    y = orig.getY();
  }

  public Coordinate getTop() {
    return new Coordinate(x, y-1);
  }

  public Coordinate getBottom() {
    return new Coordinate(x, y+1);
  }

  public Coordinate getLeft() {
    return new Coordinate(x-1, y);
  }

  public Coordinate getRight() {
    return new Coordinate(x+1, y);
  }

  public boolean isVerticalOrHorizontalNeighbor(Coordinate other) {
    return (other.getX() == x && Math.abs(other.getY() -y) == 1) ||
        (other.getY() == y && Math.abs(other.getX() -x) == 1);
  }
}
