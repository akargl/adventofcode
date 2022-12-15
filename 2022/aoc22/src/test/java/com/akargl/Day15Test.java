package com.akargl;

import java.util.List;
import java.util.Set;
import com.akargl.utils.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {

  @Test
  void getManhattanDistance() {
    assertEquals(9, Day15.getManhattanDistance(new Coordinate(8, 7), new Coordinate(2, 10)));
  }

  @Test
  void isPointInRange() {
    Day15.Sensor sensor = new Day15.Sensor(new Coordinate(0, 0), 2);
    assertTrue(sensor.isPointInRange(2, 0));
    assertTrue(sensor.isPointInRange(-1, -1));
    assertTrue(sensor.isPointInRange(0, -2));
    assertFalse(sensor.isPointInRange(3, 0));
  }

  @Test
  void getPerimeterPoints() {
    Set<Coordinate> perimeterPoints = new Day15.Sensor(new Coordinate(0, 0), 2).getPerimeterPoints();
    assertEquals(12, perimeterPoints.size());

    perimeterPoints = new Day15.Sensor(new Coordinate(2, 18), 7).getPerimeterPoints();
    assertEquals(32, perimeterPoints.size());
  }
}