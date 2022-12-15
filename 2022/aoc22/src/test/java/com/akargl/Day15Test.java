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
  void getCoveredArea() {
    Day15.Sensor sensor = new Day15.Sensor(new Coordinate(0, 0), Day15.getManhattanDistance(new Coordinate(0, 0), new Coordinate(2, 0)));
    Set<Coordinate> coveredArea = sensor.getCoveredArea();
    assertEquals(13, coveredArea.size());
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
    Day15.Sensor sensor = new Day15.Sensor(new Coordinate(0, 0), 2);
    Set<Coordinate> perimeterPoints = sensor.getPerimeterPoints();
    assertEquals(1, perimeterPoints.size());
  }
}