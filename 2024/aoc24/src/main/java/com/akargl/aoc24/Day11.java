package com.akargl.aoc24;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.akargl.aoc24.utils.InputUtils;

public class Day11 {
  protected static Map<BlinkResult, Long> resultCache = new HashMap<>();

  public static void main(String[] args) throws IOException {
    String input = InputUtils.getInputLines("inputs/d11_1.txt").getFirst();

    List<String> stones = Arrays.stream(input.split(" ")).collect(Collectors.toList());

    long numStonesP1 = findNumStones(stones, 25);
    System.out.println(numStonesP1);

    long numStonesP2 = findNumStones(stones, 75);
    System.out.println(numStonesP2);
  }


  protected static long findNumStones(List<String> stones, long numBlinks) {
    long numStones = 0;

    for (String stone : stones) {
      System.out.println("processing initial stone " + stone);
      numStones += blink(stone, numBlinks);
    }

    return numStones;
  }

  protected static long blink(String stone, long numBlinks) {
    BlinkResult blinkResult = new BlinkResult(stone, numBlinks);
    if (resultCache.containsKey(blinkResult)) {
      return resultCache.get(blinkResult);
    }

    long numStones;

    if (numBlinks == 0L) {
      numStones = 1L;
    } else if (stone.equals("0")) {
      numStones = blink("1", numBlinks -1);
    } else if (stone.length() % 2 == 0) {
      String left = stone.substring(0, stone.length()/2);
      String right = Arrays.stream(stone.substring(stone.length() / 2)
          .split(""))
          .dropWhile(s -> s.equals("0"))
          .collect(Collectors.joining(""));

      if (right.isEmpty()) {
        right = "0";
      }

      numStones = blink(left, numBlinks - 1) + blink(right, numBlinks - 1);
    } else {
      long l = Long.parseLong(stone) * 2024L;
      numStones = blink(String.valueOf(l), numBlinks - 1);
    }

    resultCache.put(blinkResult, numStones);
    return numStones;
  }

  protected record BlinkResult(String stone, long numBlinks) {}
}
