package com.akargl;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import com.akargl.utils.Coordinate;
import com.akargl.utils.InputUtils;

import lombok.Data;

public class Day15 {
  @Data
  public static class Sensor extends Coordinate {
    int range;

    public Sensor(Coordinate orig, int range) {
      super(orig);
      this.range = range;
    }

    public int getMaxX() {
      return x + range;
    }

    public int getMinX() {
      return x - range;
    }

    public int getMaxY() {
      return y + range;
    }

    public int getMinY() {
      return y - range;
    }

    public boolean isPointInRange(int pX, int pY) {
      return Math.abs(pX -x) + Math.abs(pY - y) <= range;
    }

    public Set<Coordinate> getPerimeterPoints() {
      Set<Coordinate> perimeter = new HashSet<>();

      for (int xOffset = -range -1; xOffset <= range +1; xOffset++) {
        int yOffset = (-range -1 + Math.abs(xOffset));
        perimeter.add(new Coordinate(getX() +xOffset, getY() + yOffset));
        perimeter.add(new Coordinate(getX()+ xOffset, getY() - yOffset));
      }

      return perimeter;
    }
  }

  public static void main(String[] args) throws IOException {
    String input = InputUtils.getInput("inputs/d15_1.txt");


    int part1 = getNumCoveredCellsInLine(input, 2000000);
    System.out.println("Part 1: " + part1);

    long start = System.currentTimeMillis();
    long part2 = getUncoveredPoint(input, 4000000);
    long finish = System.currentTimeMillis();
    long timeElapsed = finish - start;
    System.out.println(timeElapsed);
    System.out.println("Part 2: " + part2);
  }

  public static int getNumCoveredCellsInLine(String input, int line) {
    Map<Coordinate, Coordinate> sensorsToBeacons = parseInput(input);
    List<Sensor> sensorRanges = getSensorRanges(sensorsToBeacons).stream()
        .filter(s -> s.getMaxY() >= line && s.getMinY() <= line).toList();

    Integer minX = sensorRanges.stream().map(Sensor::getMinX).min(Integer::compareTo).get();
    Integer maxX = sensorRanges.stream().map(Sensor::getMaxX).max(Integer::compareTo).get();

    int numCoveredPoints = 0;
    for (int x = minX; x <= maxX; x++) {
      int finalX = x;
      if (sensorRanges.stream().anyMatch(s -> s.isPointInRange(finalX, line))) {
        numCoveredPoints++;
      }
    }

    Set<Coordinate> beaconsInLineXValues = sensorsToBeacons.values().stream()
        .filter(b -> b.getY() == line && b.getX() >= minX && b.getX() <= maxX).collect(Collectors.toSet());

    return numCoveredPoints - beaconsInLineXValues.size();
  }

  public static long getUncoveredPoint(String input, int maxXY) {
    Map<Coordinate, Coordinate> sensorsToBeacons = parseInput(input);
    List<Sensor> sensorRanges = getSensorRanges(sensorsToBeacons);

    Coordinate uncoveredPoint = sensorRanges.stream().map(Sensor::getPerimeterPoints).flatMap(Collection::stream)
        .filter(p -> p.getX() <= maxXY && p.getX() >= 0 && p.getY() <= maxXY && p.getY() >= 0)
        .filter(p -> sensorRanges.stream().noneMatch(s -> s.isPointInRange(p.getX(), p.getY())))
        .findAny().orElse(null);

    return uncoveredPoint.getX() * 4000000L + uncoveredPoint.getY();
  }

  public static Map<Coordinate, Coordinate> parseInput(String input) {
    Map<Coordinate, Coordinate> sensorsToBeacons = new HashMap<>();

    final String regex = "Sensor at x=(?<sensorX>-?\\d+), y=(?<sensorY>-?\\d+): closest beacon is at x=(?<beaconX>-?\\d+), y=(?<beaconY>-?\\d+)";

    final Matcher matcher = Pattern.compile(regex, Pattern.MULTILINE).matcher(input);

    while (matcher.find()) {
      Coordinate sensor = new Coordinate(Integer.parseInt(matcher.group("sensorX")), Integer.parseInt(matcher.group("sensorY")));
      Coordinate beacon = new Coordinate(Integer.parseInt(matcher.group("beaconX")), Integer.parseInt(matcher.group("beaconY")));
      sensorsToBeacons.put(sensor, beacon);
    }

    return sensorsToBeacons;
  }

  public static List<Sensor> getSensorRanges(Map<Coordinate, Coordinate> sensorToBeacons) {
    return sensorToBeacons.entrySet().stream().map(e -> new Sensor(e.getKey(), getManhattanDistance(e.getKey(), e.getValue()))).collect(Collectors.toList());
  }

  public static int getManhattanDistance(Coordinate c1, Coordinate c2) {
    return Math.abs(c1.getX() - c2.getX()) + Math.abs(c1.getY() - c2.getY());
  }
}
