package com.akargl;

import java.io.IOException;
import java.util.List;
import com.akargl.utils.Coordinate;
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
    assertTrue(Day9.RopeParts.areTouching(new Coordinate(1, 1), new Coordinate(1, 1)));
    assertTrue(Day9.RopeParts.areTouching(new Coordinate(1, 1), new Coordinate(1, 0)));
    assertTrue(Day9.RopeParts.areTouching(new Coordinate(1, 1), new Coordinate(0, 1)));
    assertTrue(Day9.RopeParts.areTouching(new Coordinate(1, 1), new Coordinate(0, 0)));

    assertFalse(Day9.RopeParts.areTouching(new Coordinate(1, 2), new Coordinate(0, 0)));
  }

  @Test
  void moveTail() {
    assertEquals(new Coordinate(2, 1),
        Day9.RopeParts.moveKnot(new Coordinate(2, 2), new Coordinate(2, 0)));
    assertEquals(new Coordinate(2, 3),
        Day9.RopeParts.moveKnot(new Coordinate(2, 2), new Coordinate(2, 4)));
    assertEquals(new Coordinate(1, 2),
        Day9.RopeParts.moveKnot(new Coordinate(2, 2), new Coordinate(0, 2)));

    //diagonal
    assertEquals(new Coordinate(1, 1),
        Day9.RopeParts.moveKnot(new Coordinate(2, 2), new Coordinate(0, 0)));
    assertEquals(new Coordinate(3, 3),
        Day9.RopeParts.moveKnot(new Coordinate(2, 2), new Coordinate(4, 4)));
  }

  @Test
  void moveRope() throws IOException {
    List<String> inputLines = InputUtils.getInputLines("inputs/d9_sample.txt");
    assertEquals(13, Day9.moveRope(inputLines, 2));
  }

}