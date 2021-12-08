package days;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utils.InputUtils;

public class Day7 {
  static long fuelCostForPosition(List<Integer> positions, int moveTo, BiFunction<Integer, Integer, Integer> calcFuelCost) {
    long sumOfFuelCost = positions.stream().map(p -> calcFuelCost.apply(p, moveTo)).mapToLong(Integer::longValue).sum();
    return sumOfFuelCost;
  }

  static long getMinCost(List<Integer> positions, BiFunction<Integer, Integer, Integer> calcFuelCost) {
    Integer max = Collections.max(positions);
    Integer min = Collections.min(positions);

    List<Long> costs = IntStream.rangeClosed(min, max).mapToLong(moveTo -> fuelCostForPosition(positions, moveTo, calcFuelCost)).boxed().toList();
    Long minCost = Collections.min(costs);
    return minCost;
  }

  public static void main(String[] args) throws IOException {
    List<Integer> initialPositions = Arrays.stream(InputUtils.getInputLines(Path.of("inputs/input7_sample.txt"), false).get(0).split(","))
        .map(Integer::parseInt).collect(Collectors.toList());

    long minCostPart1 = getMinCost(initialPositions, (p, moveTo) -> Math.abs(p - moveTo));
    System.out.println("Part1: " + minCostPart1);

    long minCostPart2 = getMinCost(initialPositions, (p, moveTo) -> {
      int fuel = 0;
      for (int i = 1; i <= Math.abs(p -moveTo) ; i++) {
        fuel += i;
      }
      return fuel;
    });
    System.out.println("Part2: " + minCostPart2);
  }
}
