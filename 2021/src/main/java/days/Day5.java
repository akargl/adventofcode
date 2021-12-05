package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class Day5 {
  @Data
  @AllArgsConstructor
  private static class Vector {
    private int x;
    private int y;

    double getLength() {
      return Math.sqrt(x * x + y * y);
    }

    Vector add(Vector v) {
      return new Vector(x + v.x, y + v.y);
    }
  }

  @Data
  @RequiredArgsConstructor
  private static class VentLine {
    @NonNull
    private Day5.Vector start;
    @NonNull
    private Day5.Vector end;

    Vector getDirection() {
      Vector direction = new Vector(end.getX() - start.getX(), end.getY() - start.getY());
      double length = direction.getLength();
      direction.setX((int) Math.round((double)direction.getX()/length));
      direction.setY((int) Math.round((double)direction.getY()/length));

      return direction;
    }

    boolean isVerticalOrHorizontal() {
      return (start.getX() == end.getX()) || (start.getY() == end.getY());
    }

    List<Vector> getAllPointsOnLine() {
      Vector direction = getDirection();
      List<Vector> points = new ArrayList<>();

      Vector v = start;
      points.add(v);
      while (!v.equals(end)) {
        v = v.add(direction);
        points.add(v);
      }

      return points;
    }
  }

   static List<VentLine> parseInput(List<String> inputLines) {
    return inputLines.stream().map(l -> Arrays.stream(l.split("\s*->\s*"))
          .map(p -> p.split(","))
          .map(p -> new Vector(Integer.parseInt(p[0]), Integer.parseInt(p[1]))).collect(Collectors.toList())
    ).map(points -> new VentLine(points.get(0), points.get(1))).toList();
  }

  static List<String> getInputLines(Path inputPath) throws IOException {
    return Files.lines(inputPath)
        .filter(l -> !l.trim().equals(""))
        .collect(Collectors.toList());
  }

  static Set<Vector> getDuplicatePoints(List<VentLine> ventLines) {
    List<Vector> allPoints = ventLines.stream()
        .map(VentLine::getAllPointsOnLine)
        .flatMap(Collection::stream).toList();

    Set<Vector> points = new HashSet<>();
    return allPoints.stream().filter(p -> !points.add(p)).collect(Collectors.toSet());
  }

  public static void main(String[] args) throws IOException {
    List<String> inputLines = getInputLines(Path.of("inputs/input5_1.txt"));
    List<VentLine> ventLines = parseInput(inputLines);

    Set<Vector> duplicatePointsPart1 = getDuplicatePoints(ventLines.stream()
        .filter(VentLine::isVerticalOrHorizontal).collect(Collectors.toList()));

    Set<Vector> duplicatePointsPart2 = getDuplicatePoints(ventLines);

    System.out.println("Part1: " + duplicatePointsPart1.size());
    System.out.println("Part2: " + duplicatePointsPart2.size());
  }
}
