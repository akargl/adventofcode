package com.akargl.aoc24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToLongBiFunction;
import java.util.stream.Collectors;
import com.akargl.aoc24.utils.InputUtils;

public class Day7 {
  public static void main(String[] args) throws IOException {
    List<String> inputLines = InputUtils.getInputLines("inputs/d7_1.txt");

    List<ToLongBiFunction<Long, Long>> operations = new ArrayList<>();
    operations.add(Long::sum);
    operations.add((x, y) -> x * y);

    long sumPossibleCalcsP1 = getAmountPossibleCalcs(inputLines, operations);
    System.out.println("Part1: " + sumPossibleCalcsP1);

    operations.add((a, b) -> Long.parseLong(a.toString().concat(b.toString())));

    long sumPossibleCalcsP2 = getAmountPossibleCalcs(inputLines, operations);
    System.out.println("Part2: " + sumPossibleCalcsP2);
  }

  private static long getAmountPossibleCalcs(List<String> inputLines, List<ToLongBiFunction<Long, Long>> operations) {
    long possibleCalcsSum = 0;
    for (String inputLine : inputLines) {
      String[] parts = inputLine.split(":");
      long expectedResult = Long.parseLong(parts[0]);
      List<Long> numbers = Arrays.stream(parts[1].trim().split(" "))
          .map(Long::parseLong).collect(Collectors.toList());

      boolean isPossible = calcIsPossible(expectedResult, numbers, operations);
      //System.out.println(expectedResult + " " + isPossible);

      if (isPossible) {
        possibleCalcsSum += expectedResult;
      }
    }

    return possibleCalcsSum;
  }

  protected static boolean calcIsPossible(long result, List<Long> numbers, List<ToLongBiFunction<Long, Long>> operations) {
    if (numbers.isEmpty()) {
      return false;
    }

    if (numbers.size() == 1) {
      return numbers.getFirst() == result;
    }

    Long a = numbers.removeFirst();
    Long b = numbers.removeFirst();

    return operations.stream().anyMatch(op -> {
      List<Long> numbersCopy = new ArrayList<>(numbers);
      numbersCopy.addFirst(op.applyAsLong(a, b));
      return calcIsPossible(result, numbersCopy, operations);
    });
  }

}
