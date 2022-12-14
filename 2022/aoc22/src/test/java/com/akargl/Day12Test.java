package com.akargl;

import java.io.IOException;
import com.akargl.utils.Coordinate;
import com.akargl.utils.InputUtils;
import com.akargl.utils.StringGrid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {
  StringGrid grid;

  @BeforeEach
  void setUp() throws IOException {
    grid = new StringGrid(InputUtils.getInputLines("inputs/d12_sample.txt"));
  }

  @Test
  void getMinDistance() {
  }

  @Test
  void isVisitable() {
    assertTrue(Day12.isVisitable(new Coordinate(0, 0), new Coordinate(1, 0), grid));
    assertTrue(Day12.isVisitable(new Coordinate(0, 0), new Coordinate(0, 1), grid));
    assertFalse(Day12.isVisitable(new Coordinate(0, 0), new Coordinate(1, 1), grid));
    assertFalse(Day12.isVisitable(new Coordinate(0, 0), new Coordinate(0, 2), grid));
    assertTrue(Day12.isVisitable(new Coordinate(3, 2), new Coordinate(2, 2), grid));
  }

  @Test
  void getElevation() {
    assertEquals('a', Day12.getElevation("S"));
    assertEquals('z', Day12.getElevation("E"));
    assertEquals('u', Day12.getElevation("u"));
  }
}