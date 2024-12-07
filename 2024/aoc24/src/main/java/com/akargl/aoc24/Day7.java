package com.akargl.aoc24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.akargl.aoc24.utils.InputUtils;

public class Day7 {
  public static void main(String[] args) throws IOException {
    List<String> inputLines = InputUtils.getInputLines("inputs/d7_1.txt");

    List<Long> possibleCalcs = new ArrayList<>();

    for (String inputLine : inputLines) {
      String[] parts = inputLine.split(":");
      long expectedResult = Long.parseLong(parts[0]);
      List<Long> numbers = Arrays.stream(parts[1].trim().split(" "))
          .map(Long::parseLong).collect(Collectors.toList());

      boolean isPossible = calcIsPossible(expectedResult, numbers);
      //System.out.println(expectedResult + " " + isPossible);

      if (isPossible) {
        possibleCalcs.add(expectedResult);
      }
    }

    Long sumPossibleCalcs = possibleCalcs.stream().reduce(0L, Long::sum);
    System.out.println(sumPossibleCalcs);
  }

  protected static boolean calcIsPossible(long result, List<Long> numbers) {
    if (numbers.isEmpty()) {
      return false;
    }

    if (numbers.size() == 1) {
      return numbers.getFirst() == result;
    }

    Long a = numbers.removeFirst();
    Long b = numbers.removeFirst();

    List<Long> list1 = new ArrayList<>(numbers);
    list1.addFirst(a + b);
    boolean b1 = calcIsPossible(result, list1);

    List<Long> list2 = new ArrayList<>(numbers);
    list2.addFirst(a * b);
    boolean b2 = calcIsPossible(result, list2);

    return b1 || b2;
  }

}
