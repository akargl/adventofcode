package com.akargl;

import java.io.IOException;
import java.util.List;
import com.akargl.utils.InputUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

  @BeforeEach
  void setUp() {
  }

  @Test
  void areTouching() {
    assertEquals(true, new Day9.RopeParts(new Day9.Coordinate(1,1), new Day9.Coordinate(1, 1)).areTouching());
    assertEquals(true, new Day9.RopeParts(new Day9.Coordinate(1,1), new Day9.Coordinate(1, 0)).areTouching());
    assertEquals(true, new Day9.RopeParts(new Day9.Coordinate(1,1), new Day9.Coordinate(0, 1)).areTouching());
    assertEquals(true, new Day9.RopeParts(new Day9.Coordinate(1,1), new Day9.Coordinate(0, 0)).areTouching());

    assertEquals(false, new Day9.RopeParts(new Day9.Coordinate(1,2), new Day9.Coordinate(0, 0)).areTouching());
  }

  @Test
  void moveTail() {
    assertEquals(new Day9.Coordinate(2, 1),
        new Day9.RopeParts(new Day9.Coordinate(2, 2), new Day9.Coordinate(2, 0)).moveTail());
    assertEquals(new Day9.Coordinate(2, 3),
        new Day9.RopeParts(new Day9.Coordinate(2, 2), new Day9.Coordinate(2, 4)).moveTail());
    assertEquals(new Day9.Coordinate(1, 2),
        new Day9.RopeParts(new Day9.Coordinate(2, 2), new Day9.Coordinate(0, 2)).moveTail());

    //diagonal
    assertEquals(new Day9.Coordinate(1, 1),
        new Day9.RopeParts(new Day9.Coordinate(2, 2), new Day9.Coordinate(0, 0)).moveTail());
    assertEquals(new Day9.Coordinate(3, 3),
        new Day9.RopeParts(new Day9.Coordinate(2, 2), new Day9.Coordinate(4, 4)).moveTail());
  }

  @Test
  void moveRope() throws IOException {
    List<String> inputLines = InputUtils.getInputLines("inputs/d9_sample.txt");
    Day9.moveRope(inputLines);
  }
}